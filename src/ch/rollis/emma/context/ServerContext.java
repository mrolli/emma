package ch.rollis.emma.context;

import java.io.File;
import java.util.ArrayList;

public class ServerContext {
    private final String serverName;

    private final ArrayList<String> serverAliases;

    private final File documentRoot;

    private boolean isDefaultContext = false;

    private boolean allowsIndexes = false;

    public ServerContext(String name, File docRoot) {
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

    public void addServerAlias(String alias) {
        serverAliases.add(alias);
    }

    /**
     * @return the documentRoot
     */
    public File getDocumentRoot() {
        return documentRoot;
    }

    public boolean isDefaultContext() {
        return isDefaultContext;
    }

    public void setDefaultContext(boolean flag) {
        isDefaultContext = flag;
    }

    public boolean allowsIndexes() {
        return allowsIndexes;
    }

    public void setAllowsIndexes(boolean flag) {
        allowsIndexes = flag;
    }

}
