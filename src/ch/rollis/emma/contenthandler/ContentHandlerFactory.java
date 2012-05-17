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
package ch.rollis.emma.contenthandler;

import ch.rollis.emma.request.Request;

/**
 * 
 * @author mrolli
 */
public class ContentHandlerFactory {
    /**
     * Factory method instantiates and return content handlers based on the
     * request received.
     * 
     * @param request
     *            The request to generate a content handler for
     * @return The content handler generated
     */
    public ContentHandler getHandler(final Request request) {
        ContentHandler handler = new FileHandler();
        return handler;
    }
}
