import com.sun.net.httpserver.HttpServer;
import handlers.*;
import handlers.creep.*;
import handlers.mentor.MentorEditProfile;
import handlers.mentor.MentorLoginPageHandler;
import handlers.mentor.coincubator.*;
import handlers.mentor.quests.*;
import handlers.mentor.store.*;
import handlers.student.*;
import handlers.mentor.students.*;

import java.net.InetSocketAddress;


public class App {
    public static void main(String[] args) throws Exception {
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        // set routes
        //Login
        server.createContext("/login", new LoginHandler());
        //logout
        server.createContext("/logout", new LogoutHandler());


        //Mentor handler
        server.createContext("/mentor/homepage", new MentorLoginPageHandler());
        server.createContext("/mentor/store", new StoreHandler());
        server.createContext("/mentor/students-menu", new StudentMenuHandler());
        server.createContext("/mentor/add-student", new AddStudentHandler());
        server.createContext("/mentor/remove-student", new RemoveStudentHandler());
        server.createContext("/mentor/add-quest", new AddQuestHandler());
        server.createContext("/mentor/remove-quest", new RemoveQuestHandler());
        server.createContext("/mentor/add-item", new AddItemHandler());
        server.createContext("/mentor/remove-item", new RemoveItemHandler());
        server.createContext("/mentor/students", new StudentsListHandler());
        server.createContext("/mentor/edit-student", new EditStudentHandler());
        server.createContext("/mentor/quest-menu", new QuestMenuHandler());
        server.createContext("/mentor/quest-list", new QuestListHandler());
        server.createContext("/mentor/store-menu", new StoreMenuHandler());
        server.createContext("/mentor/editProfile", new MentorEditProfile());
        server.createContext("/mentor/coincubators-menu", new CoincubatorMenuHandler());
        server.createContext("/mentor/add-coincubator", new AddCoincubatorHandler());
        server.createContext("/mentor/edit-coincubator", new EditCoincubatorHandler());
        server.createContext("/mentor/remove-coincubator", new RemoveCoincubatorHandler());
        server.createContext("/mentor/coincubator-list", new CoincubatorListHandler());
        server.createContext("/mentor/edit-quest", new EditQuestHandler());
        server.createContext("/mentor/edit-item", new EditItemHandler());


        //Student handler
        server.createContext("/student", new StudentLoginPageHandler());
        server.createContext("/student/coincubator", new StudentCoincubatorHandler());
        server.createContext("/student/quests", new StudentQuestsHandler());
        server.createContext("/student/store", new StudentStoreHandler());
        server.createContext("/student/inventory", new StudentInventoryHandler());
        server.createContext("/student/transactions", new StudentTransactionsHandler());
        server.createContext("/student/editProfile", new StudentEditProfileHandler());

        //CreepHandler
        server.createContext("/creep", new CreepLoginPageHandler());
        server.createContext("/creep/showMentors", new CreepShowMentorsHandler());
        server.createContext("/creep/addMentor", new CreepAddMentorHandler());
        server.createContext("/creep/editMentor", new CreepEditMentorHandler());
        server.createContext("/creep/removeMentor", new CreepRemoveMentorHandler());
        server.createContext("/creep/editProfile", new CreepEditProfileHandler());




        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }
}
