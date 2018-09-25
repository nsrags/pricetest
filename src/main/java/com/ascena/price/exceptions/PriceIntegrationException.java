package com.ascena.price.exceptions;

/**
 * Custom exception for Integration errors 
 * 
 * 
 * @author smeenavalli
 *
 */
public class PriceIntegrationException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorCode;
	
	/**
	 * PriceIntegrationException
	 * @param errorCode String
	 */
	public PriceIntegrationException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	/**
	 * PriceIntegrationException
	 * @param message String
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public PriceIntegrationException(final String message, final Throwable cause, final String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * PriceIntegrationException
	 * @param message String
	 * @param errorCode String
	 */
	public PriceIntegrationException(final String message, final String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	/**
	 * PriceIntegrationException
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public PriceIntegrationException(final Throwable cause, final String errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * getCode
	 * @return String
	 */
	public String getCode() {
		return this.errorCode;
	}
}
