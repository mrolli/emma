package ch.rollis.emma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpProtocolParser {
    private final BufferedReader reader;
    private HttpRequest request;
    private int majorVersion = 0;
    private int minorVersion = 9;

    private static final String SP = " ";

    public HttpProtocolParser(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input));
    }

    public HttpRequest parse() throws HttpProtocolException, IOException {
        request = new HttpRequest();
        parseRequestLine();
        if (majorVersion >= 1 && minorVersion >= 1) {
            parseHeaders();
            // parseBody();
        }
        validateRequest();
        return request;
    }

    private void parseRequestLine() throws HttpProtocolException, IOException {
        String reqLine = reader.readLine();
        String[] reqParts = reqLine.split(SP);

        if (reqParts.length == 2) {
            // Simple-Request
            try {
                HttpMethod method = HttpMethod.valueOf(reqParts[0]);
                // HTTP/0.9 only allows GET requests
                if (method.compareTo(HttpMethod.GET) != 0) {
                    throw new HttpProtocolException("Illegl method " + method + ".");
                }
                request.setMethod(method);
            } catch (IllegalArgumentException e) {
                throw new HttpProtocolException("Unknown method " + reqParts[0] + ".");
            }
            majorVersion = 0;
            minorVersion = 9;
            request.setProtocol(String.format("HTTP/" + majorVersion + "." + minorVersion));
        } else if (reqParts.length == 3) {
            // Full-Request
            try {
                HttpMethod method = HttpMethod.valueOf(reqParts[0]);
                request.setMethod(method);
            } catch (IllegalArgumentException e) {
                throw new HttpProtocolException("Unknown method " + reqParts[0] + ".");
            }
            Matcher m = Pattern.compile("HTTP/(\\d+).(\\d+)").matcher(reqParts[2]);
            if (m.matches()) {
                majorVersion = Integer.parseInt(m.group(1));
                minorVersion = Integer.parseInt(m.group(2));
                request.setProtocol(String.format("HTTP/" + majorVersion + "." + minorVersion));
            } else {
                throw new HttpProtocolException("Invalid protocol " + reqParts[2] + ".");
            }
        } else {
            // Bad Request
            throw new HttpProtocolException("Malformed request line.");
        }

        try {
            request.setRequestURI(new URI(reqParts[1]));
        } catch (URISyntaxException e) {
            throw new HttpProtocolException("Illegal URI.", e);
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
                request.addHeader(key, value.trim());
            }
        }
    }

    public void validateRequest() throws HttpProtocolException {
        if (majorVersion >= 1 && minorVersion >= 1 && request.getHeader("Host") == null) {
            throw new HttpProtocolException("No Host header present for HTTP/1.1");
        }
    }
}
