package com.ascena.price.validations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ascena.price.common.constant.PriceConstants;
import com.ascena.price.config.ErrorMsgPropsConfig;
import com.ascena.price.vo.PrcServiceError;

@Component
/**
 * Date Validator
 * 
 * @author SMeenavalli
 *
 */
public class DateValidator implements Validator {

	@Autowired
	ErrorMsgPropsConfig errorMsgPropsConfig;

	@Override
	public boolean isValidRequest(final Object inPutValue, final List<PrcServiceError> errors) {
		// validate for Number
		return isValidDate(inPutValue, errors);
	}

	/**
	 * isValidNumber
	 * 
	 * @param inPutValue
	 *            Object
	 * @param errors
	 *            List<PrcServiceError>
	 * @return boolean
	 */
	private boolean isValidDate(final Object inPutDate, final List<PrcServiceError> errors) {
		String pattern = PriceConstants.DATE_FORMAT.toString();
		String inputDateStr = (String)inPutDate;
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		boolean isFormatValid = true;
		try {
			Date date = dateFormat.parse((String) inPutDate);
			if (!inputDateStr.equals(dateFormat.format(date))) {
				isFormatValid = false;
            }
			
		} catch (ParseException pe) {
			isFormatValid = false;
			

		}
		if(!isFormatValid) {
			//Date parsing errors
			StringBuilder message = null;
			message = new StringBuilder(errorMsgPropsConfig.getInvldDateErrorMessage());
			message.append(inPutDate);
			PrcServiceError error = new PrcServiceError();
			// Get Error code and error messages from Error message properties file
			error.setErrorCode(errorMsgPropsConfig.getInvldDateErrorCode());
			error.setErrorDescription(message.toString());
			errors.add(error);
			
		}
		return isFormatValid;
	}
	
	@Override
	public String getValidatorName() {
		return PriceConstants.DATE_VALIDATOR.toString();
	}

}
