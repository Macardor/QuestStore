package handlers.student;

import DAO.CoincubatorDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import DAO.StudentDAO;
import helpers.CookieHandler;
import models.Coincubator;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.CoincubatorService;
import services.StudentService;

import java.io.*;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentCoincubatorHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();
    CoincubatorDAO coincubatorDAO = new CoincubatorDAO();
    CoincubatorService coincubatorService = new CoincubatorService();
    StudentService studentService = new StudentService();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 1){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }



        ResultSet resultSet = coincubatorDAO.getAllCoincubatorsFromDb();
        List<Coincubator> coincubatorsList = coincubatorService.showAllCoincubators(resultSet);
        String method = httpExchange.getRequestMethod();
        String response = "";
        StudentDAO studentDAO = new StudentDAO();
        int coins = studentDAO.getStudentCoins(user.getId());

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/coincubator.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("coincubatorsList", coincubatorsList);
            model.with("coins", coins);
            response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        if (method.equals("POST")){
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);
            int coincubatorCoinsToPay = Integer.parseInt(inputs.get("coincubatorCoinsToPay").toString());
            int coincubatorID =  Integer.parseInt(inputs.get("coincubatorID").toString());
            if (studentService.checkIfUserHasEnoughCoins(studentDAO.getStudentId(user), coincubatorCoinsToPay)) {
                coincubatorDAO.donateToCoincubatorDb(studentDAO.getStudentId(user), coincubatorID, coincubatorCoinsToPay);
            }

            httpExchange.getResponseHeaders().add("Location", "/student/coincubator");
            httpExchange.sendResponseHeaders(303, 0);
        }
    }
    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            // We have to decode the value because it's urlencoded. see: https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}
