package ch.rollis.emma.contenthandler;

import ch.rollis.emma.request.Request;

public class ContentHandlerFactory {
    public ContentHandler getHandler(Request request) {
        ContentHandler handler = new FileHandler();
        return handler;
    }
}
