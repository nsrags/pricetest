package com.ascena.price.service.cache.dao;

import java.util.List;
import java.util.Map;

import com.ascena.price.service.cache.vo.PriceCacheVo;
import com.ascena.price.vo.SkuPrice;

/**
 * Price Cache interface
 * 
 * @author smeenavalli
 *
 */
public interface PriceCacheInfDAO {
	/**
	 * get Cache By Key
	 * 
	 * @param skuId String 
	 * @return SkuPrice
	 */
	public SkuPrice getById(final String key);

	/**
	 * get cache By Key List
	 * 
	 * @param keyids List<String>
	 * @return Map<String, SkuPrice>
	 */
	public Map<String, SkuPrice> getByIdList(final List<String> keyids);

	/**
	 * create cache by PriceCacheVo
	 * 
	 * @param cacheEntry PriceCacheVo
	 * @return Boolean
	 */
	public Boolean create(final PriceCacheVo cacheEntry);

	/**
	 * create by List<PriceCacheVo>
	 * 
	 * @param cacheEntriesList List<PriceCacheVo>
	 * @return Boolean
	 */
	public Boolean create(final List<PriceCacheVo> cacheEntries);

	/**
	 * Delete cache by key
	 * 
	 * @param key String
	 * @return Boolean
	 */
	public Boolean delete(final String key);
}
