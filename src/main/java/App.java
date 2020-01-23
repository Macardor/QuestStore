import com.sun.net.httpserver.HttpServer;
import handlers.*;

import java.net.InetSocketAddress;


public class App {
    public static void main(String[] args) throws Exception {
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // set routes

        server.createContext("/login", new loginHandler());
        server.createContext("/student", new StudentLoginPageHandler());
        server.createContext("/student/coincubator", new StudentCoincubatorHandler());
        server.createContext("/student/quests", new StudentQuestsHandler());
        server.createContext("/student/store", new StudentStoreHandler());
        server.createContext("/creep", new CreepHandler());
        server.createContext("/creep/showMentors", new ShowMentorsHandler());
        server.createContext("/creep/addMentor", new AddMentorHandler());

        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }
}
