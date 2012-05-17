package ch.rollis.emma.request;

/**
 * Exception class for HTTP protocol exceptions.
 *
 * Exceptions of this type are thrown by all exceptions raised when parsing a
 * request and an illegal request is found.
 *
 * @author mrolli
 */
@SuppressWarnings("serial")
public class HttpProtocolException extends Exception {
    /**
     * Single message string constructor.
     *
     * @param msg
     *            The exception's message
     */
    public HttpProtocolException(final String msg) {
        super(msg);
    }

    /**
     * Single parent exception constructor.
     * 
     * @param cause
     *            The exception to rethrown with this type
     */
    public HttpProtocolException(final Exception cause) {
        super(cause);
    }

    /**
     * Constructor to setup an exception consisting of a exception
     * message and a parent exception.
     *
     * @param msg
     *            The exception's message
     * @param cause
     *            The exception to rethrow with this type
     */
    public HttpProtocolException(final String msg, final Exception cause) {
        super(msg, cause);
    }
}
