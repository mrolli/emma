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
package ch.rollis.emma.request;

/**
 * Exception class for HTTP protocol exceptions.
 *
 * Exceptions of this type are thrown by all exceptions raised when parsing a
 * request and an illegal request is found.
 *
 * @author mrolli
 */
@SuppressWarnings("serial")
public class HttpProtocolException extends Exception {
    /**
     * Single message string constructor.
     *
     * @param msg
     *            The exception's message
     */
    public HttpProtocolException(final String msg) {
        super(msg);
    }

    /**
     * Single parent exception constructor.
     * 
     * @param cause
     *            The exception to rethrown with this type
     */
    public HttpProtocolException(final Exception cause) {
        super(cause);
    }

    /**
     * Constructor to setup an exception consisting of a exception
     * message and a parent exception.
     *
     * @param msg
     *            The exception's message
     * @param cause
     *            The exception to rethrow with this type
     */
    public HttpProtocolException(final String msg, final Exception cause) {
        super(msg, cause);
    }
}
