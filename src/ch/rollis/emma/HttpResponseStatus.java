package ch.rollis.emma;

public enum HttpResponseStatus {
    CONTINUE (100, "Continue"),
    SWITCHING_PROTOCOLS (101, "Switching Protocols"),
    OK (200, "OK"),
    CREATED (201, "Created"),
    ACCEPTED (202, "Accepted"),
    NON_AUTHORITATIVE_INFORMATION (203, "Non-Authoritative Information"),
    NO_CONTENT (204, "No Content"),
    RESET_CONTENT (205, "Reset Content"),
    PARTIAL_CONTENT (206, "Partial Content"),
    MULTIPLE_CHOICES (300, "Multiple Choices"),
    MOVED_PERMANENTLY (301, "Moved Permanently"),
    FOUND (302, "Found"),
    SEE_OTHER (303, "See Other"),
    NOT_MODIFIED (304, "Not Modified"),
    USE_PROXY (305, "Use Proxy"),
    UNUSED (306, " (Unused)"),
    TEMPORARY_REDIRECT (307, "Temporary Redirect"),
    BAD_REQUEST (400, "Bad Request"),
    UNAUTHORIZED (401, "Unauthorized"),
    PAYMENT_REQUIRED (402, "Payment Required"),
    FORBIDDEN (403, "Forbidden"),
    NOT_FOUND (404, "Not Found"),
    METHOD_NOT_ALLOWED (405, "Method Not Allowed"),
    NOT_ACCEPTABLE (406, "Not Acceptable"),
    PROXY_AUTHENTICATION_REQUIRED (407, "Proxy Authentication Required"),
    REQUEST_TIMEOUT (408, "Request Timeout"),
    CONFLICT (409, "Conflict"),
    GONE (410, "Gone"),
    LENGTH_REQUIRED (411, "Length Required"),
    PRECONDITION_FAILED (412, "Precondition Failed"),
    REQUEST_ENTITY_TOO_LARGE (413, "Request Entity Too Large"),
    REQUEST_URI_TO_LONG (414, "Request-URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE (415, "Unsupported Media Type"),
    REQUESTED_RANGE_NOT_SATISFIABLE (416, "Requested Range Not Satisfiable"),
    EXPECTATION_FAILED (417, "Expectation Failed"),
    INTERNAL_SERVER_ERROR (500, "Internal Server Error"),
    NOT_IMPLEMENTED (501, "Not Implemented"),
    BAD_GATEWAY (502, "Bad Gateway"),
    SERVICE_UNAVAILABLE (503, "Service Unavailable"),
    GATEWAY_TIMEOUT (504, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED (505, "HTTP Version Not Supported");

    private final int code;
    private final String reasonPhrase;

    HttpResponseStatus(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    public int getCode() {
        return code;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public static HttpResponseStatus evaluateCode(int code) {
        switch (code) {
            case 200:
                return HttpResponseStatus.OK;
            case 400:
                return HttpResponseStatus.BAD_REQUEST;
            case 403:
                return HttpResponseStatus.FORBIDDEN;
            case 404:
                return HttpResponseStatus.NOT_FOUND;
            case 500:
                return HttpResponseStatus.INTERNAL_SERVER_ERROR;
            case 501:
                return HttpResponseStatus.NOT_IMPLEMENTED;
            case 505:
                return HttpResponseStatus.HTTP_VERSION_NOT_SUPPORTED;
            case 100:
                return HttpResponseStatus.CONTINUE;
            case 101:
                return HttpResponseStatus.SWITCHING_PROTOCOLS;
            case 201:
                return HttpResponseStatus.CREATED;
            case 202:
                return HttpResponseStatus.ACCEPTED;
            case 203:
                return HttpResponseStatus.NON_AUTHORITATIVE_INFORMATION;
            case 204:
                return HttpResponseStatus.NO_CONTENT;
            case 205:
                return HttpResponseStatus.RESET_CONTENT;
            case 206:
                return HttpResponseStatus.PARTIAL_CONTENT;
            case 300:
                return HttpResponseStatus.MULTIPLE_CHOICES;
            case 301:
                return HttpResponseStatus.MOVED_PERMANENTLY;
            case 302:
                return HttpResponseStatus.FOUND;
            case 303:
                return HttpResponseStatus.SEE_OTHER;
            case 304:
                return HttpResponseStatus.NOT_MODIFIED;
            case 305:
                return HttpResponseStatus.USE_PROXY;
            case 306:
                return HttpResponseStatus.UNUSED;
            case 307:
                return HttpResponseStatus.TEMPORARY_REDIRECT;
            case 401:
                return HttpResponseStatus.UNAUTHORIZED;
            case 402:
                return HttpResponseStatus.PAYMENT_REQUIRED;
            case 405:
                return HttpResponseStatus.METHOD_NOT_ALLOWED;
            case 406:
                return HttpResponseStatus.NOT_ACCEPTABLE;
            case 407:
                return HttpResponseStatus.PROXY_AUTHENTICATION_REQUIRED;
            case 408:
                return HttpResponseStatus.REQUEST_TIMEOUT;
            case 409:
                return HttpResponseStatus.CONFLICT;
            case 410:
                return HttpResponseStatus.GONE;
            case 411:
                return HttpResponseStatus.LENGTH_REQUIRED;
            case 412:
                return HttpResponseStatus.PRECONDITION_FAILED;
            case 413:
                return HttpResponseStatus.REQUEST_ENTITY_TOO_LARGE;
            case 414:
                return HttpResponseStatus.REQUEST_URI_TO_LONG;
            case 415:
                return HttpResponseStatus.UNSUPPORTED_MEDIA_TYPE;
            case 416:
                return HttpResponseStatus.REQUESTED_RANGE_NOT_SATISFIABLE;
            case 417:
                return HttpResponseStatus.EXPECTATION_FAILED;
            case 502:
                return HttpResponseStatus.BAD_GATEWAY;
            case 503:
                return HttpResponseStatus.SERVICE_UNAVAILABLE;
            case 504:
                return HttpResponseStatus.GATEWAY_TIMEOUT;

            default:
                throw new IllegalArgumentException("Unknown http status code " + code);
        }
    }
}
