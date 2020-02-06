package handlers.creep;

import DAO.MentorDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import DAO.CreepDAO;
import helpers.CookieHandler;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.CreepService;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.List;

public class CreepShowMentorsHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();
    MentorDAO mentorDAO = new MentorDAO();
    CreepService creepService = new CreepService();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 3){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }

        String method = httpExchange.getRequestMethod();

        ResultSet resultSet = mentorDAO.getAllMentorsFromDb();
        List<User> showMentorsList = creepService.getAllMentorsList(resultSet);

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/creep/showMentors.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("mentorsList", showMentorsList);
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }


}
