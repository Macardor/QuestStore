import com.sun.net.httpserver.HttpServer;
import handlers.loginHandler;
import handlers.testHandler;

import java.net.InetSocketAddress;


public class App {
    public static void main(String[] args) throws Exception {
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // set routes

        server.createContext("/cyberStore", new loginHandler());
        server.createContext("/cyberStore/student", new loginHandler());

        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }
}
