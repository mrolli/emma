package ch.rollis.emma;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.rollis.emma.context.ServerContext;
import ch.rollis.emma.context.ServerContextManager;

public class EmmaRunner {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("server.log");

        // prepare SSL KeyStore
        System.setProperty("javax.net.ssl.keyStore", "emma.keystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "geheim");

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
