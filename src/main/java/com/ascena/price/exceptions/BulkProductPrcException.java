package com.ascena.price.exceptions;

/**
 * Custom exception for Bulk  Product Price errors 
 * 
 * 
 * @author smeenavalli
 *
 */
public class BulkProductPrcException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorCode;
	
	/**
	 * BulkProductPrcException
	 * @param errorCode String
	 */
	public BulkProductPrcException(final String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	/**
	 * BulkProductPrcException
	 * @param message String
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public BulkProductPrcException(final String message, final Throwable cause, final String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * BulkProductPrcException
	 * @param message String
	 * @param errorCode String
	 */
	public BulkProductPrcException(final String message, final String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	/**
	 * BulkProductPrcException
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public BulkProductPrcException(final Throwable cause, final String errorCode) {
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
