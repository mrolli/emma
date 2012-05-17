/**
 * Copyright (c) 2012 Michael Rolli - github.com/mrolli/emma
 * All rights reserved.
 * 
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 3.0 Switzerland
 * License. To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/3.0/ch/
 * or send a letter to Creative Commons, 444 Castro Street,
 * Suite 900, Mountain View, California, 94041, USA.
 */
package ch.rollis.emma.response;

import ch.rollis.emma.Emma;
import ch.rollis.emma.request.Request;

/**
 * The response factory generates preconfigured response objects based on given
 * requests or ResponseStatus codes.
 * <p>
 * Some base header field values will be prefilled based on the used getter.
 * Especially the response header "Server" is centrally filled in here.
 * 
 * @author mrolli
 */
public class ResponseFactory {
    /**
     * HTML template for autogenerated response entities, i.e. 404, 304, ...
     */
    private static final String HTML_TEMPLATE = "<html><head><title>%s</title></head>"
            + "<body><h1>%s - %s</h1><hr/>" + Emma.VERSION + "</body></html>";

    /**
     * Returns a default response object.
     * 
     * @return Response object
     */
    public Response getResponse() {
        return getDefaultResponse();
    }

    /**
     * Returns a response object based on given request.
     * <p>
     * The request is injected to the response -> response uses the same
     * protocol version as the request.
     * 
     * @param request
     *            The request to generate a response for
     * @return Response object
     */
    public Response getResponse(final Request request) {
        Response response = getDefaultResponse();
        response.setRequest(request);
        return response;
    }

    /**
     * Returns a response object for a given ResponseStatus
     * <p>
     * The entity body is prefilled with a human readable HTML page based on the
     * response status.
     * 
     * @param status
     *            RepnseStatus of the request
     * @return Response object
     */
    public Response getResponse(final ResponseStatus status) {
        Response response = getDefaultResponse();
        response.setStatus(status);
        response.setHeader("Content-Type", "text/html");
        response.setEntity(String.format(HTML_TEMPLATE, status.getReasonPhrase(), status.getCode(),
                status.getReasonPhrase()));
        return response;
    }

    /**
     * Returns a response object for a given ResponseStatus and Request object.
     * <p>
     * The entity body is prefilled with a human readable HTML page based on the
     * response status.
     * 
     * @param request
     *            The request to generate a response for
     * @param status
     *            RepnseStatus of the request
     * @return Response object
     */
    public Response getResponse(final Request request, final ResponseStatus status) {
        Response response = getDefaultResponse();
        response.setRequest(request);
        response.setStatus(status);
        response.setHeader("Content-Type", "text/html");
        response.setEntity(String.format(HTML_TEMPLATE, status.getReasonPhrase(), status.getCode(),
                status.getReasonPhrase()));
        return response;
    }

    /**
     * Returns a default response with preconfigured headers set on all reponses
     * of this server.
     * 
     * @return Repomse object
     */
    private Response getDefaultResponse() {
        Response response = new Response();
        response.setHeader("Server", Emma.SERVER_TOKEN);
        return response;
    }
}
