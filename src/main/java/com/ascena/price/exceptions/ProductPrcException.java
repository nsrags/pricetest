package com.ascena.price.exceptions;

/**
 * Custom exception for Price errors at Product level 
 * 
 * 
 * @author smeenavalli
 *
 */
public class ProductPrcException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorCode;
	
	/**
	 * ProductPrcException
	 * @param errorCode String
	 */
	public ProductPrcException(final String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	/**
	 * ProductPrcException
	 * @param message String
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public ProductPrcException(final String message, final Throwable cause, final String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * ProductPrcException
	 * @param message String
	 * @param errorCode String
	 */
	public ProductPrcException(final String message, final String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	/**
	 * ProductPrcException
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public ProductPrcException(final Throwable cause, final String errorCode) {
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
