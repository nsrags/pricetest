package com.ascena.price.exceptions;

/**
 * Custom exception for Product Price BadRequest errors 
 * 
 * 
 * @author smeenavalli
 *
 */
public class ProductPrcBadRequestEx extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorCode;
	
	/**
	 * ProductPrcBadRequestEx
	 * @param errorCode String
	 */
	public ProductPrcBadRequestEx(final String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	/**
	 * ProductPrcBadRequestEx
	 * @param message String
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public ProductPrcBadRequestEx(final String message, final Throwable cause, final String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * ProductPrcBadRequestEx
	 * @param message String
	 * @param errorCode String
	 */
	public ProductPrcBadRequestEx(final String message,final String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	/**
	 * ProductPrcBadRequestEx
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public ProductPrcBadRequestEx(final Throwable cause, final String errorCode) {
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
