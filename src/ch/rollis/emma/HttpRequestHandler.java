/**
 * 
 */
package ch.rollis.emma;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.rollis.emma.HttpProtocolException.HttpProtocolException;


/**
 * @author mrolli
 *
 */
public class HttpRequestHandler implements Runnable {
    private final Socket socket;
    private final Logger logger;

    public HttpRequestHandler(Socket socket, Logger logger) {
        this.socket = socket;
        this.logger = logger;
    }
    @Override
    public void run() {
        HttpRequest request;
        HttpResponse response;
        InetAddress client;

        client = socket.getInetAddress();
        logger.log(Level.INFO, client.getCanonicalHostName() + " has connected.");

        try {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            request= new HttpRequest(input);
            try {
                request.parse();
            } catch (HttpProtocolException e) {
                String errorMessage = "HTTP/1.0 400 Bad Request\r\n\r\n<h1>Bad Request</h1>";
                output.write(errorMessage.getBytes());
            } catch (IOException e) {
                // response.setCode(400);
            }

            socket.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error closing socket.", e);
        }

        logger.log(Level.INFO, Thread.currentThread().getName() + " ended.");
    }

}
