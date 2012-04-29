package ch.rollis.emma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.rollis.emma.HttpProtocolException.BadRequestException;
import ch.rollis.emma.HttpProtocolException.HttpProtocolException;


public class HttpRequest {
    private final BufferedReader reader;
    private HttpMethod method;
    private URI requestURI;
    private int majorVersion = 1;
    private int minorVersion = 1;
    private final HashMap<String, String> headers;

    private static final String CRLF = "\r\n";
    private static final String SP = " ";

    public HttpRequest(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input));
        headers = new HashMap<String, String>();
    }

    public void parse() throws HttpProtocolException, IOException {
        parseRequestLine();
        if (getProtocolMajorVersion() >= 1 && getProtocolMinorVersion() >= 1) {
            parseHeaders();
            // parseBody();
        }
        validate();
    }

    private void parseRequestLine() throws HttpProtocolException, IOException {
        String reqLine = reader.readLine();
        String[] reqParts = reqLine.split(SP);

        if (reqParts.length == 2) {
            // Simple-Request
            try {
                method = HttpMethod.valueOf(reqParts[0]);
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Unknown method.");
            }
            majorVersion = 0;
            minorVersion = 9;
            // HTTP/0.9 only allows GET requests
            if (method.compareTo(HttpMethod.GET) != 0) {
                throw new BadRequestException("Illegal method.");
            }
        } else if (reqParts.length == 3) {
            // Full-Request
            try {
                method = HttpMethod.valueOf(reqParts[0]);
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Unknown method.");
            }
            Matcher m = Pattern.compile("HTTP/(\\d+).(\\d+)").matcher(reqParts[2]);
            if (m.matches()) {
                majorVersion = Integer.parseInt(m.group(1));
                minorVersion = Integer.parseInt(m.group(2));
            } else {
                throw new BadRequestException("Bad protocol.");
            }
        } else {
            // Bad Request
            throw new BadRequestException("Malformed request.");
        }

        try {
            requestURI = new URI(reqParts[1]);
        } catch (URISyntaxException e) {
            throw new BadRequestException("Illegal URI.");
        }
    }

    private void parseHeaders() throws IOException {
        String line = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.equals("")) {
                break;
            }
            int colonPos = line.indexOf(":");
            if (colonPos > 0) {
                String key = line.substring(0, colonPos);
                String value = line.substring(colonPos + 1);
                headers.put(key, value.trim());
            }
        }
    }

    public void validate() throws HttpProtocolException {
        if (getProtocolMajorVersion() >= 1 && getProtocolMinorVersion() >= 1
                && !headers.containsKey("Host")) {
            throw new BadRequestException("No Host header present for HTTP/1.1");
        }
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

    public String getProtocolVersion() {
        return String.format("HTTP/%s.%s", majorVersion, minorVersion);
    }

    /**
     * @return the majorVersion
     */
    public int getProtocolMajorVersion() {
        return majorVersion;
    }

    /**
     * @param majorVersion
     *            the majorVersion to set
     */
    public void setProtocolMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    /**
     * @return the minorVersion
     */
    public int getProtocolMinorVersion() {
        return minorVersion;
    }

    /**
     * @param minorVersion
     *            the minorVersion to set
     */
    public void setProtocolMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    /**
     * @return the headers
     */
    public HashMap<String, String> getHeaders() {
        return headers;
    }
}
