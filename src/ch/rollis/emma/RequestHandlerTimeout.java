package ch.rollis.emma;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author mrolli
 */
public class RequestHandlerTimeout implements Runnable {
    /**
     * Socket to close after timeout.
     */
    private final Socket comSocket;

    /**
     * Time (in milliseconds) to wait.
     */
    private final int time;

    /**
     * Logger instance to log to.
     */
    private final Logger logger;

    /**
     * Class constructor to set dependencies.
     * 
     * @param socket
     *            The socket to close
     * @param timeout
     *            Number of milliseconds to wait before closing the socket
     * @param loggerInstance
     *            Logger instance to log to
     */
    RequestHandlerTimeout(final Socket socket, final int timeout, final Logger loggerInstance) {
        comSocket = socket;
        time = timeout;
        logger = loggerInstance;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(time);
            comSocket.close();
        } catch (InterruptedException e) {
            Thread.interrupted();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while closing com socket");
        }
    }
}
