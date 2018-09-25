package com.ascena.price.exceptions;

/**
 * Custom exception for Bulk Sku Price errors 
 * 
 * 
 * @author smeenavalli
 *
 */
public class BulkSkuPrcException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorCode;
	
	/**
	 * BulkSkuPrcException
	 * @param errorCode
	 */
	public BulkSkuPrcException(final String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	/**
	 * BulkSkuPrcException
	 * @param message String
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public BulkSkuPrcException(final String message, final Throwable cause, final String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * BulkSkuPrcException
	 * @param message String
	 * @param errorCode String
	 */
	public BulkSkuPrcException(final String message, final String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	/**
	 * BulkSkuPrcException
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public BulkSkuPrcException(final Throwable cause, final String errorCode) {
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
