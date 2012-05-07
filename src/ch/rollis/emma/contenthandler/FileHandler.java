package ch.rollis.emma.contenthandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import ch.rollis.emma.context.ServerContext;
import ch.rollis.emma.request.Request;
import ch.rollis.emma.response.Response;
import ch.rollis.emma.response.ResponseFactory;
import ch.rollis.emma.response.ResponseStatus;
import ch.rollis.emma.util.DateConverter;
import ch.rollis.emma.util.MimeTypes;

public class FileHandler implements ContentHandler {
    @Override
    public Response process(Request request, ServerContext context) {
        File docRoot, file;

        ResponseFactory responseFacotry = new ResponseFactory();
        Response response = responseFacotry.getResponse(request);

        try {
            docRoot = context.getDocumentRoot().getCanonicalFile();

            file = new File(docRoot, request.getRequestURI().getPath()).getCanonicalFile();

            String contentType = MimeTypes.MIME_TYPES.get(MimeTypes.getExtension(file));
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            response.setStatus(ResponseStatus.OK);
            response.setContentType(contentType);
            response.setContentLength(String.valueOf(file.length()));
            response.setLastModified(DateConverter.formatRfc1123(new Date(file.lastModified())));

            byte fileContent[] = new byte[(int) file.length()];
            new FileInputStream(file).read(fileContent);
            response.setEntity(fileContent);

            return response;

        } catch (FileNotFoundException e) {
            return responseFacotry.getResponse(request, ResponseStatus.NOT_FOUND);
        } catch (IOException e) {
            return responseFacotry.getResponse(request, ResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
