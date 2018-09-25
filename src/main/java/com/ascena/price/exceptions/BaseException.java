package com.ascena.price.exceptions;

/**
 * BaseException
 * @author SMeenavalli
 *
 */
public class BaseException extends RuntimeException {
    

    
	private static final long serialVersionUID = 1L;

	public BaseException() {
        super();
    }
	/**
	 * BaseException 
	 * @param cause Throwable
	 * @param format String
	 * @param formatArgs Object
	 */
    public BaseException(final Throwable cause, final String format, final Object... formatArgs) {
        super(formatArgs.length == 0 ? format : String.format(format, formatArgs), cause);
    }
    
    /**
     * BaseException
     * @param format String
     * @param formatArgs Object
     */
    public BaseException(final String format, final Object... formatArgs) {
        super(formatArgs.length == 0 ? format : String.format(format, formatArgs));
    }
    
    /**
     * BaseException
     * @param cause Throwable
     */
    public BaseException(final Throwable cause) {
        super(cause);
    }

}
