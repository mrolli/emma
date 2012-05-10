package ch.rollis.emma;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.rollis.emma.context.ServerContextManager;

/**
 * SocketListener objects listen on a server socket for incoming client
 * requests.
 * 
 * @author mrolli
 */
public class SocketListener implements Runnable {
    private final ServerSocket serverSocket;
    private final int port;
    private final boolean sslSecured;
    private final Logger logger;
    private final ServerContextManager scm;

    public SocketListener(ServerSocket socket, boolean sslSecured, ServerContextManager scm,
            Logger logger) {
        // move web server configuration to file
        this.serverSocket = socket;
        this.port = socket.getLocalPort();
        this.sslSecured = sslSecured;
        this.scm = scm;
        this.logger = logger;
    }

    @Override
    public void run() {
        // create a thread group for all request handling threads
        ThreadGroup handlers = new ThreadGroup("HTTP Request handlers on port " + port);

        logger.log(Level.INFO, "Now listening on port " + port);
        while (!Thread.currentThread().isInterrupted()) {
            Socket comSocket;
            try {
                // setup a socket timeout to be able to react on interrupt()
                serverSocket.setSoTimeout(1000);
                comSocket = serverSocket.accept();
                Thread th = new Thread(handlers, new RequestHandler(comSocket, sslSecured, logger,
                        scm));
                th.setName("Request thread " + th.getId());
                th.start();
            } catch (SocketTimeoutException e) {
                // do nothing, just give the while loop a chance to exit on
                // interrupt
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error proccessing client request.", e);
            }
        }

        // thread was interrupted, cleanup
        try {
            Thread[] threads = new Thread[handlers.activeCount()];
            handlers.enumerate(threads);
            for (Thread t : threads) {
                t.interrupt();
            }

            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    // no action needed here
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error closing server socket.", e);
        }
        logger.log(Level.INFO, "Stopped listening on port " + port);

    }
}
