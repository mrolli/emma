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
    private final OutputStream out;
    private final HttpRequest req;
    private HttpResponseStatus status;
    private final HashMap<String, String> headers;

    private static final String CRLF = "\r\n";
    private static final String SP = " ";

    public HttpResponse(OutputStream output, HttpRequest request) {
        out = new BufferedOutputStream(output);
        req = request;
        headers = new HashMap<String, String>();
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
        out.write(body.getBytes());
        out.flush();
    }

    private void sendStatusLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(req.getProtocolVersion()).append(SP).append(status.getCode()).append(SP)
        .append(status.getReasonPhrase()).append(CRLF);
        out.write(sb.toString().getBytes());
    }

    private void sendHeaders() throws IOException {
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
