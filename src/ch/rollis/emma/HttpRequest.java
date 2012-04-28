package ch.rollis.emma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.rollis.emma.HttpProtocolException.BadRequestException;
import ch.rollis.emma.HttpProtocolException.HttpProtocolException;


public class HttpRequest {
    private final BufferedReader reader;
    private String method;

    public HttpRequest(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input));
    }

    public void parse() throws HttpProtocolException, IOException {
        parseRequestLine();
        parseHeaders();
        // parseBody();
    }

    private void parseRequestLine() throws HttpProtocolException, IOException {
        String reqLine = reader.readLine();
        String[] reqParts = reqLine.split(" ");
        if (reqParts.length != 3) {
            throw new BadRequestException();
        }
        String tmpMethod = reqParts[0];
        method = tmpMethod;

    }

    private void parseHeaders() {

    }
    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method
     *            the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }
}
