package ch.rollis.emma;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

public class HttpResponse {
    private String protocol;
    private HttpRequest request;
    private HttpResponseStatus status;
    private final HashMap<String, String> headers;
    private byte[] entityBody;

    private static final String CRLF = "\r\n";
    private static final String SP = " ";

    public HttpResponse() {
        this("HTTP/1.1");
    }

    public HttpResponse(String protocol) {
        this.protocol = protocol;
        headers = new HashMap<String, String>();
        addHeader("Server", HttpServerConfig.SERVER_TOKEN);
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() {
        if (request != null) {
            return request.getProtocol();
        }
        return protocol;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    /**
     * @return the status
     */
    public HttpResponseStatus getStatus() {
        return status;
    }

    public void setStatus(HttpResponseStatus status) {
        this.status = status;
    }

    public String getContentType() {
        return getHeader("Content-type");
    }

    public void setContentType(String value) {
        addHeader("Content-type", value);
    }

    public String getContentLength() {
        return getHeader("Content-length");
    }

    public void setContentLength(String value) {
        addHeader("Content-length", value);
    }

    public String getLastModified() {
        return getHeader("Last-modified");
    }

    public void setLastModified(String value) {
        addHeader("Last-modified", value);
    }

    private String getHeader(String key) {
        if (headers.containsKey(key)) {
            return headers.get(key);
        }
        return null;
    }

    private void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void setEntity(String entity) {
        setEntity(entity.getBytes());
    }

    public void setEntity(byte[] entity) {
        this.entityBody = entity;
    }

    public void send(OutputStream output) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(output);

        if (getHeader("Content-length") == null) {
            addHeader("Cotnent-length", String.valueOf(entityBody.length));
        }

        sendStatusLine(out);
        sendHeaders(out);

        if (request == null || request.getMethod() != HttpMethod.HEAD) {
            out.write(entityBody);
            out.write(CRLF.getBytes());
        }
        out.flush();
    }

    private void sendStatusLine(BufferedOutputStream out) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(protocol).append(SP).append(status.getCode()).append(SP)
        .append(status.getReasonPhrase()).append(CRLF);
        out.write(sb.toString().getBytes());
    }

    private void sendHeaders(BufferedOutputStream out) throws IOException {
        if (!headers.containsKey("Date")) {
            SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",
                    Locale.US);
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            headers.put("Date", formatter.format(new Date()));
        }
        for (String field : headers.keySet()) {
            out.write(String.format("%s: %s", field, headers.get(field)).getBytes());
            out.write(CRLF.getBytes());
        }
        out.write(CRLF.getBytes());
    }

}
