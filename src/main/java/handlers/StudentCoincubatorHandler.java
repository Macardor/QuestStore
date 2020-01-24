package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.CoincubatorDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import helpers.CookieHandler;
import models.Coincubator;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class StudentCoincubatorHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 1){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }
        String method = httpExchange.getRequestMethod();
        CoincubatorDAOImplementation coincubatorDAOImplementation = new CoincubatorDAOImplementation();
        List<Coincubator> coincubatorsList = coincubatorDAOImplementation.getAllCoincubators();
        StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
        int coins = studentDAOImplementation.showUserCoins(user.getId());

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/coincubator.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("coincubatorsList", coincubatorsList);
            model.with("coins", coins);
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
