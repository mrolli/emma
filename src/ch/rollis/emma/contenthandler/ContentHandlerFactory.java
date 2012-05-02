package ch.rollis.emma.contenthandler;

import ch.rollis.emma.HttpRequest;

public class ContentHandlerFactory {
    public ContentHandler getHandler(HttpRequest request) {
        ContentHandler handler = new FileHandler(request);
        return handler;
    }
}
