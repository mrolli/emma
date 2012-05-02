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

import ch.rollis.emma.contenthandler.ContentHandler;
import ch.rollis.emma.contenthandler.ContentHandlerFactory;
import ch.rollis.emma.response.HttpResponse;
import ch.rollis.emma.response.HttpResponseFactory;
import ch.rollis.emma.response.HttpResponseStatus;


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
        InetAddress client;

        client = clientSocket.getInetAddress();
        logger.log(Level.INFO, client.getCanonicalHostName() + " has connected.");

        try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            HttpProtocolParser parser = new HttpProtocolParser(input);
            try {
                HttpRequest request = parser.parse();
                ContentHandlerFactory handlerFactory = new ContentHandlerFactory();
                ContentHandler handler = handlerFactory.getHandler(request);
                HttpResponse response = handler.process();
                response.send(output);
            } catch (HttpProtocolException e) {
                logger.log(Level.WARNING, "HTTP protocol violation", e);
                HttpResponse response = new HttpResponseFactory()
                        .getResponse(HttpResponseStatus.BAD_REQUEST);
                response.send(output);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Input/Output exception", e);
                HttpResponse response = new HttpResponseFactory()
                        .getResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR);
                response.send(output);
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
