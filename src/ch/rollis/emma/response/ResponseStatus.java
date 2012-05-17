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

/**
 * Enumeration of all HTTP/1.0 and HTTP/1.1 response codes and their reason
 * phrases listed in rfc1945 and 2616.
 * 
 * @author mrolli
 */
public enum ResponseStatus {
    /**
     * 100 - Continue.
     */
    CONTINUE (100, "Continue"),
    /**
     * 101 - Switching Protocols.
     */
    SWITCHING_PROTOCOLS (101, "Switching Protocols"),
    /**
     * 200 - OK.
     */
    OK (200, "OK"),
    /**
     * 201 - Created.
     */
    CREATED (201, "Created"),
    /**
     * 202 - Accepted.
     */
    ACCEPTED (202, "Accepted"),
    /**
     * 203 - Non-Authoritative Information.
     */
    NON_AUTHORITATIVE_INFORMATION (203, "Non-Authoritative Information"),
    /**
     * 204 - No Content.
     */
    NO_CONTENT (204, "No Content"),
    /**
     * 205 - Reset Content.
     */
    RESET_CONTENT (205, "Reset Content"),
    /**
     * 206 - Partial Content.
     */
    PARTIAL_CONTENT (206, "Partial Content"),
    /**
     * 300 - Multiple Choices.
     */
    MULTIPLE_CHOICES (300, "Multiple Choices"),
    /**
     * 301 - Moved Permanently.
     */
    MOVED_PERMANENTLY (301, "Moved Permanently"),
    /**
     * 302 - Found.
     */
    FOUND (302, "Found"),
    /**
     * 303 - See Other.
     */
    SEE_OTHER (303, "See Other"),
    /**
     * 304 - Not Modified.
     */
    NOT_MODIFIED (304, "Not Modified"),
    /**
     * 305 - Use Proxy.
     */
    USE_PROXY (305, "Use Proxy"),
    /**
     * 306 -  (Unused).
     */
    UNUSED (306, " (Unused)"),
    /**
     * 307 - Temporary Redirect.
     */
    TEMPORARY_REDIRECT (307, "Temporary Redirect"),
    /**
     * 400 - Bad Request.
     */
    BAD_REQUEST (400, "Bad Request"),
    /**
     * 401 - Unauthorized.
     */
    UNAUTHORIZED (401, "Unauthorized"),
    /**
     * 402 - Payment Required.
     */
    PAYMENT_REQUIRED (402, "Payment Required"),
    /**
     * 403 - Forbidden.
     */
    FORBIDDEN (403, "Forbidden"),
    /**
     * 404 - Not Found.
     */
    NOT_FOUND (404, "Not Found"),
    /**
     * 405 - Method Not Allowed.
     */
    METHOD_NOT_ALLOWED (405, "Method Not Allowed"),
    /**
     * 406 - Not Acceptable.
     */
    NOT_ACCEPTABLE (406, "Not Acceptable"),
    /**
     * 407 - Proxy Authentication Required.
     */
    PROXY_AUTHENTICATION_REQUIRED (407, "Proxy Authentication Required"),
    /**
     * 408 - Request Timeout.
     */
    REQUEST_TIMEOUT (408, "Request Timeout"),
    /**
     * 409 - Conflict.
     */
    CONFLICT (409, "Conflict"),
    /**
     * 410 - Gone.
     */
    GONE (410, "Gone"),
    /**
     * 411 - Length Required.
     */
    LENGTH_REQUIRED (411, "Length Required"),
    /**
     * 412 - Precondition Failed.
     */
    PRECONDITION_FAILED (412, "Precondition Failed"),
    /**
     * 413 - Request Entity Too Large.
     */
    REQUEST_ENTITY_TOO_LARGE (413, "Request Entity Too Large"),
    /**
     * 414 - Request-URI Too Long.
     */
    REQUEST_URI_TO_LONG (414, "Request-URI Too Long"),
    /**
     * 415 - Unsupported Media Type.
     */
    UNSUPPORTED_MEDIA_TYPE (415, "Unsupported Media Type"),
    /**
     * 416 - Requested Range Not Satisfiable.
     */
    REQUESTED_RANGE_NOT_SATISFIABLE (416, "Requested Range Not Satisfiable"),
    /**
     * 417 - Expectation Failed.
     */
    EXPECTATION_FAILED (417, "Expectation Failed"),
    /**
     * 500 - Internal Server Error.
     */
    INTERNAL_SERVER_ERROR (500, "Internal Server Error"),
    /**
     * 501 - Not Implemented.
     */
    NOT_IMPLEMENTED (501, "Not Implemented"),
    /**
     * 502 - Bad Gateway.
     */
    BAD_GATEWAY (502, "Bad Gateway"),
    /**
     * 503 - Service Unavailable.
     */
    SERVICE_UNAVAILABLE (503, "Service Unavailable"),
    /**
     * 504 - Gateway Timeout.
     */
    GATEWAY_TIMEOUT (504, "Gateway Timeout"),
    /**
     * 505 - HTTP Version Not Supported.
     */
    HTTP_VERSION_NOT_SUPPORTED (505, "HTTP Version Not Supported");

