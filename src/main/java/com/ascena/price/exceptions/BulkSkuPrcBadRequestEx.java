package com.ascena.price.exceptions;

/**
 * Custom exception for bulk Sku Price Bad Request errors 
 * 
 * 
 * @author smeenavalli
 *
 */
public class BulkSkuPrcBadRequestEx extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorCode;
	
	/**
	 * BulkSkuPrcBadRequestEx
	 * @param errorCode String
	 */
	public BulkSkuPrcBadRequestEx(final String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	/**
	 * BulkSkuPrcBadRequestEx
	 * @param message String
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public BulkSkuPrcBadRequestEx(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * BulkSkuPrcBadRequestEx
	 * @param message String
	 * @param errorCode String
	 */
	public BulkSkuPrcBadRequestEx(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	/**
	 * BulkSkuPrcBadRequestEx
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public BulkSkuPrcBadRequestEx(Throwable cause, String errorCode) {
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
