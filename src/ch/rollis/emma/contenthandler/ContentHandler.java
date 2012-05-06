package ch.rollis.emma.contenthandler;

import java.io.IOException;

import ch.rollis.emma.context.ServerContext;
import ch.rollis.emma.request.Request;
import ch.rollis.emma.response.Response;

public interface ContentHandler {
    public Response process(Request request, ServerContext context) throws IOException;
}
