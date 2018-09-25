package com.ascena.price.exceptions;

/**
 * Custom exception for Sku Price errors 
 * 
 * 
 * @author smeenavalli
 *
 */
public class SkuPrcException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorCode;
	
	/**
	 * SkuPrcException
	 * @param errorCode String
	 */
	public SkuPrcException(final String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	/**
	 * SkuPrcException
	 * @param message String
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public SkuPrcException(final String message, final Throwable cause, final String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * SkuPrcException
	 * @param message String
	 * @param errorCode String
	 */
	public SkuPrcException(final String message, final String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	/**
	 * SkuPrcException
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public SkuPrcException(final Throwable cause, final String errorCode) {
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
