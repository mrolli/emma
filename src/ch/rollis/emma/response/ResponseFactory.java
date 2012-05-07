package ch.rollis.emma.response;

import ch.rollis.emma.Emma;
import ch.rollis.emma.request.Request;

public class ResponseFactory {
    private static final String HTML_TEMPLATE = "<html><head><title>%s</title></head>"
            + "<body><h1>%s - %s</h1><hr/>" + Emma.VERSION + "</body></html>";

    public Response getResponse() {
        return getDefaultResponse();
    }

    public Response getResponse(Request request) {
        Response response = getDefaultResponse();
        response.setProtocol(request.getProtocol());
        response.setRequest(request);
        return response;
    }

    public Response getResponse(ResponseStatus status) {
        Response response = getDefaultResponse();
        response.setStatus(status);
        response.setHeader("Content-Type", "text/html");
        response.setEntity(String.format(HTML_TEMPLATE, status.getReasonPhrase(), status.getCode(),
                status.getReasonPhrase()));
        return response;
    }

    public Response getResponse(Request request, ResponseStatus status) {
        Response response = getDefaultResponse();
        response.setProtocol(request.getProtocol());
        response.setRequest(request);
        response.setStatus(status);
        response.setHeader("Content-Type", "text/html");
        response.setEntity(String.format(HTML_TEMPLATE, status.getReasonPhrase(), status.getCode(),
                status.getReasonPhrase()));
        return response;
    }

    private Response getDefaultResponse() {
        Response response = new Response();
        response.setHeader("Server", Emma.SERVER_TOKEN);
        return response;
    }
}
