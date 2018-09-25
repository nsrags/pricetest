package com.ascena.price.conf;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;
import com.ascena.price.config.ErrorMsgPropsConfig;

/**
 * 
 * @author smeenavalli
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
public class ErrorMsgPropsConfTest {
	
	@Autowired 
	ErrorMsgPropsConfig errorMsgPropConf;
	
	@Test
	public void testErrorMsgPropsConfig() {
		
		errorMsgPropConf.setInvldNmbrErrorCode("PRC-INVALID-INPUT-ERR");
		errorMsgPropConf.setInvldNmbrErrorMessage("Invalid Input - Provided input is not a Number  :");
		errorMsgPropConf.setSkuPrcMissingErrorCode("PRC-NOT-EXISTSS-SKU");
		errorMsgPropConf.setSkuPrcMissingErrorMessage("Price is not existing for SKU :");
		errorMsgPropConf.setPrdPrcMissingErrorCode("PRC-NOT-EXISTS-PRD");
		errorMsgPropConf.setPrdPrcMissingErrorMessage("Price is not existing for Product :");
		errorMsgPropConf.setSkuBulkExcdErrorCode("BULK-SKU-PRC-INVALID-INPUT-ERR");
		errorMsgPropConf.setSkuBulkExcdErrorMessage("Sku Id list in  Price request pay load  exceeded maximum limit :");
		errorMsgPropConf.setPrdBulkExcdErrorCode("BULK-SKU-PRC-INVALID-PAYLD-ERR");
		errorMsgPropConf.setPrdBulkExcdErrorMessage("Product Id list in  Price request pay load  exceeded maximum limit :");
		errorMsgPropConf.setSkuBulkInvldInputErrorCode("BULK-SKU-PRC-INVALID-PAYLD-ERRR");
		errorMsgPropConf.setSkuBulkInvldInputErrorMsg("Invalid Bulk Sku  Price Request Payload : ");
		errorMsgPropConf.setPrdBulkInvldInputErrorCode("BULK-PRD-INV-INVALID-PAYLD-ERR");
		errorMsgPropConf.setPrdBulkInvldInputErrorMsg("Prcalid Bulk Product Price Request Payload :");
		errorMsgPropConf.setNoRecFoundInDbErrorCode("PRC-NOT-EXISTSS-DATASTORE");
		errorMsgPropConf.setNoRecFoundInDbErrorMessage("Price not found in Google Data Store for  :");
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getInvldNmbrErrorCode() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getInvldNmbrErrorMessage() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getSkuPrcMissingErrorCode() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getSkuPrcMissingErrorMessage() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getPrdPrcMissingErrorCode() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getPrdPrcMissingErrorMessage() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getSkuBulkExcdErrorCode() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getSkuBulkExcdErrorMessage() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getPrdBulkExcdErrorCode() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getPrdBulkExcdErrorMessage() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getNoRecFoundInDbErrorCode() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getNoRecFoundInDbErrorMessage() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getSkuBulkInvldInputErrorCode() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getSkuBulkInvldInputErrorMsg() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getPrdBulkInvldInputErrorCode() != null);
		assertTrue("testErrorMsgPropsConfig", errorMsgPropConf.getPrdBulkInvldInputErrorMsg() != null);
		
	}
}
