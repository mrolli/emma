package ch.rollis.emma;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

import javax.net.ssl.SSLServerSocketFactory;

import ch.rollis.emma.context.ServerContextManager;

/**
 * Creates port based server socket listeners.
 * <p>
 * The socket listeners are created based on the port the socket shall listen on
 * and accept connection and a flag to indicate if the communication shall be
 * SSL secured on that port.
 * Additional dependencies of the connection listeners are to be provided by the
 * class constructor of this factory.
 * 
 * @author mrolli
 */
public class SocketListenerFactory {
    /**
     * Global logger instance the listeners should log to.
     */
    private final Logger loggerInstance;

    /**
     * Instance of ServerContextManager the listeners are dependent on.
     */
    private final ServerContextManager scmInstance;

    /**
     * Class constructor.
     * 
     * @param scm
     *              The server context manager instance to inject into listeners.
     * 
     * @param logger
     *              The logger instance to inject into listeners.
     */
    public SocketListenerFactory(final ServerContextManager scm, final Logger logger) {
        scmInstance = scm;
        loggerInstance = logger;
    }

    /**
     * Factory method that generates socket listeners that listens on a server
     * socket on specified TCP port and is optionally SSL secured.
     * <p>
     * 
     * @param port
     *            Integer denoting the port to create a server socket on
     * @param sslSecured
     *            Boolean flag if generated socket shall be SSL secured
     * @return The created socket listener
     * @throws Exception
     *             If creation of a server socket fails.
     */
    public SocketListener getListener(final int port, final boolean sslSecured) throws Exception {
        ServerSocket socket;

        try {
            if (sslSecured) {
                socket = SSLServerSocketFactory.getDefault().createServerSocket(port);
            } else {
                socket = new ServerSocket(port);
            }
            return new SocketListener(socket, sslSecured, scmInstance, loggerInstance);
        } catch (IOException e) {
            throw new Exception("Unable to open server socket on port " + port + ".", e);
        }

    }
}
