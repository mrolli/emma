package ch.rollis.emma.contenthandler;

import java.io.IOException;

import ch.rollis.emma.HttpResponse;

public interface ContentHandler {
    public HttpResponse process() throws IOException;
}
