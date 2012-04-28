package ch.rollis.emma;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Emma - Tiny Webserver
 * 
 * Emma is a tiny webserver that implements...
 * 
 * @author mrolli
 */
public class HttpServer {
    private final int port;
    private final Logger logger;

    public HttpServer(int port) throws HttpServerException {
        // move webserver configuration to file
        this.port = port;
        logger = Logger.getLogger("server_log");
    }

    public void start() throws HttpServerException {
        ServerSocket serverSocket = null;

        logger.log(Level.INFO, "Starting Emma webserver.");

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to open server socket on port "
                    + port + ".", e);
        }

        // create a thread group for alle request handling threads
        ThreadGroup threadGroup = new ThreadGroup("HTTP Request handlers");
        while (!Thread.currentThread().isInterrupted()) {
            Socket socket;
            try {
                socket = serverSocket.accept();
                Thread th = new Thread(threadGroup, new HttpRequestHandler(
                        socket, logger));
                th.setName("Request thread " + th.getId());
                th.start();
                logger.log(Level.INFO, th.getName() + " started.");
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error proccessing client request.", e);
            }
        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error closing server socket.", e);
        }

    }
}
