package ch.rollis.emma;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import ch.rollis.emma.configuration.Configuration;
import ch.rollis.emma.configuration.Configuration.Listen.Port;
import ch.rollis.emma.configuration.VirtualHost;
import ch.rollis.emma.context.ServerContext;
import ch.rollis.emma.context.ServerContextManager;

/**
 * Main class of Emma web server.
 * <p>
 * The main class of Emma web server provides the application's main method and
 * is responsible for loading the web server configuration from an xml
 * configuration file.
 * 
 * @author mrolli
 */
public final class Emma {
    /**
     * Emma version string for generated HTML output.
     */
    public static final String VERSION = "<a href=\"http://github.com/mrolli/emma\">"
            + "Emma Web Server 1.0</a> - An tiny web server in Java";

    /**
     * Emma server token used in Server response header.
     */
    public static final String SERVER_TOKEN = "Emma/1.0";

    /**
     * Filenames of default files to be served if available in case
     * a directory is requested.
     */
    public static final String[] DEFAULT_FILES = new String[] {"index.html", "index.htm"};

    /**
     * Private default constructor to forbid class instantiation.
     */
    private Emma() {
    }

    /**
     * Emma's main method to warm up the application.
     * <p>
     * Responsibilities include setting up dependencies, starting socket
     * listeners
     * threads for each TcP port configured and finally waiting for a shutdown
     * signal (shutdown, exit, quit) on stdin and if requested to finally
     * shutdown
     * the application by interrupting all socket listener threads.
     * <p>
     * Command line arguments allowed is an alternative configuration filename
     * to parse.
     *
     * @param args
     *            Array of cli arguments
     */
    public static void main(final String[] args) {
        String configFile = "./config/server.xml";

        Logger logger = Logger.getLogger("server.log");
        logger.log(Level.INFO, "Starting emma");

        try {
            if (args.length > 0) {
                configFile = args[0];
            }
            Configuration config = loadConfiguration(configFile);

            // prepare SSL KeyStore
            Configuration.KeyStore keyStore = config.getKeyStore();
            System.setProperty("javax.net.ssl.keyStore", keyStore.getPath());
            System.setProperty("javax.net.ssl.keyStorePassword", keyStore.getPassword());

            // prepare ServerContextManager
            ServerContextManager scm = new ServerContextManager();
            List<VirtualHost> vhosts = config.getVirtualHosts();
            for (VirtualHost vhost : vhosts) {
                scm.addContext(ServerContext.create(vhost));
            }

            // setup SocketListeners
            ThreadGroup socketListeners = new ThreadGroup("Socket Listeners");
            SocketListenerFactory slf = new SocketListenerFactory(scm, logger);
            List<Port> ports = config.getPorts();
            for (Port port : ports) {
                SocketListener sl = slf.getListener(port.getNumber(), port.isSecured());
                Thread t = new Thread(socketListeners, sl, "SocketListener on port " + port);
                t.start();
            }

            // wait for program termination
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String cmd = in.readLine();
                if (cmd.equals("quit") || cmd.equals("exit") || cmd.equals("shutdown")) {
                    break;
                }
            }

            logger.log(Level.INFO, "Stopping emma");

            Thread[] threads = new Thread[socketListeners.activeCount()];
            socketListeners.enumerate(threads);
            for (Thread t : threads) {
                t.interrupt();
            }

            for (Thread t : threads) {
                t.join();
            }

            logger.log(Level.INFO, "Exiting emma");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Returns a configuration object after reading it in from file and parsing
     * it in with JAXB using a defined schema.
     *
     * @param configFile
     *            Configuration filename
     * @return Configuration object built
     * @throws Exception
     *             in case the configuration file cannot be parsed or is not
     *             available
     */
    public static Configuration loadConfiguration(final String configFile) throws Exception {
        try {
            JAXBContext jc = JAXBContext.newInstance(Configuration.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            SchemaFactory schemaFactory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("./config/schema.xsd"));
            unmarshaller.setSchema(schema);
            return (Configuration) unmarshaller.unmarshal(new File(configFile));
        } catch (Exception e) {
            throw new Exception("Unable to start Emma due to a configuration error", e);
        }
    }
}
