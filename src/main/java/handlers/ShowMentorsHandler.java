package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.CreepDAOImplementation;
import helpers.CookieHandler;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ShowMentorsHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 3){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }

        String method = httpExchange.getRequestMethod();
        CreepDAOImplementation creepDAOImplementation = new CreepDAOImplementation();
        List<User> showMentorsList = creepDAOImplementation.showAllMentors();

        if (method.equals("GET")){
            System.out.println("vjhghjgjh");
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/showMentors.twig");
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
