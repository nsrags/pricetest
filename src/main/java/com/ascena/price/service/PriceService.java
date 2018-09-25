package com.ascena.price.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ascena.price.common.util.PriceUtils;
import com.ascena.price.config.ApplicationConfig;
import com.ascena.price.config.ErrorMsgPropsConfig;
import com.ascena.price.dao.PriceDAO;
import com.ascena.price.dao.datastore.domain.Price;
import com.ascena.price.exceptions.PriceIntegrationException;
import com.ascena.price.exceptions.SysException;
import com.ascena.price.payload.ErrorPayloadGenerator;
import com.ascena.price.service.cache.dao.PriceCacheInfDAO;
import com.ascena.price.service.cache.dao.transform.CacheRespTransformer;
import com.ascena.price.service.cache.vo.PriceCacheVo;
import com.ascena.price.service.integration.PrcProductInfoIntg;
import com.ascena.price.vo.PrcServiceError;
import com.ascena.price.vo.SkuPrice;

/**
 * PriceService
 * 
 * @author smeenavalli
 *
 */
@Service
public class PriceService implements PriceServiceInf {
	private static Logger logger = LoggerFactory.getLogger(PriceService.class);

	@Autowired
	PriceCacheInfDAO cache;

	@Autowired
	PrcProductInfoIntg productInfoIntg;

	@Autowired
	ErrorPayloadGenerator errorPayloadGenerator;

	@Autowired
	PriceDAO<Price> priceDAO;

	@Autowired
	CacheRespTransformer cacheRespTransformer;

	@Autowired
	ErrorMsgPropsConfig errorMsgPropsConfig;

	@Autowired
	PriceUtils priceUtils;

	@Autowired
	ApplicationConfig appConfig;

	/**
	 * getPriceBySku
	 * 
	 * @param skuId
	 * 
	 * @param priceStrDateStr
	 *            String
	 * @param priceEndDateStr
	 *            String
	 * @return SkuPrice
	 * @throws SysException
	 */
	@Override
	public SkuPrice getPriceBySku(final String skuId, final String priceStrDateStr, final String priceEndDateStr)
			throws SysException {
		return getPrice(skuId, priceStrDateStr, priceEndDateStr);
	}

	/**
	 * getPrice
	 * 
	 * @param skuId
	 *            String
	 * @param priceStrDateStr
	 *            String
	 * @param priceEndDateStr
	 *            String
	 * @return SkuPrice
	 * @throws SysException
	 */
	public SkuPrice getPrice(String skuId, String priceStrDateStr, String priceEndDateStr) throws SysException {
		Date priceStartDate = null;
		Date priceEndDate = null;
		if (!StringUtils.isEmpty(priceStrDateStr)) {
			priceStartDate = priceUtils.parseDate(priceStrDateStr);
		} else {
			priceStartDate = new Date(System.currentTimeMillis());
		}
		if (!StringUtils.isEmpty(priceEndDateStr)) {
			priceEndDate = priceUtils.parseDate(priceEndDateStr);
		} else {
			priceEndDate = new Date(System.currentTimeMillis());
		}
		return getPrice(skuId, priceStartDate, priceEndDate);
	}

	/**
	 * getPriceByProduct
	 * 
	 * @param productId
	 *            String
	 * @return Map<String, SkuPrice> - Map with SKU's Price
	 * @throws PriceIntegrationException
	 * @throws SystemException
	 */
	public Map<String, SkuPrice> getPriceByProduct(final String productId)
			throws SysException, PriceIntegrationException {

		List<String> skuIds = null;
		Map<String, SkuPrice> skuPrices = new HashMap<>();
		// Get Active SKUl's List for a Product from Product Info Service
		skuIds = productInfoIntg.getActiveSkus(productId);
		if (CollectionUtils.isEmpty(skuIds)) {
			return skuPrices;
		}

		Map<String, SkuPrice> cacheEntries = getPrice(skuIds);
		if (!CollectionUtils.isEmpty(cacheEntries)) {
			skuIds.forEach(skuid -> {
				if (cacheEntries.containsKey(skuid)) {
					skuPrices.put(skuid, cacheEntries.get(skuid));

				} else {
					SkuPrice skuPrice = errorPayloadGenerator.generateSkuErrorPayload(skuid);
					skuPrices.put(skuid, skuPrice);
				}
			});
		}

		return skuPrices;
	}

