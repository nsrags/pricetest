package com.ascena.price.exceptions;

/**
 * Custom exception for Database failures etc
 * 
 * @author smeenavalli
 *
 */
public class SysException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorCode;
	
	/**
	 * SysException
	 * @param errorCode String
	 */
	public SysException(final String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	/**
	 * SysException
	 * @param message String
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public SysException(final String message,final Throwable cause, final String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * SysException 
	 * @param message String
	 * @param errorCode String
	 */
	public SysException(final String message, final String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	/**
	 * SysException
	 * @param cause Throwable
	 * @param errorCode String
	 */
	public SysException(final Throwable cause, final String errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return this.errorCode;
	}
}
