package ch.rollis.emma.contenthandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import ch.rollis.emma.HttpRequest;
import ch.rollis.emma.HttpResponse;
import ch.rollis.emma.HttpResponseStatus;
import ch.rollis.emma.HttpServerConfig;

public class FileHandler implements ContentHandler {
    private final HttpRequest request;

    public FileHandler(HttpRequest request) {
        this.request = request;
    }

    @Override
    public HttpResponse process() throws IOException {
        HttpResponse response = new HttpResponse();
        response.setProtocol(request.getProtocol());

        File rootDir = new File("./vhosts/default/public_html").getCanonicalFile();
        File file = new File(rootDir, request.getRequestURI().getPath()).getCanonicalFile();

        String extension = HttpServerConfig.getExtension(file);
        String contentType = HttpServerConfig.MIME_TYPES.get(extension);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        response.setStatus(HttpResponseStatus.OK);
        response.setContentType(contentType);
        response.setContentLength(String.valueOf(file.length()));
        response.setLastModified(new Date(file.lastModified()).toString());

        byte fileContent[] = new byte[(int) file.length()];
        new FileInputStream(file).read(fileContent);
        response.setEntity(fileContent);

        return response;
    }
}
