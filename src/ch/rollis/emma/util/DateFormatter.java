package ch.rollis.emma.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormatter {
    private static SimpleDateFormat rfc1123;

    public static String formatRfc1123(Date date) {
        if (rfc1123 == null) {
            rfc1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",
                    Locale.US);
            rfc1123.setTimeZone(TimeZone.getTimeZone("GMT"));
        }

        return rfc1123.format(date);
    }
}
