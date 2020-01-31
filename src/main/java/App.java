import com.sun.net.httpserver.HttpServer;
import handlers.*;
import handlers.mentor.quests.AddQuestHandler;
import handlers.mentor.quests.QuestListHandler;
import handlers.mentor.quests.QuestMenuHandler;
import handlers.mentor.quests.RemoveQuestHandler;
import handlers.mentor.store.AddItemHandler;
import handlers.mentor.store.RemoveItemHandler;
import handlers.StudentCoincubatorHandler;
import handlers.mentor.store.StoreHandler;
import handlers.mentor.store.StoreMenuHandler;
import handlers.mentor.students.*;

import java.net.InetSocketAddress;


public class App {
    public static void main(String[] args) throws Exception {
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // set routes
        //Login
        server.createContext("/login", new loginHandler());
        //logout
        server.createContext("/logout", new LogoutHandler());


        //Mentor handler
        server.createContext("/mentor/homepage", new MentorHandler());
        server.createContext("/mentor/store", new StoreHandler());
        server.createContext("/mentor/students-menu", new StudentMenuHandler()); //all students
        server.createContext("/mentor/add-student", new AddStudentHandler());
//        server.createContext("/mentor/student-menu", new StudentMenuHandler());
        server.createContext("/mentor/remove-student", new RemoveStudentHandler()); //remove student menu
        server.createContext("/mentor/add-quest", new AddQuestHandler());
        server.createContext("/mentor/remove-quest", new RemoveQuestHandler());
        server.createContext("/mentor/add-item", new AddItemHandler());
        server.createContext("/mentor/remove-item", new RemoveItemHandler());
        server.createContext("/mentor/students", new PupilsListHandler());
        server.createContext("/mentor/edit-student", new EditStudentHandler());
        server.createContext("/mentor/quest-menu", new QuestMenuHandler());
        server.createContext("/mentor/quest-list", new QuestListHandler());
        server.createContext("/mentor/store-menu", new StoreMenuHandler());


        //Student handler
        server.createContext("/student", new StudentLoginPageHandler());
        server.createContext("/student/coincubator", new StudentCoincubatorHandler());
        server.createContext("/student/quests", new StudentQuestsHandler());
        server.createContext("/student/store", new StudentStoreHandler());
        server.createContext("/student/inventory", new StudentInventoryHandler());
        server.createContext("/student/transactions", new StudentTransationsHandler());
        server.createContext("/student/editProfile", new StudentEditProfile());

        //CreepHandler
        server.createContext("/creep", new CreepHandler());
        server.createContext("/creep/showMentors", new ShowMentorsHandler());
        server.createContext("/creep/addMentor", new AddMentorHandler());
        server.createContext("/creep/editMentor", new EditMentorHandler());
        server.createContext("/creep/removeMentor", new RemoveMentorHandler());




        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }
}
