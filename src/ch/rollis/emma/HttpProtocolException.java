package ch.rollis.emma;

@SuppressWarnings("serial")
public class HttpProtocolException extends Exception {
    public HttpProtocolException(String msg) {
        super(msg);
    }

    public HttpProtocolException(Exception cause) {
        super(cause);
    }

    public HttpProtocolException(String msg, Exception cause) {
        super(msg, cause);
    }
}
