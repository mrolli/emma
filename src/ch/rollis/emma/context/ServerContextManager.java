package ch.rollis.emma.context;

import java.util.ArrayList;
import java.util.HashMap;

import ch.rollis.emma.request.Request;

public class ServerContextManager {

    private final HashMap<String, ServerContext> contexts;

    public ServerContextManager() {
        contexts = new HashMap<String, ServerContext>();
    }

    public ServerContext getContext(Request request) throws ServerContextException {
        String host = request.getHeader("Host");
        if (contexts.containsKey(host)) {
            return contexts.get(host);
        } else {
            return getDefaultContext();
        }
    }

    public void addContext(ServerContext context) throws ServerContextException {
        String name = context.getServerName();
        ArrayList<String> aliases = context.getServerAliases();

        if (contexts.containsKey(name)) {
            throw new ServerContextException(
                    "A context with the given server name already exists: " + name);
        }
        contexts.put(name, context);

        for (String alias : aliases) {
            if (contexts.containsKey(alias)) {
                throw new ServerContextException(
                        "A context with the given server name already exists: " + name);
            }
            contexts.put(alias, context);
        }

        if (context.isDefault()) {
            if (hasDefaultContext()) {
                throw new ServerContextException("Default context is already registered");
            }
            contexts.put("default", context);
        }
    }

    public boolean hasDefaultContext() {
        return contexts.containsKey("default");
    }

    public ServerContext getDefaultContext() throws ServerContextException {
        if (!hasDefaultContext()) {
            throw new ServerContextException("No default context is configured");
        }
        return contexts.get("default");
    }
}
