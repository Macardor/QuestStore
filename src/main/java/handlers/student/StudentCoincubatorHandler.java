package handlers.student;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import DAO.CoincubatorDAO;
import DAO.StudentDAO;
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

public class StudentCoincubatorHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();
    CoincubatorDAO coincubatorDAO = new CoincubatorDAO();
    CoincubatorService coincubatorService = new CoincubatorService();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 1){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }
        String method = httpExchange.getRequestMethod();
        ResultSet resultSet = coincubatorDAO.getAllCoincubatorsFromDb();
        List<Coincubator> coincubatorsList = coincubatorService.showAllCoincubators(resultSet);
        StudentDAO studentDAO = new StudentDAO();
        int coins = studentDAO.showUserCoins(user.getId());

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
