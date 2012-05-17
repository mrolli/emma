package ch.rollis.emma.util;

import java.io.File;
import java.util.HashMap;

/**
 * Utitlity class to find mime types of files.
 * 
 * @author mrolli
 */
public final class MimeTypes {
    /**
     * file extension to mime types map.
     */
    private static final HashMap<String, String> MIME_TYPES = new HashMap<String, String>();

    /**
     * Private class constructor to deny instantiation.
     */
    private MimeTypes() {
    }

    /**
     * Work out the file's extension from filename. If there isn't one an empty
     * string is returned.
     * 
     * @param file
     *            File object to get the extension for
     * @return the extension found
     */
    public static String getExtension(final File file) {
        return MimeTypes.getExtension(file.getName());
    }

    /**
     * Work out the file's extension from filename. If there isn't one an empty
     * string is returned.
     * 
     * @param filename
     *            File name to get the extension for
     * @return the extension found
     */
    public static String getExtension(final String filename) {
        String extension = "";
        int dotPos = filename.lastIndexOf(".");
        if (dotPos >= 0) {
            extension = filename.substring(dotPos);
        }
        return extension.toLowerCase();
    }

    /**
     * Returns the mime type associated with a given file extension.
     * 
     * @param fileExtension
     *            to get the mime type for
     * @return The mime type evaluated
     */
    public static String evaluate(final String fileExtension) {
        return MIME_TYPES.get(fileExtension);
    }

    static {
        // Set up the filename extension to mime type associations.
        String ps = "application/postscript";
        MIME_TYPES.put(".ai", ps);
        MIME_TYPES.put(".ps", ps);
        MIME_TYPES.put(".eps", ps);

        String rtf = "application/rtf";
        MIME_TYPES.put(".rtf", rtf);

        String au = "audio/basic";
        MIME_TYPES.put(".au", au);
        MIME_TYPES.put(".snd", au);

        String exe = "application/octet-stream";
        MIME_TYPES.put(".bin", exe);
        MIME_TYPES.put(".dms", exe);
        MIME_TYPES.put(".lha", exe);
        MIME_TYPES.put(".lzh", exe);
        MIME_TYPES.put(".exe", exe);
        MIME_TYPES.put(".class", exe);

        String doc = "application/msword";
        MIME_TYPES.put(".doc", doc);

        String pdf = "application/pdf";
        MIME_TYPES.put(".pdf", pdf);

        String ppt = "application/powerpoint";
        MIME_TYPES.put(".ppt", ppt);

        String smi = "application/smil";
        MIME_TYPES.put(".smi", smi);
        MIME_TYPES.put(".smil", smi);
        MIME_TYPES.put(".sml", smi);

        String js = "application/x-javascript";
        MIME_TYPES.put(".js", js);

        String zip = "application/zip";
        MIME_TYPES.put(".zip", zip);

        String midi = "audio/midi";
        MIME_TYPES.put(".midi", midi);
        MIME_TYPES.put(".kar", midi);

        String mp3 = "audio/mpeg";
        MIME_TYPES.put(".mpga", mp3);
        MIME_TYPES.put(".mp2", mp3);
        MIME_TYPES.put(".mp3", mp3);

        String wav = "audio/x-wav";
        MIME_TYPES.put(".wav", wav);

        String gif = "image/gif";
        MIME_TYPES.put(".gif", gif);

        String ief = "image/ief";
        MIME_TYPES.put(".ief", ief);

        String jpeg = "image/jpeg";
        MIME_TYPES.put(".jpeg", jpeg);
        MIME_TYPES.put(".jpg", jpeg);
        MIME_TYPES.put(".jpe", jpeg);

        String png = "image/png";
        MIME_TYPES.put(".png", png);

        String tiff = "image/tiff";
        MIME_TYPES.put(".tiff", tiff);
        MIME_TYPES.put(".tif", tiff);

        String vrml = "model/vrml";
        MIME_TYPES.put(".wrl", vrml);
        MIME_TYPES.put(".vrml", vrml);

        String css = "text/css";
        MIME_TYPES.put(".css", css);

        String html = "text/html";
        MIME_TYPES.put(".html", html);
        MIME_TYPES.put(".htm", html);
        MIME_TYPES.put(".shtml", html);
        MIME_TYPES.put(".shtm", html);
        MIME_TYPES.put(".stm", html);
        MIME_TYPES.put(".sht", html);

        String txt = "text/plain";
        MIME_TYPES.put(".txt", txt);
        MIME_TYPES.put(".inf", txt);
        MIME_TYPES.put(".nfo", txt);

        String xml = "text/xml";
        MIME_TYPES.put(".xml", xml);
        MIME_TYPES.put(".dtd", xml);

        String mpeg = "video/mpeg";
        MIME_TYPES.put(".mpeg", mpeg);
        MIME_TYPES.put(".mpg", mpeg);
        MIME_TYPES.put(".mpe", mpeg);

        String avi = "video/x-msvideo";
        MIME_TYPES.put(".avi", avi);
    }
}
