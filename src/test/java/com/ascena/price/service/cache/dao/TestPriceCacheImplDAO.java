package com.ascena.price.service.cache.dao;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;
import com.ascena.price.common.util.PriceUtils;
import com.ascena.price.config.ApplicationConfig;
import com.ascena.price.service.cache.dao.transform.CacheRespTransformer;
import com.ascena.price.service.cache.vo.PriceCacheVo;
import com.ascena.price.vo.SkuPrice;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author smeenavalli
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
public class TestPriceCacheImplDAO extends MockRedisRepositoryDAO {

	@Autowired
	PriceCacheImplDAO priceCacheImplDAO;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	CacheRespTransformer cacheRespTransformer;

	@Autowired
	ApplicationConfig appConfiguration;

	@Autowired
	PriceUtils priceUtils;

	@Before
	public void init() {
		super.init();
		priceCacheImplDAO = new PriceCacheImplDAO();
		priceCacheImplDAO.cacheRepository = this.redisRepository;
		priceCacheImplDAO.objectMapper = this.objectMapper;
		priceCacheImplDAO.cacheRespTransformer = this.cacheRespTransformer;
		this.appConfiguration.setIsCachePrefixEnabled("false");
		priceCacheImplDAO.appConfiguration = this.appConfiguration;
		priceCacheImplDAO.priceUtils = this.priceUtils ;
	}
    
	@Test
	public void testGetByIdList_Scenario2() {
		List<PriceCacheVo> cacheList = new ArrayList<>();
		PriceCacheVo priceCache1 = new PriceCacheVo();		
		priceCache1.setCacheCreatedBy("Batch Admin");
		priceCache1.setCacheCreatedOn("2018-06-20T16:35:40.102Z");
		priceCache1.setSkuId("30064566");
		priceCache1.setSalePrice("10.40");
		priceCache1.setListPrice("14.90");
		priceCache1.setMsrp("14.90");
		priceCache1.setCurrency("$");
		priceCache1.setGwpEligible("false");
		priceCache1.setIsOnClerance("false");
		priceCache1.setPomoId("Prom1232");
		priceCache1.setPomoPrice("10.40");
		priceCache1.setPriceId("10001");
		priceCache1.setPricePoint("");
		priceCache1.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		priceCache1.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		priceCache1.setPromoMessage("Limited time 30% off!");
		priceCache1.setCacheUpdatedBy("Price Service");
		priceCache1.setCacheUpdatedOn("2018-06-20T16:35:40.102Z");
		cacheList.add(priceCache1);
		PriceCacheVo priceCache2 = new PriceCacheVo();
		priceCache2.setSkuId("30064111");
		priceCache2.setSalePrice("30.00");
		priceCache2.setListPrice("30.00");
		priceCache2.setMsrp("30.00");
		priceCache2.setCurrency("$");
		priceCache2.setGwpEligible("false");
		priceCache2.setIsOnClerance("false");
		priceCache2.setPriceId("10002");
		priceCache2.setPricePoint("");
		priceCache2.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		priceCache2.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		cacheList.add(priceCache2);
		Boolean isCacheCreated = priceCacheImplDAO.create(cacheList);
		assertTrue("testGetById", isCacheCreated == true);
		List<String> skuIds = new ArrayList<>();
		skuIds.add("30064111");
		skuIds.add("30064566");
		Map<String, SkuPrice> innSkus = priceCacheImplDAO.getByIdList(skuIds);
		assertTrue("testGetById Data"+innSkus, innSkus != null);
		assertTrue("testGetById Data: "+innSkus.size(), innSkus.size() > 0);
		priceCacheImplDAO.delete("30064111");
		priceCacheImplDAO.delete("30064566");
		
	}
	
