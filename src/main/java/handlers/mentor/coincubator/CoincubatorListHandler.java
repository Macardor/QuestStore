package handlers.mentor.coincubator;

import DAO.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.CookieHandler;
import models.Coincubator;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.CoincubatorService;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.List;

public class CoincubatorListHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();
    CoincubatorDAO coincubatorDAO = new CoincubatorDAO();
    CoincubatorService coincubatorService = new CoincubatorService();
    StudentDAO studentDAO = new StudentDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if (user == null || user.getUserType() != 2) {
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }
        //TODO waiting for refactor
        String method = httpExchange.getRequestMethod();
        ResultSet resultSet = coincubatorDAO.getAllCoincubatorsFromDb();
        List<Coincubator> coincubatorsList = coincubatorService.getAllCoincubators(resultSet);

        int coins = studentDAO.getStudentCoins(user.getId());

        if (method.equals("GET")) {
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