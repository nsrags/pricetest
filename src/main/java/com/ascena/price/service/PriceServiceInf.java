package com.ascena.price.service;

import java.util.List;
import java.util.Map;

import com.ascena.price.exceptions.PriceIntegrationException;
import com.ascena.price.exceptions.SysException;
import com.ascena.price.vo.SkuPrice;

/**
 * 
 * @author smeenavalli
 *
 */
public interface PriceServiceInf {
	
	/**
	 * getPriceBySku for sku ID
	 * @param skuId String
	 * @return SkuPrice
	 * @throws SysException
	 */
	public SkuPrice getPriceBySku(final String skuId) throws SysException;
	
	/**
	 * getPriceBySku for sku ID, start Date,endDate
	 * @param skuId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SysException
	 */
	public SkuPrice getPriceBySku(final String skuId,final String startDate,final String endDate) throws SysException;
	
	/**
	 * getPriceByProduct
	 * @param productId String
	 * @return Map<String, SkuPrice>
	 * @throws SysException
	 * @throws PriceIntegrationException
	 */
	public Map<String, SkuPrice> getPriceByProduct(final String productId) throws SysException, PriceIntegrationException;
	
	/**
	 * getPrice
	 * @param skuIds List<String>
	 * @return Map<String, SkuPrice>
	 * @throws SysException
	 */
	public Map<String, SkuPrice> getPrice(final List<String> skuIds) throws SysException;
	
}
