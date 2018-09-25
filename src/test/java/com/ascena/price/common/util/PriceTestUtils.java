package com.ascena.price.common.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
/**
 * 
 * @author smeenavalli
 *
 */
public class PriceTestUtils {

	@Autowired
	PriceUtils priceUtils;

	@Test
	public void testCacheKeyGeneration() {

		String cacheKey1 = priceUtils.createCacheKey("30064508");
		assertTrue("Test case failed due to input provided", cacheKey1 != null);
		assertTrue("Test case failed due to input provided Cache Key1: " + cacheKey1,
				cacheKey1.contentEquals("30064508"));

		priceUtils.getAppConfig().setIsCachePrefixEnabled("true");
		String cacheKey2 = priceUtils.createCacheKey("30064508");

		assertTrue("Test case failed due to input provided Cache Key: " + cacheKey2,
				cacheKey2.contentEquals("Prc:30064508"));
	}

}
