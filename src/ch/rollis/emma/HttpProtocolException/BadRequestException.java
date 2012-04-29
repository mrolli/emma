package ch.rollis.emma.HttpProtocolException;

public class BadRequestException extends HttpProtocolException {
    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(Exception cause) {
        super(cause);
    }

    public BadRequestException(String msg, Exception cause) {
        super(msg, cause);
    }
}
