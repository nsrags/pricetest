package com.ascena.price.exceptions;

/**
 * Custom exception for Bulk Product Price Bad Request errors 
 * 
 * 
 * @author smeenavalli
 *
 */
public class BulkProductPrcBadRequestEx extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorCode;
	
	/**
	 * BulkProductPrcBadRequestEx
	 * @param errorCode String
	 */
	public BulkProductPrcBadRequestEx(final String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	/**
	 * BulkProductPrcBadRequestEx
	 * @param message String
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public BulkProductPrcBadRequestEx(final String message, final Throwable cause, final String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * BulkProductPrcBadRequestEx
	 * @param message String
	 * @param errorCode String
	 */
	public BulkProductPrcBadRequestEx(final String message, final String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	/**
	 * BulkProductPrcBadRequestEx
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public BulkProductPrcBadRequestEx(final Throwable cause, final String errorCode) {
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
