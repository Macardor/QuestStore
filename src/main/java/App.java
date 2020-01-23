import com.sun.net.httpserver.HttpServer;
import handlers.*;
import handlers.StudentCoincubatorHandler;
import handlers.mentor.store.StoreHandler;
import handlers.mentor.students.AddStudentHandler;
import handlers.mentor.students.PupilsListHandler;
import handlers.mentor.students.RemoveStudentHandler;

import java.net.InetSocketAddress;


public class App {
    public static void main(String[] args) throws Exception {
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // set routes

        server.createContext("/cyberStore", new loginHandler());
        server.createContext("/cyberStore/student/coincubator", new StudentCoincubatorHandler());
        server.createContext("/cyberStore/creep", new CreepHandler());
        server.createContext("/cyberStore/creep/showMentors", new ShowMentorsHandler());
        server.createContext("/cyberStore/creep/addMentor", new AddMentorHandler());
        server.createContext("/mentor/add-student", new AddStudentHandler());
        //Mentor handler
        server.createContext("/cyberStore/student", new StudentLoginPageHandler());
        server.createContext("/mentor/store", new StoreHandler());
        server.createContext("/mentor/students", new PupilsListHandler()); //all students
        server.createContext("/mentor/homepage", new MentorHandler());
        server.createContext("/mentor/remove-student", new RemoveStudentHandler()); //remove student menu
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
