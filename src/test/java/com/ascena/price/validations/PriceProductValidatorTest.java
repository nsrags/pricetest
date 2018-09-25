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
import com.ascena.price.vo.product.request.PrcProductRequestPayload;
import com.ascena.price.vo.product.request.ProductRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
/**
 * Validator for Product Price requests
 * 
 * @author smeenavalli
 *
 */
public class PriceProductValidatorTest {

	@Autowired
	ProductPrcValidator productPrcValidator;

	@Test
	public void validateProductIdForNonNumber() {

		List<PrcServiceError> errors = new ArrayList<>();
		productPrcValidator.validateRequest("prd-6862291", errors);
		assertTrue("Test case failed due to input provided", errors.size() > 0);
	}

	@Test
	public void validateProductIdForNumber() {

		List<PrcServiceError> errors = new ArrayList<>();
		productPrcValidator.validateRequest("6862291", errors);
		assertTrue("Test case failed due to input provided", errors.size() == 0);
	}

	@Test
	public void validateProductIdwithSpaces() {

		List<PrcServiceError> errors = new ArrayList<>();
		productPrcValidator.validateRequest(" 6862291 ", errors);
		assertTrue("Test case failed due to input provided", errors.size() > 0);
	}

	// @Test
	/**
	 * validate Bulk Request For Sku id validations
	 */
	public void validateBulkRequestForNumber() {
		PrcProductRequestPayload prcRequest = new PrcProductRequestPayload();
		List<PrcServiceError> payLoadErrors = new ArrayList<>();
		Map<String, List<PrcServiceError>> skuErrorsMap = new HashMap<>();
		List<ProductRequest> requestList = new ArrayList<>();

		ProductRequest product1 = new ProductRequest();
		product1.setProductId("prd-2420077");
		ProductRequest product2 = new ProductRequest();
		product2.setProductId("6862291");
		ProductRequest product3 = new ProductRequest();
		product3.setProductId("  6862292 ");
		ProductRequest product4 = new ProductRequest();
		product4.setProductId("6862287");
		requestList.add(product1);
		requestList.add(product2);
		requestList.add(product3);
		requestList.add(product4);

		prcRequest.serPrcProductRequestPayload(requestList);

		productPrcValidator.validateBulkRequest(prcRequest, payLoadErrors, skuErrorsMap, "2");
		assertTrue("Test case failed due to input provided", skuErrorsMap.size() > 0);
	}

	@Test
	/**
	 * Validate Bulk SKu request
	 */
	public void validateBulkRequestforNumber() {
		PrcProductRequestPayload prcRequest = new PrcProductRequestPayload();
		List<PrcServiceError> payLoadErrors = new ArrayList<>();
		Map<String, List<PrcServiceError>> skuErrorsMap = new HashMap<>();
		ProductRequest product1 = new ProductRequest();
		product1.setProductId("6862287");
		ProductRequest product2 = new ProductRequest();
		product2.setProductId("6862291");
		ProductRequest product3 = new ProductRequest();
		product3.setProductId("2420077");
		ProductRequest product4 = new ProductRequest();
		product4.setProductId("2911752");

		List<ProductRequest> requestList = new ArrayList<>();
		requestList.add(product1);
		requestList.add(product2);
		requestList.add(product3);
		requestList.add(product4);

		prcRequest.serPrcProductRequestPayload(requestList);
		// No Sku ;evel errors but errors exists at Payload
		productPrcValidator.validateBulkRequest(prcRequest, payLoadErrors, skuErrorsMap, "2");
		assertTrue("Test case failed due to input provided", skuErrorsMap.size() == 0);
		assertTrue("Test case failed due to input provided", payLoadErrors.size() > 0);
		skuErrorsMap = new HashMap<>();
		payLoadErrors = new ArrayList<>();// No errors
		productPrcValidator.validateBulkRequest(prcRequest, payLoadErrors, skuErrorsMap, "4");
		assertTrue("Test case failed due to input provided", skuErrorsMap.size() == 0);
		assertTrue("Test case failed due to input provided", payLoadErrors.size() == 0);

	}
}
