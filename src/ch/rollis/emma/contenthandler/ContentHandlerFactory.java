package ch.rollis.emma.contenthandler;

import ch.rollis.emma.request.Request;

/**
 * 
 * @author mrolli
 */
public class ContentHandlerFactory {
    /**
     * Factory method instantiates and return content handlers based on the
     * request received.
     * 
     * @param request
     *            The request to generate a content handler for
     * @return The content handler generated
     */
    public ContentHandler getHandler(final Request request) {
        ContentHandler handler = new FileHandler();
        return handler;
    }
}
