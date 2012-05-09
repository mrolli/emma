package ch.rollis.emma.context;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.rollis.emma.configuration.VirtualHost;
import ch.rollis.emma.util.BriefLogFormatter;

public class ServerContext {
    private final String serverName;

    private final ArrayList<String> serverAliases;

    private final File documentRoot;

    private boolean isDefault = false;

    private boolean allowsIndexes = false;

    private Logger logger;

    private ServerContext(String name, File docRoot) {
        serverName = name;
        documentRoot = docRoot;
        serverAliases = new ArrayList<String>();
    }

    /**
     * @return the serverName
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @return the serverAliases
     */
    public ArrayList<String> getServerAliases() {
        return serverAliases;
    }

    private void addServerAlias(String alias) {
        serverAliases.add(alias);
    }

    /**
     * @return the documentRoot
     */
    public File getDocumentRoot() {
        return documentRoot;
    }

    public boolean isDefault() {
        return isDefault;
    }

    private void setDefault(boolean flag) {
        isDefault = flag;
    }

    public boolean allowsIndexes() {
        return allowsIndexes;
    }

    private void setAllowsIndexes(boolean flag) {
        allowsIndexes = flag;
    }

    public void log(Level level, String msg, Throwable thrown) {
        if (logger != null) {
            logger.log(level, msg, thrown);
        }
    }

    public void log(Level level, String msg) {
        if (logger != null) {
            logger.log(level, msg);
        }
    }

    private void setLogger(Logger logger) {
        this.logger = logger;
    }

    public static ServerContext create(VirtualHost vhost) throws ServerContextException {
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
                throw new ServerContextException("Error with log file."
                        + vhost.getServerName());
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
