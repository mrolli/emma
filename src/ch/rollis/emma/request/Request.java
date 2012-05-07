package ch.rollis.emma.request;

import java.net.URI;
import java.util.HashMap;

import ch.rollis.emma.HttpMethod;

/**
 * Class represents a HTTP request.
 * 
 * Supported header fields are denoted in class constructor.
 * 
 * @author mrolli
 */
public class Request {
    private String protocol;
    private HttpMethod method;
    private URI requestURI;
    private final HashMap<String, String> headersGeneral;
    private final HashMap<String, String> headersRequest;
    private final HashMap<String, String> headersEntity;

    public Request() {
        headersGeneral = new HashMap<String, String>();
        headersGeneral.put("DATE", null);
        headersGeneral.put("PRAGMA", null);

        headersRequest = new HashMap<String, String>();
        headersRequest.put("HOST", null);
        headersRequest.put("AUTHORIZATION", null);
        headersRequest.put("FROM", null);
        headersRequest.put("IF-MODIFIED-SINCE", null);
        headersRequest.put("REFERER", null);
        headersRequest.put("USER-AGENT", null);

        headersEntity = new HashMap<String, String>();
        headersEntity.put("ALLOW", null);
        headersEntity.put("CONTENT-ENCODING", null);
        headersEntity.put("CONTENT-LENGTH", null);
        headersEntity.put("CONTENT-TYPE", null);
        headersEntity.put("EXPIRES", null);
        headersEntity.put("LAST-MODIFIED", null);
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
     * Check if request is a GET request.
     * 
     * @return true if request is GET, false otherwise
     */
    public boolean isGet() {
        return method.equals(HttpMethod.GET);
    }

    /**
     * Check if request is a POST request.
     * 
     * @return true if request is POST, false otherwise
     */
    public boolean isPost() {
        return method.equals(HttpMethod.POST);
    }

    /**
     * Check if request is a HEAD request.
     * 
     * @return true if request is HEAD, false otherwise
     */
    public boolean isHead() {
        return method.equals(HttpMethod.HEAD);
    }

    /**
     * Check if request is a PUT request.
     * 
     * @return true if request is PUT, false otherwise
     */
    public boolean isPut() {
        return method.equals(HttpMethod.PUT);
    }

    /**
     * Check if request is a DELETE request.
     * 
     * @return true if request is DELETE, false otherwise
     */
    public boolean isDelete() {
        return method.equals(HttpMethod.DELETE);
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
     * Check if request is a full request
     * 
     * @return true if request is a full request; false otherwise
     */
    public boolean isFullRequest() {
        return !"HTTP/0.9".equals(getProtocol());
    }

    /**
     * Convenience method
     * 
     * See method isFullRequst()
     * 
     * @return true if request is a simple request; false otherwise
     */
    public boolean isSimpleRequest() {
        return !isFullRequest();
    }

    /**
     * Returns the value of a general header field.
     * 
     * @param key
     *            Header field to retrieve the value for
     * @return Value of header field
     */
    public String getHeader(String key) {
        key = key.toUpperCase();
        if (headersGeneral.containsKey(key)) {
            return headersGeneral.get(key);
        } else if (headersRequest.containsKey(key)) {
            return headersRequest.get(key);
        } else if (headersEntity.containsKey(key)) {
            return headersEntity.get(key);
        }
        return null;
    }

    /**
     * Set the value of a header field to the request.
     * 
     * Values for unknown header fields are stored as entity headers, see
     * rfc1945.
     * 
     * @param key
     *            Header field
     * @param value
     *            Value of header field
     */
    public void setHeader(String key, String value) {
        key = key.toUpperCase();
        if (headersGeneral.containsKey(key)) {
            headersGeneral.put(key, value);
        } else if (headersRequest.containsKey(key)) {
            headersRequest.put(key, value);
        } else {
            headersEntity.put(key, value);
        }
    }
}
