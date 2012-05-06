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
import ch.rollis.emma.context.ServerContext;
import ch.rollis.emma.context.ServerContextManager;
import ch.rollis.emma.request.Request;
import ch.rollis.emma.response.Response;
import ch.rollis.emma.response.ResponseFactory;
import ch.rollis.emma.response.ResponseStatus;


/**
 * @author mrolli
 *
 */
public class HttpRequestHandler implements Runnable {
    private final Socket clientSocket;
    private final ServerContextManager scm;
    private final Logger logger;

    public HttpRequestHandler(Socket socket, Logger logger, ServerContextManager contentManager) {
        this.clientSocket = socket;
        this.scm = contentManager;
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
                Request request = parser.parse();
                ServerContext context = scm.getContext(request);
                ContentHandler handler = new ContentHandlerFactory().getHandler(request);
                Response response = handler.process(request, context);
                response.send(output);
            } catch (HttpProtocolException e) {
                logger.log(Level.WARNING, "HTTP protocol violation", e);
                Response response = new ResponseFactory().getResponse(ResponseStatus.BAD_REQUEST);
                response.send(output);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "RequestHandler cannot handle request", e);
                Response response = new ResponseFactory()
                .getResponse(ResponseStatus.INTERNAL_SERVER_ERROR);
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
