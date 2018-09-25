package com.ascena.price.validations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ascena.price.exceptions.UnKnownValidatorException;

/**
 * Factory of Validators 
 * @author smeenavalli
 *
 */
@Component
/**
 * 	Factory of Validators
 * @author SMeenavalli
 *
 */
public class ValidatorsFactory {

	@Autowired
	private List<Validator> validators;

	private static final Map<String, Validator> validatorsRegistry = new HashMap<>();

	/**
	 * initialize Validator Registry
	 */
	@PostConstruct
	public void initValidatorRegistry() {
		validators.forEach(validator -> validatorsRegistry.put(
				validator.getValidatorName(), 
				validator));

	}

	/**
	 * Gives validator by Name
	 * @param validatorName
	 * @return Validator
	 */
	public static Validator getValidator(final String validatorName) {
		Validator validator = validatorsRegistry.get(validatorName);
		if (validator == null) {
			throw new UnKnownValidatorException(
					"Unknown Validator type: " + validatorName + "\t"+ "validatorsRegistry" + validatorsRegistry);
		}
		return validator;
	}
}
