package ch.rollis.emma;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.rollis.emma.context.ServerContext;
import ch.rollis.emma.context.ServerContextException;
import ch.rollis.emma.context.ServerContextManager;

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
    private final ServerContextManager scm;

    public HttpServer(int port) throws HttpServerException {
        // move webserver configuration to file
        this.port = port;
        logger = Logger.getLogger("server_log");
        scm = new ServerContextManager();

        File docRoot = new File("./vhosts/default/public_html");
        ServerContext con = new ServerContext("localhost", docRoot);
        con.setDefaultContext(true);
        try {
            scm.addContext(con);
        } catch (ServerContextException e) {
            throw new HttpServerException("Unable to start emma", e);
        }

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
            Socket comSocket;
            try {
                comSocket = serverSocket.accept();
                Thread th = new Thread(threadGroup, new HttpRequestHandler(comSocket, logger, scm));
                th.setName("Request thread " + th.getId());
                th.start();
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
