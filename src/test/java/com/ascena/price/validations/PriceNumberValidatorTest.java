package com.ascena.price.validations;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;
import com.ascena.price.vo.PrcServiceError;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)

/**
 * Validator for Number
 * @author smeenavalli
 *
 */
public class PriceNumberValidatorTest {

	@Autowired
	NumberValidator numberValidator;
	
	
	@Test
	public void validateNonNumber() {
	 
		List<PrcServiceError> errors = new ArrayList<>();
		numberValidator.isValidRequest("prd-6862291", errors);
		assertTrue("Test case failed due to input provided",errors.size()>0);
	}
	
	@Test
	public void validateProductId() {
	 
		List<PrcServiceError> errors = new ArrayList<>();
		numberValidator.isValidRequest("6862291", errors);
		assertTrue("Test case failed due to input provided",errors.size() == 0);
	}
	@Test
	public void validateProductIdwithSpaces() {
	 
		List<PrcServiceError> errors = new ArrayList<>();
		numberValidator.isValidRequest("6862291 ", errors);
		assertTrue("Test case failed due to input provided",errors.size() > 0);
	}
	@Test
	public void validateSkuId() {
	 
		List<PrcServiceError> errors = new ArrayList<>();
		numberValidator.isValidRequest("12207710", errors);
		assertTrue("Test case failed due to input provided",errors.size() == 0);
	}
}
