package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.CreepDAOImplementation;
import models.Mentor;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class EditMentorMenu implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        CreepDAOImplementation creepDAOImplementation = new CreepDAOImplementation();
        int mentorId = 1;
        Mentor mentor = creepDAOImplementation.getMentorById(mentorId);
        List<User> showMentorsList = creepDAOImplementation.showAllMentors();
        String method = httpExchange.getRequestMethod();
        String response = "";


    }
}
