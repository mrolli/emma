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
    private final Socket clientSocket;
    private final Logger logger;

    public HttpRequestHandler(Socket socket, Logger logger) {
        this.clientSocket = socket;
        this.logger = logger;
    }
    @Override
    public void run() {
        HttpProtocolParser parser;
        HttpRequest request;
        HttpResponse response;
        InetAddress client;

        client = clientSocket.getInetAddress();
        logger.log(Level.INFO, client.getCanonicalHostName() + " has connected.");

        try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            parser = new HttpProtocolParser(input);
            try {
                request = parser.parse();
            } catch (HttpProtocolException e) {
                logger.log(Level.SEVERE, "HTTP protocol violation", e);

                HttpResponseStatus resStatus = HttpResponseStatus.BAD_REQUEST;
                response = new HttpResponse(output);
                response.setStatus(resStatus);
                response.send("<h1>" + resStatus.getReasonPhrase() + "</h1>");
            } catch (IOException e) {
                logger.log(Level.SEVERE, "HTTP protocol violation", e);

                HttpResponseStatus resStatus = HttpResponseStatus.INTERNAL_SERVER_ERROR;
                response = new HttpResponse(output);
                response.setStatus(resStatus);
                response.send("<h1>" + resStatus.getReasonPhrase() + "</h1>");
            } finally {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error closing socket.", e);
        }

        logger.log(Level.INFO, Thread.currentThread().getName() + " ended.");
    }
}
