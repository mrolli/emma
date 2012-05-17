/**
 * Classes of this package generate HTTP responses based on request.
 * <p>
 * The responsibility of a content handler is basically to generate a response
 * object based on request received. Different content handler (implementing the
 * ContentHandler interface) have different strategies how they generate a response,
 * i.e. FileHandler reads files and servers their contents.
 * <p>
 * ContentHandler must not be instantiated manually but fetched from the
 * ContentHandlerFactory. The factory chooses the correct (configured)
 * content handler based on the request that was dispatched.
 */
package ch.rollis.emma.contenthandler;
