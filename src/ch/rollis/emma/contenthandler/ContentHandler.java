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

import ch.rollis.emma.context.ServerContext;
import ch.rollis.emma.request.Request;
import ch.rollis.emma.response.Response;

/**
 * Content handler interface implemented by all content handlers.
 * 
 * @author mrolli
 */
public interface ContentHandler {
    /**
     * Process a request within a given server context, generates a response and
     * return it.
     * 
     * @param request
     *            The request to process
     * @param context
     *            The server context the request was received
     * @return The generated response based on the request
     * @throws Exception
     *             In case an exception was encountered and could be handled
     */
    Response process(Request request, ServerContext context) throws Exception;
}