    /**
     * The response code.
     */
    private final int code;

    /**
     * The reason phrase.
     */
    private final String reasonPhrase;

    /**
     * Class constructor sets up a ReponseStatus object.
     * 
     * @param c
     *            The response code
     * @param rp
     *            The reason phrase
     */
    ResponseStatus(final int c, final String rp) {
        code = c;
        reasonPhrase = rp;
    }

    /**
     * Return the response status code.
     * 
     * @return Status code
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the reason phrase.
     * 
     * @return Reason phrase
     */
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    /**
     * Evaluates a response code to a ResponseStatus and returns it.
     * 
     * @param code
     *            The code to evaluate
     * @return ReponseStatus
     */
    public static ResponseStatus evaluateCode(final int code) {
        switch (code) {
            case 200:
                return ResponseStatus.OK;
            case 400:
                return ResponseStatus.BAD_REQUEST;
            case 403:
                return ResponseStatus.FORBIDDEN;
            case 404:
                return ResponseStatus.NOT_FOUND;
            case 500:
                return ResponseStatus.INTERNAL_SERVER_ERROR;
            case 501:
                return ResponseStatus.NOT_IMPLEMENTED;
            case 505:
                return ResponseStatus.HTTP_VERSION_NOT_SUPPORTED;
            case 100:
                return ResponseStatus.CONTINUE;
            case 101:
                return ResponseStatus.SWITCHING_PROTOCOLS;
            case 201:
                return ResponseStatus.CREATED;
            case 202:
                return ResponseStatus.ACCEPTED;
            case 203:
                return ResponseStatus.NON_AUTHORITATIVE_INFORMATION;
            case 204:
                return ResponseStatus.NO_CONTENT;
            case 205:
                return ResponseStatus.RESET_CONTENT;
            case 206:
                return ResponseStatus.PARTIAL_CONTENT;
            case 300:
                return ResponseStatus.MULTIPLE_CHOICES;
            case 301:
                return ResponseStatus.MOVED_PERMANENTLY;
            case 302:
                return ResponseStatus.FOUND;
            case 303:
                return ResponseStatus.SEE_OTHER;
            case 304:
                return ResponseStatus.NOT_MODIFIED;
            case 305:
                return ResponseStatus.USE_PROXY;
            case 306:
                return ResponseStatus.UNUSED;
            case 307:
                return ResponseStatus.TEMPORARY_REDIRECT;
            case 401:
                return ResponseStatus.UNAUTHORIZED;
            case 402:
                return ResponseStatus.PAYMENT_REQUIRED;
            case 405:
                return ResponseStatus.METHOD_NOT_ALLOWED;
            case 406:
                return ResponseStatus.NOT_ACCEPTABLE;
            case 407:
                return ResponseStatus.PROXY_AUTHENTICATION_REQUIRED;
            case 408:
                return ResponseStatus.REQUEST_TIMEOUT;
            case 409:
                return ResponseStatus.CONFLICT;
            case 410:
                return ResponseStatus.GONE;
            case 411:
                return ResponseStatus.LENGTH_REQUIRED;
            case 412:
                return ResponseStatus.PRECONDITION_FAILED;
            case 413:
                return ResponseStatus.REQUEST_ENTITY_TOO_LARGE;
            case 414:
                return ResponseStatus.REQUEST_URI_TO_LONG;
            case 415:
                return ResponseStatus.UNSUPPORTED_MEDIA_TYPE;
            case 416:
                return ResponseStatus.REQUESTED_RANGE_NOT_SATISFIABLE;
            case 417:
                return ResponseStatus.EXPECTATION_FAILED;
            case 502:
                return ResponseStatus.BAD_GATEWAY;
            case 503:
                return ResponseStatus.SERVICE_UNAVAILABLE;
            case 504:
                return ResponseStatus.GATEWAY_TIMEOUT;
            default:
                throw new IllegalArgumentException("Unknown http status code " + code);
        }
    }
}
