package ch.rollis.emma.response;

import ch.rollis.emma.HttpServerConfig;
import ch.rollis.emma.request.Request;

public class HttpResponseFactory {
    public HttpResponse getResponse() {
        return getDefaultResponse();
    }

    public HttpResponse getResponse(Request request) {
        HttpResponse response = getDefaultResponse();
        response.setProtocol(request.getProtocol());
        response.setRequest(request);
        return response;
    }

    public HttpResponse getResponse(HttpResponseStatus status) {
        HttpResponse response = getDefaultResponse();
        response.setStatus(status);
        response.setEntity(String.format("<h1>%s - %s</h1>", status.getCode(),
                status.getReasonPhrase()));
        return response;
    }

    public HttpResponse getResponse(Request request, HttpResponseStatus status) {
        HttpResponse response = getDefaultResponse();
        response.setProtocol(request.getProtocol());
        response.setRequest(request);
        response.setStatus(status);
        response.setEntity(String.format("<h1>%s - %s</h1>", status.getCode(),
                status.getReasonPhrase()));
        return response;
    }

    private HttpResponse getDefaultResponse() {
        HttpResponse response = new HttpResponse();
        response.addHeader("Server", HttpServerConfig.SERVER_TOKEN);
        return response;
    }
}
