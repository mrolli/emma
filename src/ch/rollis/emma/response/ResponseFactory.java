package ch.rollis.emma.response;

import ch.rollis.emma.HttpServerConfig;
import ch.rollis.emma.request.Request;

public class ResponseFactory {
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
        response.setEntity(String.format("<h1>%s - %s</h1>", status.getCode(),
                status.getReasonPhrase()));
        return response;
    }

    public Response getResponse(Request request, ResponseStatus status) {
        Response response = getDefaultResponse();
        response.setProtocol(request.getProtocol());
        response.setRequest(request);
        response.setStatus(status);
        response.setEntity(String.format("<h1>%s - %s</h1>", status.getCode(),
                status.getReasonPhrase()));
        return response;
    }

    private Response getDefaultResponse() {
        Response response = new Response();
        response.addHeader("Server", HttpServerConfig.SERVER_TOKEN);
        return response;
    }
}
