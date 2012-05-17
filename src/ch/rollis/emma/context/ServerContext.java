package ch.rollis.emma.context;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.rollis.emma.configuration.VirtualHost;
import ch.rollis.emma.util.BriefLogFormatter;

/**
 * The server context class encapsulates relevant data about a specific server
 * context.
 * <p>
 * The server context consists of a server name and its document root and
 * optionally a context logger and aliases by which it is also addressable.
 * Further it has some flags which control features i.e. directory listing.
 * 
 * @author mrolli
 */
public final class ServerContext {
    /**
     * Primary server name.
     */
    private final String serverName;

    /**
     * Aliases this server context listens on.
     */
    private final ArrayList<String> serverAliases;

    /**
     * Document root of server context.
     */
    private final File documentRoot;

    /**
     * Denotes if this server context is the default context.
     */
    private boolean isDefault = false;

    /**
     * Denotes if server context allow listing directories.
     */
    private boolean allowsIndexes = false;

    /**
     * Logger instance of server context.
     */
    private Logger logger;

    /**
     * Class constructor initializes object with its dependencies.
     * 
     * @param name
     *            Primary name of the server context
     * @param docRoot
     *            Document root of server context
     */
    private ServerContext(final String name, final File docRoot) {
        serverName = name;
        documentRoot = docRoot;
        serverAliases = new ArrayList<String>();
    }

    /**
     * Returns the primary server name of this context.
     * 
     * @return the serverName
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Returns a list of aliaes the context listens to.
     * 
     * @return The list of serverAliases
     */
    public ArrayList<String> getServerAliases() {
        return serverAliases;
    }

    /**
     * Adds an alias to the list of aliases of this context.
     * 
     * @param alias
     *            The alias the context listens to
     */
    private void addServerAlias(final String alias) {
        serverAliases.add(alias);
    }

    /**
     * @return the documentRoot
     */
    public File getDocumentRoot() {
        return documentRoot;
    }

    /**
     * Checks if this server context is default.
     * 
     * @return True if context is default; false otherwise
     */
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * Sets boolean flag if this server context is default.
     * 
     * @param flag
     *            True if context is default
     */
    private void setDefault(final boolean flag) {
        isDefault = flag;
    }

    /**
     * Checks if listing directories is allowed.
     * 
     * @return True if listing is allowed, false otherwise.
     */
    public boolean allowsIndexes() {
        return allowsIndexes;
    }

    /**
     * Sets booelean flag that denotes if listing directories is allowed.
     * 
     * @param flag
     *            True if listing directories is allowed
     */
    private void setAllowsIndexes(final boolean flag) {
        allowsIndexes = flag;
    }

    /**
     * Log a message with a parent throwable to the log instance.
     * 
     * @param level
     *            The log level to log the message with
     * @param msg
     *            The log message to log
     * @param thrown
     *            The throwable associated with this log message
     */
    public void log(final Level level, final String msg, final Throwable thrown) {
        if (logger != null) {
            logger.log(level, msg, thrown);
        }
    }

    /**
     * Log a message to the log instance.
     * 
     * @param level
     *            The log level to log the message with
     * @param msg
     *            The log message to log
     */
    public void log(final Level level, final String msg) {
        if (logger != null) {
            logger.log(level, msg);
        }
    }

    /**
     * Set the logger instance for this server context.
     * 
     * @param loggerInstance
     *            The logger instance to use
     */
    private void setLogger(final Logger loggerInstance) {
        this.logger = loggerInstance;
    }

    /**
     * The server context factory method generates new server context objects
     * based on virtual host configuration objects.
     * 
     * @param vhost
     *            The virtual host configuration
     * @return The server context created
     * @throws ServerContextException
     *             In case the generation was not successful
     */
    public static ServerContext create(final VirtualHost vhost) throws ServerContextException {
        File docRoot = new File(vhost.getDocumentRoot());
        if (!docRoot.exists() || !docRoot.isDirectory()) {
            throw new ServerContextException("Document root does not exist for "
                    + vhost.getServerName());
        }
        ServerContext context = new ServerContext(vhost.getServerName(), docRoot);

        String logFilename = vhost.getLogFilename();
        if (!logFilename.equals("")) {
            try {
                FileHandler handler = new FileHandler(vhost.getLogFilename(), true);
                handler.setFormatter(new BriefLogFormatter());
                Logger logger = Logger.getLogger(context.getServerName());
                logger.addHandler(handler);
                context.setLogger(logger);
            } catch (IOException e) {
                throw new ServerContextException("Error with log file." + vhost.getServerName(), e);
            }
        }

        for (String alias : vhost.getAliases()) {
            context.addServerAlias(alias);
        }

        context.setDefault(vhost.isDefault());
        context.setAllowsIndexes(vhost.isAllowIndexes());
        return context;
    }

}

