package com.ascena.price.service.cache.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ascena.price.common.util.PriceUtils;
import com.ascena.price.config.ApplicationConfig;
import com.ascena.price.service.cache.dao.transform.CacheRespTransformer;
import com.ascena.price.service.cache.vo.PriceCacheVo;
import com.ascena.price.vo.SkuPrice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
/**
 * Cache Implementation class for Price
 * 
 * @author smeenavalli
 *
 */
public class PriceCacheImplDAO implements PriceCacheInfDAO {

	private static Logger logger = LoggerFactory.getLogger(PriceCacheImplDAO.class);

	@Autowired
	RedisRepositoryDAO cacheRepository;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	CacheRespTransformer cacheRespTransformer;

	@Autowired
	ApplicationConfig appConfiguration;

	@Autowired
	PriceUtils priceUtils;

	@HystrixCommand(fallbackMethod = "getByIdFallback", groupKey = "PriceServiceCacheGroup", commandKey = "PriceCacheGetByIdCmd", threadPoolKey = "getPriceByIdThreadPool", 
			commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "10"),
					@HystrixProperty(name = "maxQueueSize", value = "100"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000") })
	@Override
	public SkuPrice getById(final String skuId) {
		// Find entry in Redis
		String cacheEntry = cacheRepository.find(priceUtils.createCacheKey(skuId));
		SkuPrice skuPrice = new SkuPrice();
		PriceCacheVo priceCacheVo = null;
		if (!StringUtils.isEmpty(cacheEntry)) {
			priceCacheVo = deserializeJSON(cacheEntry);
			skuPrice = cacheRespTransformer.transformToSku(priceCacheVo);

		}
		return skuPrice;
	}

	@HystrixCommand(fallbackMethod = "getByListIdFallback", groupKey = "PriceServiceCacheGroup", commandKey = "PriceCacheGetByIdListCmd", threadPoolKey = "getPriceByIdListThreadPool", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "10"),
					@HystrixProperty(name = "maxQueueSize", value = "100"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000") })
	@Override
	public Map<String, SkuPrice> getByIdList(final List<String> skuIds) {
		Map<String, SkuPrice> skusPrices = new HashMap<>();
		// Find all
		List<String> prcCacheEntries = cacheRepository.findAll(skuIds);
		if (!CollectionUtils.isEmpty(prcCacheEntries)) {
			prcCacheEntries.forEach(cacheEntry -> {
				PriceCacheVo priceCacheVo = null;
				SkuPrice skuPrice = null;
				if (!StringUtils.isEmpty(cacheEntry)) {
					priceCacheVo = deserializeJSON(cacheEntry);
					skuPrice = cacheRespTransformer.transformToSku(priceCacheVo);
					if (skuPrice != null && !StringUtils.isEmpty(skuPrice.getSkuId())) {
						skusPrices.put(skuPrice.getSkuId(), skuPrice);
					}

				}
			});
		}
		return skusPrices;
	}

	@HystrixCommand(fallbackMethod = "createFallback", groupKey = "PriceServiceCacheGroup", commandKey = "PriceCacheCreateCmd", threadPoolKey = "createPriceThreadPool", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "10"),
					@HystrixProperty(name = "maxQueueSize", value = "100"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000") })
	@Override
	public Boolean create(final PriceCacheVo priceCacheVo) {

		String priceStr = null;
		try {
			priceStr = objectMapper.writeValueAsString(priceCacheVo);
		} catch (JsonProcessingException jse) {
			if (logger.isErrorEnabled()) {
				logger.error(String.format("Json Processing Failures to create Cache %s Details : %s", priceCacheVo,
						jse.getMessage()));
			}
			return Boolean.FALSE;
		}
		if (!StringUtils.isEmpty(priceStr)) {
			// Create cache in Redis
			cacheRepository.create(priceUtils.createCacheKey(priceCacheVo.getSkuId()), JSONValue.escape(priceStr));
		}

		return Boolean.TRUE;
	}

	@Override
	@HystrixCommand(fallbackMethod = "deleteFallback", groupKey = "PriceServiceCacheGroup", commandKey = "PriceCacheDeleteCmd", threadPoolKey = "deletePriceThreadPool", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "10"),
					@HystrixProperty(name = "maxQueueSize", value = "101"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000") })
	public Boolean delete(final String key) {
		// Delete cache
		cacheRepository.delete(priceUtils.createCacheKey(key));
		return Boolean.TRUE;
	}

	@HystrixCommand(fallbackMethod = "createListFallback", groupKey = "PriceServiceCacheGroup", commandKey = "PriceCacheCreateListCmd", threadPoolKey = "createPriceListThreadPool", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "10"),
					@HystrixProperty(name = "maxQueueSize", value = "101"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000") })
	@Override
	public Boolean create(final List<PriceCacheVo> cacheEntries) {
		Map<String, String> skuPriceCaches = new HashMap<>();
		cacheEntries.forEach(cacheVo -> {
			try {
				String priceStr = objectMapper.writeValueAsString(cacheVo);
				skuPriceCaches.put(priceUtils.createCacheKey(cacheVo.getSkuId()), JSONValue.escape(priceStr));
			} catch (JsonProcessingException e) {
				if (logger.isErrorEnabled()) {
					logger.error(String.format("Server Failures to create Cache %s ", cacheVo));
				}
			}
		});
		if (!CollectionUtils.isEmpty(skuPriceCaches)) {
			cacheRepository.create(skuPriceCaches);
		}
		return Boolean.TRUE;
	}

	/**
	 * getByIdFallback
	 * 
	 * @param skuId
	 *            String
	 * @return SkuPrice SkuPrice
	 * @throws Throwable error
	 */
	public SkuPrice getByIdFallback(final String skuId, final Throwable error) {
		SkuPrice sku = new SkuPrice();
		if (logger.isErrorEnabled()) {
			logger.error(
					String.format("Server Failures for getById %s : Error Details: %s", skuId, error.getMessage()));
		}
		return sku;

	}

	/**
	 * getByListIdFallback 
	 * @param skuIdsList skuIds
	 * @param error Throwable
	 * @return
	 */
	public Map<String, SkuPrice> getByListIdFallback(final List<String> skuIds, final Throwable error) {
		if (logger.isErrorEnabled()) {
			logger.error(String.format("Server Failures for Cache getByListId %s Error Details: %s", skuIds,
					error.getMessage()));
		}

		return new HashMap<>();

	}

	/**
	 * createFallback 
	 * @param priceCacheVo PriceCacheVo
	 * @param Throwable error
	 * @return Boolean
	 */
	public Boolean createFallback(final PriceCacheVo priceCacheVo, final Throwable error) {

		if (logger.isErrorEnabled()) {
			logger.error(String.format("Server Failures for Cache Create %s Error Details: %s", priceCacheVo,
					error.getMessage()));
		}

		return Boolean.FALSE;
	}

	/**
	 * deleteFallback 
	 * @param skuId String
	 * @return Boolean
	 */
	public Boolean deleteFallback(final String skuId) {

		if (logger.isErrorEnabled()) {
			logger.error(String.format("Server Failures for Cache Deletion %s ", skuId));
		}
		return Boolean.FALSE;
	}

	/**
	 * deserializeJSON
	 * @param cacheEntry String
	 * @return PriceCacheVo
	 */
	private PriceCacheVo deserializeJSON(final String cacheEntry) {
		PriceCacheVo priceCacheVo = null;
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("deserializeJSON %s", cacheEntry));
		}
		try {
			priceCacheVo = objectMapper.readValue(StringEscapeUtils.unescapeJava(cacheEntry), PriceCacheVo.class);

		} catch (IOException ioe) {
			if (logger.isErrorEnabled()) {
				logger.error(String.format(" Deserialize JSON Error Details:  %s ", ioe.getMessage()));
			}
		}
		return priceCacheVo;
	}

	/**
	 * createListFallback
	 * @param cacheEntries List<PriceCacheVo>
	 * @param Throwable error
	 * @return Boolean
	 */
	public Boolean createListFallback(final List<PriceCacheVo> cacheEntries, final Throwable error) {

		if (logger.isErrorEnabled()) {
			logger.error(String.format("Server Failures for Cache Create entries %s Error Details: %s", cacheEntries,
					error.getMessage()));
		}

		return Boolean.FALSE;
	}

}
