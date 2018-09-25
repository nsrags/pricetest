package com.ascena.price.exceptions;
/**
 * UnKnown Validator Exception
 * @author SMeenavalli
 *
 */
public class UnKnownValidatorException extends RuntimeException{
	private static final long serialVersionUID = -8460356990632230194L;
	/**
	 * 
	 * @param message String
	 */
	public UnKnownValidatorException(final String message) {
        super(message);
    }
	
}
