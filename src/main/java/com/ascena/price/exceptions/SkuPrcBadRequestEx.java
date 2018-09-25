package com.ascena.price.exceptions;

/**
 * Custom exception for Sku Price BadRequest errors 
 * 
 * 
 * @author smeenavalli
 *
 */
public class SkuPrcBadRequestEx extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorCode;
	
	/**
	 * SkuPrcBadRequestEx
	 * @param errorCode String
	 */
	public SkuPrcBadRequestEx(final String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	/**
	 * SkuPrcBadRequestEx
	 * @param message String
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public SkuPrcBadRequestEx(final String message, final Throwable cause, final String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * SkuPrcBadRequestEx
	 * @param message String
	 * @param errorCode String
	 */
	public SkuPrcBadRequestEx(final String message, final String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	/**
	 * SkuPrcBadRequestEx
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public SkuPrcBadRequestEx(final Throwable cause, final String errorCode) {
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
