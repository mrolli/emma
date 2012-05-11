package ch.rollis.emma;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.rollis.emma.context.ServerContextManager;

/**
 * SocketListener threads listen on a server socket for incoming client
 * requests.
 * <p>
 * After a connection has been established by a client control is dispatched to
 * a newly spawned request handler thread.
 * <p>
 * A socket timeout is set to be able to react on thread interruption.
 * 
 * @author mrolli
 */
public class SocketListener implements Runnable {
    /**
     * The server socket this listener is in a charge of.
     */
    private final ServerSocket serverSocket;

    /**
     * True if the socket is SSL secured.
     */
    private final boolean sslSecured;

    /**
     * Global logger instance.
     */
    private final Logger logger;

    /**
     * Server context manager the request handlers are dependent on.
     */
    private final ServerContextManager contextManager;

    /**
     * Class constructors that setup the dependencies of a socket listener
     * injected by the SocketListenerFactory.
     * <p>
     * A socket listener should never be instantiated by the client code. It's
     * preferred to use the SocketListenerFactory to generate instances of
     * socket listeners.
     * 
     * @param socket
     *            The socket this listener is in charge of
     * @param sslFlag
     *            Flag that denotes if the socket is SSL secured or not
     * @param scm
     *            The ServerContextManager for the request handlers
     * @param loggerInstance
     *            Global logger instance to log messages to
     */
    public SocketListener(final ServerSocket socket, final boolean sslFlag,
            final ServerContextManager scm, final Logger loggerInstance) {
        // move web server configuration to file
        serverSocket = socket;
        sslSecured = sslFlag;
        contextManager = scm;
        logger = loggerInstance;
    }

    @Override
    public void run() {
        // create a thread group for all request handling threads
        ThreadGroup handlers = new ThreadGroup("HTTP Request handlers on port "
                + serverSocket.getLocalPort());

        logger.log(Level.INFO, "Now listening on port " + serverSocket.getLocalPort());
        while (!Thread.currentThread().isInterrupted()) {
            Socket comSocket;
            try {
                // setup a socket timeout to be able to react on interrupt()
                serverSocket.setSoTimeout(1000);
                comSocket = serverSocket.accept();
                Thread th = new Thread(handlers, new RequestHandler(comSocket, sslSecured, logger,
                        contextManager));
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
                    logger.log(Level.FINE, "Socket listener was interrupted while waiting for"
                            + "a request handler to shutdown.");
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error closing server socket.", e);
        }
        logger.log(Level.INFO, "Stopped listening on port " + serverSocket.getLocalPort());

    }
}
