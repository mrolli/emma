package ch.rollis.emma;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

import javax.net.ssl.SSLServerSocketFactory;

import ch.rollis.emma.context.ServerContextManager;

public class SocketListenerFactory {
    private final Logger logger;
    private final ServerContextManager scm;

    public SocketListenerFactory(ServerContextManager scm, Logger logger) {
        this.scm = scm;
        this.logger = logger;
    }

    public SocketListener getListener(int port, boolean sslSecured) throws Exception {
        ServerSocket socket;

        try {
            if (sslSecured) {
                socket = SSLServerSocketFactory.getDefault().createServerSocket(port);
            } else {
                socket = new ServerSocket(port);
            }
            return new SocketListener(socket, sslSecured, scm, logger);
        } catch (IOException e) {
            throw new Exception("Unable to open server socket on port " + port + ".", e);
        }

    }
}
