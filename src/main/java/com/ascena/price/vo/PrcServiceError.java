
package com.ascena.price.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "errorCode", "errorDescription" })
/**
 * Json with jackson annotations
 * 
 * @author smeenavalli
 *
 */
public class PrcServiceError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * error Code
	 */
	@JsonProperty("errorCode")
	private String errorCode;
	/**
	 * error Description
	 */
	@JsonProperty("errorDescription")
	private String errorDescription;

	/**
	 * get ErrorCode
	 * 
	 * @return String
	 */
	@JsonProperty("errorCode")
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * set ErrorCode
	 * 
	 * @param errorCode
	 *            String
	 */
	@JsonProperty("errorCode")
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * getErrorDescription
	 * 
	 * @return String
	 */
	@JsonProperty("errorDescription")
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * set ErrorDescription
	 * 
	 * @param errorDescription
	 *            String
	 */
	@JsonProperty("errorDescription")
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	@Override
	public String toString() {
		return "PrcServiceError{" + "errorCode='" + errorCode + '\'' + "errorDescription='" + errorDescription + '\''
				+ '}';
	}
}
