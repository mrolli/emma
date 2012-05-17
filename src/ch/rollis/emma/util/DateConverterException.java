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

/**
 * Exception type thrown by DateConverter class.
 * 
 * @author mrolli
 */
@SuppressWarnings("serial")
public class DateConverterException extends Exception {
    /**
     * Single message string constructor.
     * 
     * @param msg
     *            The exception's message
     */
    public DateConverterException(final String msg) {
        super(msg);
    }
}
