package ch.rollis.emma.contenthandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import ch.rollis.emma.HttpRequest;
import ch.rollis.emma.HttpServerConfig;
import ch.rollis.emma.response.HttpResponse;
import ch.rollis.emma.response.HttpResponseFactory;
import ch.rollis.emma.response.HttpResponseStatus;
import ch.rollis.emma.util.DateFormatter;

public class FileHandler implements ContentHandler {
    private final HttpRequest request;

    public FileHandler(HttpRequest request) {
        this.request = request;
    }

    @Override
    public HttpResponse process() {
        File rootDir, file;

        HttpResponseFactory responseFacotry = new HttpResponseFactory();
        HttpResponse response = responseFacotry.getResponse(request);

        try {
            rootDir = new File("./vhosts/default/public_html").getCanonicalFile();

            file = new File(rootDir, request.getRequestURI().getPath()).getCanonicalFile();

            String extension = HttpServerConfig.getExtension(file);
            String contentType = HttpServerConfig.MIME_TYPES.get(extension);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            response.setStatus(HttpResponseStatus.OK);
            response.setContentType(contentType);
            response.setContentLength(String.valueOf(file.length()));
            response.setLastModified(DateFormatter.formatRfc1123(new Date(file.lastModified())));

            byte fileContent[] = new byte[(int) file.length()];
            new FileInputStream(file).read(fileContent);
            response.setEntity(fileContent);

            return response;

        } catch (FileNotFoundException e) {
            return responseFacotry.getResponse(HttpResponseStatus.NOT_FOUND);
        } catch (IOException e) {
            return responseFacotry.getResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
