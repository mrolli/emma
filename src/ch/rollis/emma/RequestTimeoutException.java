package ch.rollis.emma;

/**
 * Exception type thrown if request timeout is encountered.
 * 
 * @author mrolli
 */
@SuppressWarnings("serial")
public class RequestTimeoutException extends Exception {
    /**
     * Single parent exception constructor.
     * 
     * @param cause
     *            The exception to rethrown with this type
     */
    public RequestTimeoutException(final Exception cause) {
        super(cause);

    }
}
