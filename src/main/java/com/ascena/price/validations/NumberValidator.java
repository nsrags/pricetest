package com.ascena.price.validations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ascena.price.common.constant.PriceConstants;
import com.ascena.price.config.ErrorMsgPropsConfig;
import com.ascena.price.vo.PrcServiceError;

@Component
/**
 * Number Validator
 * 
 * @author SMeenavalli
 *
 */
public class NumberValidator implements Validator {

	@Autowired
	ErrorMsgPropsConfig errorMsgPropsConfig;
	
	@Override
	public boolean isValidRequest(final Object inPutValue, final List<PrcServiceError> errors) {
		// validate for Number
		return isValidNumber(inPutValue, errors);
	}

	/**
	 * isValidNumber
	 * @param inPutValue Object
	 * @param errors List<PrcServiceError>
	 * @return boolean
	 */
	private boolean isValidNumber(final Object inPutValue,final List<PrcServiceError> errors) {
		String id = String.valueOf(inPutValue);
		StringBuilder message = null;
		message = new StringBuilder(errorMsgPropsConfig.getInvldNmbrErrorMessage());
		message.append(id);
		String pattern = PriceConstants.NUMBER_PATTERN.toString(); // Validate for Number
		if (!id.matches(pattern)) { // Not a Number
			PrcServiceError error = new PrcServiceError();
			// Get Error code and error messages from Error message properties file
			error.setErrorCode(errorMsgPropsConfig.getInvldNmbrErrorCode());
			error.setErrorDescription(message.toString());
			errors.add(error);
			return false;
		}
		return true;
	}

	@Override
	public String getValidatorName() {
		return PriceConstants.NUMBER_VALIDATOR.toString();
	}

}
