Emma
====

Emma is a tiny little webserver in Java, hacked together in the context of an exercise in data communication lecture.

Installation/Setup
------------------

First, just clone the eclipse project directory to your environment. Emma is preconfigured (see [`./config/server.xml`](https://github.com/mrolli/emma/blob/master/config/server.xml)):

  * Sockets on ports 8080 (http) and 8443 (https)
  * Default Virtual Host: localhost
  * Virtual Host: test.localhost
  * KeyStore for SSL is set with System.property within the main method

Start Emma by invoking Emma.main(). the path to an alternate server configuration can be provided as first argument to main(). If no alternate server configuration is provided, the configuration is read from ./config/server.xml. For details on server configuration see ./config/schema.xsd.

A welcome page with additional Information like the features implemented, test cases and API-Documentation is available at http://localhost:8080/index.html

Features
--------
    
  * Support for Simple-Requests (HTTP/0.9) and Full-Requests (HTTP/1.0+) according rfc1945 and rfc2616
  * Parallel handling of all client request (Thread support)
  * Support for persistent connection aka Keep-Alive (HTTP/1.1, default, @see ch.rollis.emma.RequestHandler.requestTimeout)
  * Sockets on multiple ports (default: 8080, 8443)
  * SSL support configurable on any port (8443 default to SSL, see [`./config/server.xml`](https://github.com/mrolli/emma/blob/master/config/server.xml))
  * Implemented HTTP methods: GET, HEAD (POST, PUT, DELETE, OPTIONS not implemented, but entity sent gets parsed into the Request object)
  * Support for conditional GET with correct response code (304 - Not Modified)
  * Redirect (301 - Moved Permanently) on request to an URI without trailing slash if it referes to a directory
  * Automatic delivery of default index file (@see Emma.DEFAULT_FILES) - default: index.html, index.htm
  * Delivery of directory listing if allowed (see Virtual Hosts)
  * Support for Virtual Hosts (VH) ([`./config/server.xml`](https://github.com/mrolli/emma/blob/master/config/server.xml)):
    * "infinite" number of VHs possible
    * Each VH has its own server name and any aliases
    * Virtual host based request logging in Apache style format (including response code, content length, referer and user agent)
    * Default host will be served if no applicable VH can be found
    * Option "allowIndexes" (Directory Listings) per VH
    
Supported Header Fields: Request
-----------------------------------

  * All request headers get parsed and are stored in the Request object (unknown headers as entity headers)
  * General Headers: Connection
  * Request Headers: Host, If-Modified-Since (→ Conditional GET), Referer, User-Agent
  * Entity Headers: Content-Length

Supported Header Fields: Response
------------------------------------

  * General Headers: Date (nach rfc1135), Connection
  * Response Headers: Server, Location
  * Entity Headers: Content-Type (Mime Type based on file extensions), Content-Length, Last-Modified
  
What else?
----------

  * Human readable error responses (i.e. on 400 - Bad Request, 404 - Not found, 500 - Internal Server Error)
  * Shutdown of Emma by entering `exit`, `quit`, `shutdown` + CRLF on stdin while Emma is running
  * XML-based server configuration (JAXB; see [`./config/schema.xsd`](https://github.com/mrolli/emma/blob/master/config/schema.xsd))
  * Full API-Documentation (see <http://localhost:8080/doc/>)
  
- - -
This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Switzerland License. 
