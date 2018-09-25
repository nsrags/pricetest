package com.ascena.price.validations;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;
import com.ascena.price.vo.PrcServiceError;
import com.ascena.price.vo.sku.request.PrcSkuRequestPayload;
import com.ascena.price.vo.sku.request.SkuRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
/**
 * Validator for Sku Price requests
 * 
 * @author smeenavalli
 *
 */
public class PriceSkuValidatorTest {

	@Autowired
	SkuPrcValidator skuPrcValidator;

	@Test
	public void validateRequest() {

		List<PrcServiceError> errors = new ArrayList<>();		
		skuPrcValidator.validateRequest("sku-12207710", errors);
		assertTrue("Test case failed due to input provided", errors.size() > 0);
	}

	@Test
	public void validateSkuId() {

		List<PrcServiceError> errors = new ArrayList<>();
		skuPrcValidator.validateRequest("12207710", errors);
		assertTrue("Test case failed due to input provided", errors.size() == 0);
	}

	@Test
	public void validateSkuIdWithSpaces() {

		List<PrcServiceError> errors = new ArrayList<>();
		skuPrcValidator.validateRequest(" 12207710 ", errors);
		assertTrue("Test case failed due to input provided", errors.size() > 0);
	}

	@Test
	/**
	 * validate Sku Bulk Request For Sku id validations
	 */
	public void validateSkuBulkRequestForNumber() {
		PrcSkuRequestPayload prcRequest = new PrcSkuRequestPayload();
		List<PrcServiceError> payLoadErrors = new ArrayList<>();
		Map<String, List<PrcServiceError>> skuErrorsMap = new HashMap<>();
		SkuRequest sku1 = new SkuRequest();
		sku1.setSkuId("sku-12207710");
		SkuRequest sku2 = new SkuRequest();
		sku2.setSkuId("12207723");

		SkuRequest sku3 = new SkuRequest();
		sku3.setSkuId("  12207765");

		SkuRequest sku4 = new SkuRequest();
		sku4.setSkuId("12207752");

		List<SkuRequest> requestList = new ArrayList<>();
		requestList.add(sku1);
		requestList.add(sku2);
		requestList.add(sku3);
		requestList.add(sku4);
		prcRequest.setPriceSkuRequest(requestList);
		skuPrcValidator.validateBulkRequest(prcRequest, payLoadErrors, skuErrorsMap, "2");
		assertTrue("Test case failed due to input provided", skuErrorsMap.size() > 0);
	}

	@Test
	/**
	 * Validate Bulk SKu request
	 */
	public void validateSkuBulkRequestforNumber() {
		PrcSkuRequestPayload prcRequest = new PrcSkuRequestPayload();
		List<PrcServiceError> payLoadErrors = new ArrayList<>();
		Map<String, List<PrcServiceError>> skuErrorsMap = new HashMap<>();
		SkuRequest sku1 = new SkuRequest();
		sku1.setSkuId("12207710");
		SkuRequest sku2 = new SkuRequest();
		sku2.setSkuId("12207723");
		SkuRequest sku3 = new SkuRequest();
		sku3.setSkuId("12207765");
		SkuRequest sku4 = new SkuRequest();
		sku4.setSkuId("12207752");

		List<SkuRequest> requestList = new ArrayList<>();
		requestList.add(sku1);
		requestList.add(sku2);
		requestList.add(sku3);
		requestList.add(sku4);

		prcRequest.setPriceSkuRequest(requestList);
		// No Sku ;evel errors but errors exists at Payload
		skuPrcValidator.validateBulkRequest(prcRequest, payLoadErrors, skuErrorsMap, "2");
		assertTrue("Test case failed due to input provided", skuErrorsMap.size() == 0);
		assertTrue("Test case failed due to input provided", payLoadErrors.size() > 0);
		skuErrorsMap = new HashMap<>();
		payLoadErrors = new ArrayList<>();// No errors
		skuPrcValidator.validateBulkRequest(prcRequest, payLoadErrors, skuErrorsMap, "4");
		assertTrue("Test case failed due to input provided", skuErrorsMap.size() == 0);
		assertTrue("Test case failed due to input provided", payLoadErrors.size() == 0);

	}
}
