package com.ascena.price.validations;

import java.util.List;

import com.ascena.price.vo.PrcServiceError;

/**
 * Validator interface
 * @author smeenavalli
 *
 */
public interface Validator  {
	
	/**
	 * isValidRequest
	 * @param inPutValue
	 * @param errors - List<PrcServiceError>
	 * @return boolean
	 */
	public boolean isValidRequest(Object inPutValue,List<PrcServiceError> errors);

	/**
	 * get Validator Name 
	 * @return String
	 */
	public String getValidatorName();
	
	
}
