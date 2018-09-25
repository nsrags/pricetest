package com.ascena.price.cache.dao.transform;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;
import com.ascena.price.dao.datastore.domain.Price;
import com.ascena.price.service.cache.dao.transform.CacheRespTransformer;
import com.ascena.price.service.cache.vo.PriceCacheVo;
import com.ascena.price.vo.SkuPrice;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
/**
 * 
 * @author smeenavalli
 *
 */
public class PrcCacheRespTrnsfrmTest {

	@Autowired
	CacheRespTransformer cacheRespTrsfrmr;

	@Test
	public void testTransformToCacheObj() {
		Price prcEntity = new Price();
		prcEntity.setSkuId("30064508");
		prcEntity.setSalePrice("10.43");
		prcEntity.setListPrice("14.90");
		prcEntity.setMsrp("14.90");
		prcEntity.setCurrency("$");
		prcEntity.setGwpEligible("false");
		prcEntity.setIsOnClerance("false");		
		prcEntity.setPomoId("Prom1232");
		prcEntity.setPomoPrice("10.43");
		prcEntity.setPriceId("10001");
		prcEntity.setPricePoint("");
		prcEntity.setPriceStartDate(new Date(System.currentTimeMillis()-24*60*60*1000));
		prcEntity.setPriceEndDate(new Date(System.currentTimeMillis()+24*60*60*1000));
		prcEntity.setPromoMessage("Limited time 30% off!");		
		prcEntity.setCreatedBy("Batch Admin");
		prcEntity.setCreatedOn("2018-06-20T16:35:40.102Z");
		prcEntity.setUpdatedBy("Price Service");
		prcEntity.setUpdatedOn("2018-06-20T16:35:40.102Z");

		PriceCacheVo prcCacheVo = cacheRespTrsfrmr.transformToCacheObj(prcEntity);
		assertTrue("Test case failed due to input provided", prcCacheVo != null);
		assertNotNull("Test case failed due to input provided", prcCacheVo.getListPrice() );
		assertNotNull("Test case failed due to input provided", prcCacheVo.getMsrp() );
		assertNotNull("Test case failed due to input provided", prcCacheVo.getSalePrice() );
		assertNotNull("Test case failed due to input provided", prcCacheVo.getPomoPrice() );
		assertNotNull("Test case failed due to input provided", prcCacheVo.getPriceStartDate() );
		assertNotNull("Test case failed due to input provided", prcCacheVo.getPriceEndDate() );
		
		SkuPrice skuPrice = cacheRespTrsfrmr.transformToSku(prcCacheVo);

		assertTrue("Test case failed due to input provided", skuPrice != null);
		assertNotNull("Test case failed due to input provided", skuPrice.getListPrice() );
		assertNotNull("Test case failed due to input provided", skuPrice.getMsrp() );
		assertNotNull("Test case failed due to input provided", skuPrice.getSalePrice() );
		assertNotNull("Test case failed due to input provided", skuPrice.getPomoPrice() );
		assertNotNull("Test case failed due to input provided", skuPrice.getPriceStartDate() );
		assertNotNull("Test case failed due to input provided", skuPrice.getPriceEndDate() );
	}

	@Test
	public void transformToCacheObjList() {
		List<Price> dbEntities = new ArrayList<>();
		Price prcEntity1 = new Price();
		prcEntity1.setSkuId("30064508");
		prcEntity1.setSalePrice("10.43");
		prcEntity1.setListPrice("14.90");
		prcEntity1.setMsrp("14.90");
		prcEntity1.setCurrency("$");
		prcEntity1.setGwpEligible("false");
		prcEntity1.setIsOnClerance("false");		
		prcEntity1.setPomoId("Prom1232");
		prcEntity1.setPomoPrice("10.43");
		prcEntity1.setPriceId("10001");
		prcEntity1.setPricePoint("");
		prcEntity1.setPriceStartDate(new Date(System.currentTimeMillis()-24*60*60*1000));
		prcEntity1.setPriceEndDate(new Date(System.currentTimeMillis()+24*60*60*1000));
		prcEntity1.setPromoMessage("Limited time 30% off!");		
		prcEntity1.setCreatedBy("Batch Admin");
		prcEntity1.setCreatedOn("2018-06-20T16:35:40.102Z");
		prcEntity1.setUpdatedBy("Price Service");
		prcEntity1.setUpdatedOn("2018-06-20T16:35:40.102Z");
		dbEntities.add(prcEntity1);
		
		Price prcEntity2 = new Price();
		prcEntity2.setSkuId("30064582");
		prcEntity2.setSalePrice("34.40");
		prcEntity2.setListPrice("34.40");
		prcEntity2.setMsrp("34.40");
		prcEntity2.setCurrency("$");
		prcEntity2.setGwpEligible("false");
		prcEntity2.setIsOnClerance("false");		
		prcEntity2.setPomoId("");
		prcEntity2.setPomoPrice("");
		prcEntity2.setPriceId("10002");
		prcEntity2.setPricePoint("");
		prcEntity2.setPriceStartDate(new Date(System.currentTimeMillis()-24*60*60*1000));
		prcEntity2.setPriceEndDate(new Date(System.currentTimeMillis()+24*60*60*1000));
		prcEntity2.setPromoMessage("Limited time 30% off!");		
		prcEntity2.setCreatedBy("Batch Admin");
		prcEntity2.setCreatedOn("2018-06-20T16:35:40.102Z");
		prcEntity2.setUpdatedBy("Price Service");
		prcEntity2.setUpdatedOn("2018-06-20T16:35:40.102Z");
		dbEntities.add(prcEntity2);
		
		List<PriceCacheVo> cacheEntriesList = cacheRespTrsfrmr.transformToCacheObj(dbEntities);
		assertTrue("Test case failed due to input provided", cacheEntriesList != null);
		assertTrue("Test case failed due to input provided", cacheEntriesList.size() > 0);

		Map<String, SkuPrice> priceMap = cacheRespTrsfrmr.transformToSku(cacheEntriesList);
		assertNotNull("Test case failed due to input provided", priceMap );
		assertTrue("Test case failed due to input provided", priceMap.size() > 0);
		assertNotNull("Test case failed due to input provided", priceMap.get("30064582"));
		assertTrue("Test case failed due to input provided", priceMap.get("30064508").getSkuId().contentEquals("30064508") );
		
		
	}

}
