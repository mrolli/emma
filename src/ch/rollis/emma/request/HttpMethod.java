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
 * Enumeration of all methods defined in HTTP/1.0 and HTTP/1.1.
 *
 * The enumartion contains all methods defined in rfc1945 and rfc2616.
 *
 * @author mrolli
 */
public enum HttpMethod {
    /**
     * The OPTIONS method represents a request for information about the
     * communication options available on the request/response chain
     * identified by the Request-URI.
     */
    OPTIONS,

    /**
     * The GET method means retrieve whatever information (in the form of an
     * entity) is identified by the Request-URI.
     */
    GET,

    /**
     * The HEAD method is identical to GET except that the server MUST NOT
     * return a message-body in the response.
     */
    HEAD,

    /**
     * The POST method is used to request that the origin server accept the
     * entity enclosed in the request as a new subordinate of the resource
     * identified by the Request-URI in the Request-Line.
     */
    POST,

    /**
     * The PUT method requests that the enclosed entity be stored under the
     * supplied Request-URI.
     */
    PUT,

    /**
     * The DELETE method requests that the origin server delete the resource
     * identified by the Request-URI.
     */
    DELETE,

    /**
     * The TRACE method is used to invoke a remote, application-layer loop-
     * back of the request message.
     */
    TRACE,

    /**
     * This specification reserves the method name CONNECT for use with a
     * proxy that can dynamically switch to being a tunnel (e.g. SSL
     * tunneling).
     */
    CONNECT
}

