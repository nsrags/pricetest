package com.ascena.price.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;
import com.ascena.price.exceptions.PriceIntegrationException;
import com.ascena.price.exceptions.SysException;
import com.ascena.price.vo.SkuPrice;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)

public class PriceServiceTest {
	
	private static Logger logger = LoggerFactory.getLogger(PriceServiceTest.class);
	
	@Autowired
	PriceService priceService;

	private Closeable session;

	@Before
	public void init() {
		session = ObjectifyService.begin();
	}

	@Test
	public void testGetPriceBySku()  {
		SkuPrice skuPrice;
		try {
			skuPrice = priceService.getPriceBySku("10201530");
			assertNotNull("SkuPrice details are not available for 10201530: ", skuPrice);
			assertNull("SkuPrice details are not available for 10201530 : ", skuPrice.getCurrency());
			assertNull("SkuPrice details are not available for 10201530 : ", skuPrice.getListPrice());
			assertNull("SkuPrice details are not available for 10201530 : ", skuPrice.getPriceStartDate());
			assertNull("SkuPrice details are not available for 10201530 : ", skuPrice.getPriceEndDate());
			assertNull("SkuPrice details are not available for 10201530 : ", skuPrice.getSalePrice());
			assertNull("SkuPrice details are not available for 10201530 : ", skuPrice.getSkuId());
			assertNull("SkuPrice details are not available for 10201530 : ", skuPrice.getMsrp());
			assertNotNull("SkuPrice details are not available for 10201530 : ", skuPrice.getErrors());
		} catch (SysException se) {
			if (logger.isErrorEnabled()) {
				logger.error(String.format("Server Failures on Data store connectivity %s ", se.getMessage()));
			}
			
		}
		
	}

	@Test
	public void testGetPriceBySkus() throws SysException {
		List<String> skuIds = new ArrayList<>();
		skuIds.add("10201530");
		skuIds.add("10206564");

		Map<String, SkuPrice> skuPrices = priceService.getPrice(skuIds);
		assertNotNull("SkuPrice details are not available for : ", skuPrices);
		assertTrue("SkuPrice details are not available for 10201530 : ", skuPrices.size() == 0);
		
	}

	@Test(expected=Exception.class)
	public void testGetInventoryByProduct() throws SysException, PriceIntegrationException {
		Map<String, SkuPrice> skuPrices = priceService.getPriceByProduct("121111");
		assertNull("SkuPrice details are not available for 121111: ", skuPrices);
	}
	@After
	public void destroy() {
		session.close();
	}

}
