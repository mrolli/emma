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
    private final OutputStream output;
    private String protocol;
    private HttpResponseStatus status;
    private final HashMap<String, String> headers;

    private static final String CRLF = "\r\n";
    private static final String SP = " ";

    public HttpResponse(OutputStream output) {
        this(output, "HTTP/1.1");
    }

    public HttpResponse(OutputStream output, String protocol) {
        this.output = new BufferedOutputStream(output);
        this.protocol = protocol;
        headers = new HashMap<String, String>();
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setStatus(HttpResponseStatus status) {
        this.status = status;
    }

    /**
     * @return the status
     */
    public HttpResponseStatus getStatus() {
        return status;
    }

    public void send(String body) throws IOException {
        body = body + "\r\n";
        sendStatusLine();
        sendHeaders();
        output.write(body.getBytes());
        output.flush();
    }

    private void sendStatusLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(protocol).append(SP).append(status.getCode()).append(SP)
        .append(status.getReasonPhrase()).append(CRLF);
        output.write(sb.toString().getBytes());
    }

    private void sendHeaders() throws IOException {
        if (!headers.containsKey("Date")) {
            SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",
                    Locale.US);
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            headers.put("Date", formatter.format(new Date()));
        }
        for (String field : headers.keySet()) {
            output.write(String.format("%s: %s", field, headers.get(field)).getBytes());
            output.write(CRLF.getBytes());
        }
        output.write(CRLF.getBytes());
    }

}