	@Test
	public void testGetByIdList_Scenario1() {
	
		
		List<PriceCacheVo> cacheList = new ArrayList<>();
		PriceCacheVo priceCache1 = new PriceCacheVo();		
		priceCache1.setCacheCreatedBy("Batch Admin");
		priceCache1.setCacheCreatedOn("2018-06-20T16:35:40.102Z");
		priceCache1.setSkuId("30064566");
		priceCache1.setSalePrice("10.40");
		priceCache1.setListPrice("14.90");
		priceCache1.setMsrp("14.90");
		priceCache1.setCurrency("$");
		priceCache1.setGwpEligible("false");
		priceCache1.setIsOnClerance("false");
		priceCache1.setPomoId("Prom1232");
		priceCache1.setPomoPrice("10.40");
		priceCache1.setPriceId("10001");
		priceCache1.setPricePoint("");
		priceCache1.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		priceCache1.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
		priceCache1.setPromoMessage("Limited time 30% off!");
		priceCache1.setCacheUpdatedBy("Price Service");
		priceCache1.setCacheUpdatedOn("2018-06-20T16:35:40.102Z");
		cacheList.add(priceCache1);
		PriceCacheVo priceCache2 = new PriceCacheVo();
		priceCache2.setSkuId("30064111");
		priceCache2.setSalePrice("30.00");
		priceCache2.setListPrice("30.00");
		priceCache2.setMsrp("30.00");
		priceCache2.setCurrency("$");
		priceCache2.setGwpEligible("false");
		priceCache2.setIsOnClerance("false");
		priceCache2.setPriceId("10002");
		priceCache2.setPricePoint("");
		priceCache2.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		priceCache2.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		cacheList.add(priceCache2);
		priceCacheImplDAO.create(cacheList);
		// Find 30064566	
		SkuPrice price1 = (SkuPrice) priceCacheImplDAO.getById("30064566");
		assertTrue("Test case failed due to input provided", price1 != null);
		assertTrue("Test case failed due to input provided", price1.getListPrice() != null);
		assertTrue("Test case failed due to input provided", price1.getMsrp() != null);
		assertTrue("Test case failed due to input provided", price1.getSkuId() != null);
		assertTrue("Test case failed due to input provided", price1.getPriceEndDate() != null);
		assertTrue("Test case failed due to input provided", price1.getSalePrice() != null);
		assertTrue("Test case failed due to input provided", price1.getListPrice() != null);
		assertTrue("Test case failed due to input provided", price1.getPriceStartDate() != null);
		assertTrue("Test case failed due to input provided", price1.getSkuId() != null);

		// Delete 30064566
		Boolean deltedStatus1 = priceCacheImplDAO.delete("30064566");
		assertTrue("testGetById", deltedStatus1 == true);

		// Find 30064111
		SkuPrice price2 = priceCacheImplDAO.getById("30064111");
		assertTrue("Test case failed due to input provided", price2 != null);
		assertTrue("Test case failed due to input provided", price2.getListPrice() != null);
		assertTrue("Test case failed due to input provided", price2.getMsrp() != null);
		assertTrue("Test case failed due to input provided", price2.getSkuId() != null);
		assertTrue("Test case failed due to input provided", price2.getPriceEndDate() != null);
		assertTrue("Test case failed due to input provided", price2.getSalePrice() != null);
		assertTrue("Test case failed due to input provided", price2.getListPrice() != null);
		assertTrue("Test case failed due to input provided", price2.getPriceStartDate() != null);
		assertTrue("Test case failed due to input provided", price2.getSkuId() != null);

		// Delete 30064111
		Boolean deltedStatus2 = priceCacheImplDAO.delete("30064111");
		assertTrue("testGetById", deltedStatus2 == true);
	}

	@Test
	public void testGetById() {
		PriceCacheVo priceCache = new PriceCacheVo();		
		priceCache.setCacheCreatedBy("Batch Admin");
		priceCache.setCacheCreatedOn("2018-06-20T16:35:40.102Z");
		priceCache.setSkuId("30064566");
		priceCache.setSalePrice("10.40");
		priceCache.setListPrice("14.90");
		priceCache.setMsrp("14.90");
		priceCache.setCurrency("$");
		priceCache.setGwpEligible("false");
		priceCache.setIsOnClerance("false");
		priceCache.setPomoId("Prom1232");
		priceCache.setPomoPrice("10.40");
		priceCache.setPriceId("10001");
		priceCache.setPricePoint("");
		priceCache.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		priceCache.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		
		priceCache.setPromoMessage("Limited time 30% off!");
		priceCache.setCacheUpdatedBy("Price Service");
		priceCache.setCacheUpdatedOn("2018-06-20T16:35:40.102Z");
		 
		Boolean createdStatus = priceCacheImplDAO.create(priceCache);
		assertTrue("testGetById", createdStatus == true);
		// Find
		SkuPrice price = priceCacheImplDAO.getById("30064566");
		assertTrue("Test case failed due to input provided", price != null);
		assertTrue("Test case failed due to input provided", price.getListPrice() != null);
		assertTrue("Test case failed due to input provided", price.getMsrp() != null);
		assertTrue("Test case failed due to input provided", price.getSkuId() != null);
		assertTrue("Test case failed due to input provided", price.getPriceEndDate() != null);
		assertTrue("Test case failed due to input provided", price.getSalePrice() != null);
		assertTrue("Test case failed due to input provided", price.getListPrice() != null);
		assertTrue("Test case failed due to input provided", price.getPriceStartDate() != null);
		assertTrue("Test case failed due to input provided", price.getSkuId() != null);
		// Delete
		Boolean deltedStatus = priceCacheImplDAO.delete("30064566");
		assertTrue("testGetById", deltedStatus == true);

	}
}
