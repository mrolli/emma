package ch.rollis.emma.contenthandler;

import java.io.IOException;

import ch.rollis.emma.response.Response;

public interface ContentHandler {
    public Response process() throws IOException;
}
