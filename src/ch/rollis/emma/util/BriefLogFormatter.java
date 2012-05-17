/**
 * Copyright (c) 2012 Michael Rolli - github.com/mrolli/emma
 * All rights reserved.
 * 
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 3.0 Switzerland
 * License. To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/3.0/ch/
 * or send a letter to Creative Commons, 444 Castro Street,
 * Suite 900, Mountain View, California, 94041, USA.
 */
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
