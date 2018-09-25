package com.ascena.price.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;
import com.ascena.price.dao.datastore.domain.Price;
import com.ascena.price.exceptions.SysException;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
public class PriceTestDAO {

	private Closeable session;

	@Autowired
	PriceDAO<Price> priceDAO;

	@Before
	public void init() {
		session = ObjectifyService.begin();
	}

	@Test
	public void testGetById() throws SysException {
		try {
			Price price1 = (Price) priceDAO.getById("30064566");
			assertTrue("Test case failed due to input provided", price1 != null);
			assertTrue("Test case failed due to input provided", price1.getListPrice() != null);
			assertTrue("Test case failed due to input provided", price1.getMsrp() != null);
			assertTrue("Test case failed due to input provided", price1.getSkuId() != null);
			assertTrue("Test case failed due to input provided", price1.getPriceEndDate() != null);
			assertTrue("Test case failed due to input provided", price1.getSalePrice() != null);
			assertTrue("Test case failed due to input provided", price1.getListPrice() != null);
			assertTrue("Test case failed due to input provided", price1.getPriceStartDate() != null);
			assertTrue("Test case failed due to input provided", price1.getSkuId() != null);
			assertTrue("Test case failed due to input provided", price1.getCreatedBy() != null);
			assertTrue("Test case failed due to input provided", price1.getCreatedOn() != null);
			assertTrue("Test case failed due to input provided", price1.getUpdatedBy() != null);
			assertTrue("Test case failed due to input provided", price1.getUpdatedOn() != null);

			Price price2 = (Price) priceDAO.getByPrcForSkuId("30064566",new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
			assertTrue("Test case failed due to input provided", price2 != null);
			assertTrue("Test case failed due to input provided", price2.getListPrice() != null);
			assertTrue("Test case failed due to input provided", price2.getMsrp() != null);
			assertTrue("Test case failed due to input provided", price2.getSkuId() != null);
			assertTrue("Test case failed due to input provided", price2.getPriceEndDate() != null);
			assertTrue("Test case failed due to input provided", price2.getSalePrice() != null);
			assertTrue("Test case failed due to input provided", price2.getListPrice() != null);
			assertTrue("Test case failed due to input provided", price2.getPriceStartDate() != null);
			assertTrue("Test case failed due to input provided", price2.getSkuId() != null);
			assertTrue("Test case failed due to input provided", price2.getCreatedBy() != null);
			assertTrue("Test case failed due to input provided", price2.getCreatedOn() != null);
			assertTrue("Test case failed due to input provided", price2.getUpdatedBy() != null);
			assertTrue("Test case failed due to input provided", price2.getUpdatedOn() != null);
		} catch (SysException se) {
			throw se;
		}
	}

	// @Test
	public void testDataStoreInvalidId() throws SysException {
		try {

			priceDAO = new PriceDAO<Price>();
			Price price = (Price) priceDAO.getById("30064583");
			assertTrue("Test case failed due to input provided", price == null);

		} catch (SysException se) {
			throw se;
		}
	}

	// @Test
	public void testGetByIds() throws SysException {
		List<String> idsList = new ArrayList<>();
		idsList.add("30064566");
		idsList.add("30064582");
		priceDAO = new PriceDAO<Price>();
		List<Price> prices = (List<Price>) priceDAO.getById(idsList);

		assertTrue("Test case failed due to input provided", prices != null);
		assertTrue("Test case failed due to input provided", prices.size() > 0);

	}

	// @Test
	public void testGetByIdsNotFound() throws SysException {
		
			List<String> idsList = new ArrayList<>();
			idsList.add("30064511");
			idsList.add("30064512");
			priceDAO = new PriceDAO<Price>();
			List<Price> prices = (List<Price>) priceDAO.getById(idsList);
			assertFalse("Test case failed due to input provided", prices.get(0) != null);
			assertFalse("Test case failed due to input provided", prices.get(1) != null);
		
	}

	@After
	public void destroy() {
		session.close();
	}
}
