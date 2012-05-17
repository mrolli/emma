package ch.rollis.emma.util;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Brief log formatter extends java.util.logging.Formatter to be able to only
 * log the message to a logger without any other metadata.
 * 
 * @author mrolli
 */
public class BriefLogFormatter extends Formatter {
    /**
     * Line separator to used based on system's default.
     */
    private static final String LINE_SEP = System.getProperty("line.separator");

    @Override
    public String format(final LogRecord record) {
        return new StringBuilder().append(record.getMessage()).append(LINE_SEP).toString();
    }

}
