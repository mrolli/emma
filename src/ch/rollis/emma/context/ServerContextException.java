package ch.rollis.emma.context;

/**
 * Exceptions of this type are thrown by objects of package context.
 * 
 * @author mrolli
 */
@SuppressWarnings("serial")
public class ServerContextException extends Exception {
    /**
     * Single message string constructor.
     * 
     * @param msg
     *            The exception's message
     */
    public ServerContextException(final String msg) {
        super(msg);
    }

    /**
     * Single parent exception constructor.
     * 
     * @param cause
     *            The exception to rethrow with this type
     */
    public ServerContextException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructor to setup an exception consisting of a exception message and a
     * parent exception.
     * 
     * @param msg
     *            The exception's message
     * @param cause
     *            The exception to rethrow with this type
     */
    public ServerContextException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

}

