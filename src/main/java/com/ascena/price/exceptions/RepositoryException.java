
package com.ascena.price.exceptions;

/**
 * RepositoryException 
 * @author SMeenavalli
 *
 */
public class RepositoryException extends BaseException {
    private static final long serialVersionUID = 1L;
    
    /**
     * RepositoryException
     * @param format
     * @param formatArgs
     */
    public RepositoryException(final String format, final Object... formatArgs) {
        super(format, formatArgs);
    }

    /**
     * 	RepositoryException
     * @param cause
     * @param format
     * @param formatArgs
     */
    public RepositoryException(final Throwable cause, final String format, final Object... formatArgs) {
        super(cause, format, formatArgs);
    }

}
