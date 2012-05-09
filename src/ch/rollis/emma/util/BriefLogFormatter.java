package ch.rollis.emma.util;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class BriefLogFormatter extends Formatter {

    private static final String lineSep = System.getProperty("line.separator");
    /**
     * A Custom format implementation that is designed for brevity.
     */
    @Override
    public String format(LogRecord record) {
        return new StringBuilder().append(record.getMessage()).append(lineSep).toString();
    }

}