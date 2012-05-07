package ch.rollis.emma;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.rollis.emma.context.ServerContext;
import ch.rollis.emma.context.ServerContextManager;

public class Emma {
    public static final String VERSION = "<a href=\"http://github.com/mrolli/emma\">Emma Web Server 1.0</a> - An tiny web server in Java";
    public static final String SERVER_TOKEN = "Emma/1.0";

    public static void main(String[] args) {
        // prepare SSL KeyStore
        System.setProperty("javax.net.ssl.keyStore", "emma.keystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "geheim");

        Logger logger = Logger.getLogger("server.log");
        logger.log(Level.INFO, "Starting emma");

        HashMap<Integer, Boolean> ports = new HashMap<Integer, Boolean>();
        ports.put(8080, false);
        ports.put(8443, true);

        ServerContextManager scm = new ServerContextManager();
        File docRoot = new File("./vhosts/default/public_html");
        ServerContext con = new ServerContext("localhost", docRoot);
        con.setDefaultContext(true);
        try {
            scm.addContext(con);

            ThreadGroup socketListeners = new ThreadGroup("Socket Listeners");

            for (int port : ports.keySet()) {
                SocketListener sl = new SocketListener(port, scm, logger);
                sl.setSecured(ports.get(port));
                Thread t = new Thread(socketListeners, sl, "SocketListener on port " + port);
                t.start();
            }

            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(1000);
            }

            logger.log(Level.INFO, "Stopping emma");

            Thread[] threads = new Thread[socketListeners.activeCount()];
            socketListeners.enumerate(threads);
            for (Thread t : threads) {
                t.interrupt();
                t.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        logger.log(Level.INFO, "Exiting emma");
        System.exit(0);
    }
}