	/**
	 * Gives Price for a given Sku Id
	 * 
	 * @param skuId
	 *            String
	 * @return SkuPrice
	 * @throws SysException
	 */
	private SkuPrice getPrice(final String skuId, final Date priceStrDate, final Date priceEndDate)
			throws SysException {
		SkuPrice skuPrice = null;
		Price priceEntity = null;
		// Check entry in Redis cache
		skuPrice = cache.getById(skuId);
		if (skuPrice.getSkuId() != null) {// cache exists in redis
			return skuPrice;
		} else {// cache not exists in redis
				// Call Google Data store data base
			priceEntity = callDataStore(skuId, priceStrDate, priceEndDate);

			if (priceEntity == null) {
				return noEntityExistsInDb(skuId);
			}
			// store in Redis cache if Price found in database
			PriceCacheVo newCacheEntry = cacheRespTransformer.transformToCacheObj(priceEntity);
			Boolean isCacheCrtd = cache.create(newCacheEntry);
			if (!isCacheCrtd && logger.isErrorEnabled()) {
				logger.error(String.format("Failed to create cache entry in Redis : Cache Object: %s", newCacheEntry));
			} else {
				logger.error(String.format("Cache entry has been created in Redis : Cache Object: %s", newCacheEntry));
			}
			return cacheRespTransformer.transformToSku(newCacheEntry);

		}

	}

	/**
	 * callDataStore
	 * 
	 * @param skuId
	 * @return Price
	 * @throws SysException
	 */

	private Price callDataStore(final String skuId, final Date priceStrDate, final Date priceEndDate)
			throws SysException {

		return priceDAO.getByPrcForSkuId(skuId, priceStrDate, priceEndDate);
	}

	/**
	 * callDataStore
	 * 
	 * @param skusPrices
	 *            Map<String, SkuPrice>
	 * @param skuIds
	 *            List<String>
	 * @return List<Price>
	 * @throws SysException
	 */
	private List<Price> callDataStore(final Map<String, SkuPrice> skusPrices, final List<String> skuIds)
			 {
		List<String> databaseKeys = new ArrayList<>();
		List<Price> prices = new ArrayList<>();
		// List if any entries are not available in cache
		if (!CollectionUtils.isEmpty(skuIds)) {
			skuIds.forEach(skuid -> {
				if (!skusPrices.containsKey(skuid)) {
					databaseKeys.add(skuid);
				}

			});
		}
		// call Google Data store for those only
		if (!CollectionUtils.isEmpty(databaseKeys)) {
			return priceDAO.getById(databaseKeys);
		}

		return prices;
	}
	/**
	 * Price not found in Database for skuId
	 * 
	 * @param skuId
	 *            String
	 * @return SkuPrice
	 */
	private SkuPrice noEntityExistsInDb(final String skuId) {
		List<PrcServiceError> errors = new ArrayList<>();
		StringBuilder errorDesc = new StringBuilder();
		SkuPrice skuPrice = new SkuPrice();
		PrcServiceError error = new PrcServiceError();
		error.setErrorCode(errorMsgPropsConfig.getNoRecFoundInDbErrorCode());
		errorDesc.append(errorMsgPropsConfig.getNoRecFoundInDbErrorMessage());
		errorDesc.append(skuId);
		if (logger.isErrorEnabled()) {
			logger.error(String.format("Price not found in Google Data Store for SKU  %s", skuId));
		}
		error.setErrorDescription(errorDesc.toString());
		errors.add(error);
		skuPrice.setErrors(errors);
		return skuPrice;
	}

	/**
	 * getPrice
	 * 
	 * @param skuIdsList
	 *            List<String>
	 * @return Map<String, SkuPrice>
	 * @throws SysException
	 */	
	@Override	
	public Map<String, SkuPrice> getPrice(final List<String> skuIds) throws SysException {
		Map<String, SkuPrice> skusPrices = null;
		Map<String, SkuPrice> skusDbEntities = null;
		List<String> cacheKeys = new ArrayList<>();
		skuIds.forEach(skuId -> cacheKeys.add(priceUtils.createCacheKey(skuId)));
		// Check entry in Redis cache
		skusPrices = cache.getByIdList(cacheKeys);
		// Call database if any of the entries are found not found in cache
		List<Price> dbEntities = callDataStore(skusPrices, skuIds);
		if (!CollectionUtils.isEmpty(dbEntities)) {
			// store entries in Redis cache for further calls
			List<PriceCacheVo> cacheEntries = cacheRespTransformer.transformToCacheObj(dbEntities);
			if (!CollectionUtils.isEmpty(cacheEntries)) {
				cache.create(cacheEntries);
				skusDbEntities = cacheRespTransformer.transformToSku(cacheEntries);
			}
		}
		if (!CollectionUtils.isEmpty(skusDbEntities)) {
			skusPrices.putAll(skusDbEntities);
		}

		return skusPrices;
	}

	
	@Override
	public SkuPrice getPriceBySku(String skuId) throws SysException {

		return getPrice(skuId, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));

	}

}
