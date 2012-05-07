package ch.rollis.emma;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLServerSocketFactory;

import ch.rollis.emma.context.ServerContextManager;

/**
 * SocketListener objects listen on a server socket for incoming client
 * requests.
 * 
 * 
 * 
 * @author mrolli
 */
public class SocketListener implements Runnable {
    private final int port;
    private final Logger logger;
    private final ServerContextManager scm;
    private boolean sslSecured = false;

    public SocketListener(int port, ServerContextManager scm, Logger logger) {
        // move webserver configuration to file
        this.port = port;
        this.scm = scm;
        this.logger = logger;
    }

    public void setSecured(boolean flag) {
        sslSecured = flag;
    }

    public boolean isSecured() {
        return sslSecured;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;

        logger.log(Level.INFO, "Now listening on port " + port);

        try {
            if (isSecured()) {
                serverSocket = SSLServerSocketFactory.getDefault().createServerSocket(port);
            } else {
                serverSocket = new ServerSocket(port);
            }
            serverSocket.setSoTimeout(1000);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to open server socket on port " + port + ".", e);
        }

        // create a thread group for all request handling threads
        ThreadGroup handlers = new ThreadGroup("HTTP Request handlers on port " + port);

        while (!Thread.currentThread().isInterrupted()) {
            Socket comSocket;
            try {
                comSocket = serverSocket.accept();
                Thread th = new Thread(handlers, new RequestHandler(comSocket, logger, scm));
                th.setName("Request thread " + th.getId());
                th.start();
            } catch (SocketTimeoutException e) {
                // do nothing, just give the while loop a chance to exit on
                // interrupt
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error proccessing client request.", e);
            }
        }

        try {
            Thread[] threads = new Thread[handlers.activeCount()];
            handlers.enumerate(threads);
            for (Thread t : threads) {
                t.interrupt();
            }
            serverSocket.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error closing server socket.", e);
        }
        logger.log(Level.INFO, "Stopped listening on port " + port);

    }
}
