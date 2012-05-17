package ch.rollis.emma.util;

/**
 * Exception type thrown by DateConverter class.
 * 
 * @author mrolli
 */
@SuppressWarnings("serial")
public class DateConverterException extends Exception {
    /**
     * Single message string constructor.
     * 
     * @param msg
     *            The exception's message
     */
    public DateConverterException(final String msg) {
        super(msg);
    }
}
