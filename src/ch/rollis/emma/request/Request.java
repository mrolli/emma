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
    public String getGeneralHeader(String key) {
        key = key.toUpperCase();
        return headersGeneral.get(key);
    }

    /**
     * Returns the all general headers.
     * 
     * @return Map of general headers and values
     */
    public HashMap<String, String> getGeneralHeaders() {
        return (HashMap<String, String>) headersGeneral.clone();
    }

    /**
     * Returns the value of a request header field.
     * 
     * @param key
     *            Header field to retrieve the value for
     * @return Value of header field
     */
    public String getRequestHeader(String key) {
        key = key.toUpperCase();
        return headersRequest.get(key);
    }

    /**
     * Returns the all request headers.
     * 
     * @return Map of request headers and values
     */
    public HashMap<String, String> getRequestHeaders() {
        return (HashMap<String, String>) headersRequest.clone();
    }

    /**
     * Returns the value of an entity header field.
     * 
     * @param key
     *            Header field to retrieve the value for
     * @return Value of header field
     */
    public String getEntityHeader(String key) {
        key = key.toUpperCase();
        return headersEntity.get(key);
    }

    /**
     * Returns the entity headers.
     * 
     * @return Map of entity headers and values
     */
    public HashMap<String, String> getEntityHeaders() {
        return (HashMap<String, String>) headersEntity.clone();
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
