package ch.rollis.emma.request;

import java.net.URI;
import java.util.HashMap;

import ch.rollis.emma.HttpMethod;


public class Request {
    private String protocol;
    private HttpMethod method;
    private URI requestURI;
    private final HashMap<String, String> headers;

    public Request() {
        headers = new HashMap<String, String>();
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the method
     */
    public HttpMethod getMethod() {
        return method;
    }

    /**
     * @param method
     *            the method to set
     */
    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    /**
     * @return the requestURI
     */
    public URI getRequestURI() {
        return requestURI;
    }

    /**
     * @param requestURI
     *            the requestURI to set
     */
    public void setRequestURI(URI requestURI) {
        this.requestURI = requestURI;
    }

    /**
     * @return the headers
     */
    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getHeader(String key) {
        if (!headers.containsKey(key)) {
            return null;
        }
        return headers.get(key);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }
}
