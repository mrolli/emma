package ch.rollis.emma.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateConverter {
    /**
     * 
     */
    private static SimpleDateFormat rfc1123;

    private static SimpleDateFormat logFormat;

    static {
        rfc1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        rfc1123.setTimeZone(TimeZone.getTimeZone("GMT"));

        logFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    }

    public static String formatRfc1123(Date date) {
        return rfc1123.format(date);
    }

    public static String formatLog(Date date) {
        return logFormat.format(date);
    }

    public static Date dateFromString(String date) throws DateConverterException {
        try {
            return rfc1123.parse(date);
        } catch (ParseException e) {
            // do nothing
        }

        try {
            return logFormat.parse(date);
        } catch (ParseException e) {
            // do nothing
        }

        // no conversion was possible
        throw new DateConverterException("Unable to parse date " + date);
    }
}
