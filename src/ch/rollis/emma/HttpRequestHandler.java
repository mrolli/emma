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
            } catch (HttpProtocolException e) {
                logger.log(Level.SEVERE, "HTTP protocol violation", e);
                sendErrorResponse(output, HttpResponseStatus.BAD_REQUEST);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Input/Output exception", e);
                sendErrorResponse(output, HttpResponseStatus.INTERNAL_SERVER_ERROR);
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

    private void sendErrorResponse(OutputStream output, HttpResponseStatus resStatus)
            throws IOException {
        HttpResponse response = new HttpResponse(output);
        response.setStatus(resStatus);
        response.send("<h1>" + resStatus.getReasonPhrase() + "</h1>");
    }
}
