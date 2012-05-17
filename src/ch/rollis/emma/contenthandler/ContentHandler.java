package ch.rollis.emma.contenthandler;

import ch.rollis.emma.context.ServerContext;
import ch.rollis.emma.request.Request;
import ch.rollis.emma.response.Response;

/**
 * Content handler interface implemented by all content handlers.
 * 
 * @author mrolli
 */
public interface ContentHandler {
    /**
     * Process a request within a given server context, generates a response and
     * return it.
     * 
     * @param request
     *            The request to process
     * @param context
     *            The server context the request was received
     * @return The generated response based on the request
     * @throws Exception
     *             In case an exception was encountered and could be handled
     */
    Response process(Request request, ServerContext context) throws Exception;
}
