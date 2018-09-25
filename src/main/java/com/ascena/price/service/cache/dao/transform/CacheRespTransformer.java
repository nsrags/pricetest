package com.ascena.price.service.cache.dao.transform;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ascena.price.common.constant.PriceConstants;
import com.ascena.price.dao.datastore.domain.Price;
import com.ascena.price.service.cache.vo.PriceCacheVo;
import com.ascena.price.vo.SkuPrice;

@Component
/**
 * Transforms Cache object to Response payload ( SKU)
 * 
 * @author smeenavalli
 *
 */
public class CacheRespTransformer {

	private static Logger logger = LoggerFactory.getLogger(CacheRespTransformer.class);

	/**
	 * transforms To Sku
	 * 
	 * @param priceCacheVo
	 *            - PriceCacheVo
	 * @return SkuPrice
	 */
	public SkuPrice transformToSku(final PriceCacheVo priceCacheVo) {
		SkuPrice skuPrice = new SkuPrice();
		if (priceCacheVo != null) {
			// skuId
			skuPrice.setSkuId(priceCacheVo.getSkuId());
			skuPrice.setSalePrice(priceCacheVo.getSalePrice());
			skuPrice.setCurrency(priceCacheVo.getCurrency());
			skuPrice.setGwpEligible( priceCacheVo.getGwpEligible());
			skuPrice.setIsOnClerance( priceCacheVo.getIsOnClerance());
			skuPrice.setListPrice( priceCacheVo.getListPrice());
			skuPrice.setMsrp( priceCacheVo.getMsrp());
			skuPrice.setPomoId( priceCacheVo.getPomoId());
			skuPrice.setPomoPrice( priceCacheVo.getPomoPrice());
			skuPrice.setPriceEndDate( priceCacheVo.getPriceEndDate());
			skuPrice.setPriceId( priceCacheVo.getPriceId());
			skuPrice.setPricePoint( priceCacheVo.getPricePoint());
			skuPrice.setPriceStartDate( priceCacheVo.getPriceStartDate());
			skuPrice.setPromoMessage( priceCacheVo.getPromoMessage());
			
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Price Cache Vo %s", priceCacheVo));
			}
		}
		return skuPrice;
	}
	
	/**
	 * transformToSku
	 * @param priceCaches List<PriceCacheVo>
	 * @return Map<String, SkuPrice>
	 */
	public Map<String, SkuPrice> transformToSku(final List<PriceCacheVo> priceCaches) {
		Map<String, SkuPrice> skuPrices = new HashMap<>();
		priceCaches.forEach(priceCacheVo -> {
			SkuPrice skuPrice = transformToSku(priceCacheVo);
			if(!StringUtils.isEmpty(skuPrice.getSkuId()) ) {
				skuPrices.put(skuPrice.getSkuId(), skuPrice);
			}
			
		});
		return skuPrices;
	}
		
	/**
	 * transforms To Sku
	 * 
	 * @param Price
	 *            - priceEntity
	 * @return PriceCacheVo
	 */
	public PriceCacheVo transformToCacheObj(final Price priceEntity) {
		PriceCacheVo cacheEntry = new PriceCacheVo();
		if (priceEntity != null) {
			cacheEntry.setCurrency(priceEntity.getCurrency());
			cacheEntry.setSkuId(priceEntity.getSkuId());
			cacheEntry.setSalePrice(priceEntity.getSalePrice());
			cacheEntry.setCurrency(priceEntity.getCurrency());
			cacheEntry.setGwpEligible( priceEntity.getGwpEligible());
			cacheEntry.setIsOnClerance( priceEntity.getIsOnClerance());
			cacheEntry.setListPrice( priceEntity.getListPrice());
			cacheEntry.setMsrp( priceEntity.getMsrp());
			cacheEntry.setPomoId( priceEntity.getPomoId());
			cacheEntry.setPomoPrice( priceEntity.getPomoPrice());
			cacheEntry.setPriceStartDate(priceEntity.getPriceStartDate());
			cacheEntry.setPriceEndDate(priceEntity.getPriceEndDate());
			cacheEntry.setPriceId( priceEntity.getPriceId());
			cacheEntry.setPricePoint( priceEntity.getPricePoint());
			cacheEntry.setPromoMessage( priceEntity.getPromoMessage());
			cacheEntry.setCacheCreatedBy(PriceConstants.PRICE_SERVICE.toString());
			cacheEntry.setCacheUpdatedBy(PriceConstants.PRICE_SERVICE.toString());
			Instant currentDate = Instant.ofEpochMilli(System.currentTimeMillis());
			cacheEntry.setCacheCreatedOn(currentDate.toString());
			cacheEntry.setCacheUpdatedOn(currentDate.toString());
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Price Entry : Database Vo %s", priceEntity));
				logger.debug(String.format("Price Entry : Cache Entity Vo %s", cacheEntry));
			}
		}
		return cacheEntry;
	}
	/**
	 * transform Database entry To Cache Obj for saving in Redis
	 * @param dbEntities List<Price>
	 * @return List<PriceCacheVo>
	 */
	public List<PriceCacheVo> transformToCacheObj(final List<Price> dbEntities) {
		List<PriceCacheVo> priceCaches = new ArrayList<>();
		dbEntities.forEach(priceEntity -> {
			if(priceEntity != null){
				PriceCacheVo cacheVo = transformToCacheObj(priceEntity);
				priceCaches.add(cacheVo);	
			}
			
		});
		return priceCaches;
	}
}
