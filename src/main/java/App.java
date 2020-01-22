import com.sun.net.httpserver.HttpServer;
import handlers.*;

import java.net.InetSocketAddress;


public class App {
    public static void main(String[] args) throws Exception {
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // set routes

        server.createContext("/cyberStore", new loginHandler());
        server.createContext("/cyberStore/student", new loginHandler());
        server.createContext("/cyberStore/creep", new CreepHandler());
        server.createContext("/cyberStore/creep/showMentors", new ShowMentorsHandler());
        server.createContext("/cyberStore/creep/addMentor", new AddMentorHandler());

        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }
}
