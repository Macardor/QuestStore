//package handlers.mentor.coincubator;
//
//import DAO.*;
//import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
//import helpers.CookieHandler;
//import models.Coincubator;
//import models.User;
//import org.jtwig.JtwigModel;
//import org.jtwig.JtwigTemplate;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.List;
//
//public class CoincubatorListHandler implements HttpHandler {
//    CoincubatorDAO coincubatorDAO = new CoincubatorDAO();
//    StudentDAO studentDAO = new StudentDAO();
//    User user = null;
//    CookieHandler cookieHandler = new CookieHandler();
//    @Override
//    public void handle(HttpExchange httpExchange) throws IOException {
//        user = cookieHandler.cookieChecker(httpExchange);
//        if (user == null || user.getUserType() != 2) {
//            httpExchange.getResponseHeaders().set("Location", "/login");
//            httpExchange.sendResponseHeaders(303, 0);
//        }
//        //TODO waiting for refactor
//        String method = httpExchange.getRequestMethod();
//        CoincubatorDAO coincubatorDAO = new CoincubatorDAO();
//        List<Coincubator> coincubatorsList = coincubatorDAO.getAllCoincubators();
//        int coins = studentDAO.showUserCoins(user.getId());
//
//        if (method.equals("GET")) {
//            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/coincubator.twig");
//            JtwigModel model = JtwigModel.newModel();
//            model.with("coincubatorsList", coincubatorsList);
//            model.with("coins", coins);
//            String response = template.render(model);
//
//            httpExchange.sendResponseHeaders(200, response.length());
//            OutputStream os = httpExchange.getResponseBody();
//            os.write(response.getBytes());
//            os.close();
//        }
//    }
//}