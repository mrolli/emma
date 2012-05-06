package ch.rollis.emma.context;

@SuppressWarnings("serial")
public class ServerContextException extends Exception {
    public ServerContextException(String message) {
        super(message);
    }

    public ServerContextException(Throwable cause) {
        super(cause);
    }

    public ServerContextException(String message, Throwable cause) {
        super(message, cause);
    }

}
