package ch.rollis.emma;

/**
 * General unspecific exception class.
 * 
 * @author mrolli
 */
@SuppressWarnings("serial")
public class HttpServerException extends Exception {
    public HttpServerException(String msg) {
        super(msg);
    }

    public HttpServerException(Exception cause) {
        super(cause);
    }

    public HttpServerException(String msg, Exception cause) {
        super(msg, cause);
    }
}
