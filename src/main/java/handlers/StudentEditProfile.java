package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.ItemDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import helpers.CookieHandler;
import models.Item;
import models.Student;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.StudentService;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentEditProfile implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
    StudentService studentService = new StudentService();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 1){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }

        String method = httpExchange.getRequestMethod();
        int coins = studentDAOImplementation.showUserCoins(user.getId());

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/studentEditProfile.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("user", user);
            model.with("coins", coins);
            String response = template.render(model);
            System.out.println(response);

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

            String login = inputs.get("login").toString();
            String password = inputs.get("password").toString();
            String firstName = inputs.get("firstName").toString();
            String lastName = inputs.get("lastName").toString();
            int studentDetailsId = studentService.getUserDetailsId(user);

            Student studentToEdit = new Student(login,password,2,true,firstName,lastName);
            studentService.editStudent(studentToEdit,studentDetailsId);

            httpExchange.getResponseHeaders().set("Location", "/student");
            httpExchange.sendResponseHeaders(303, 0);
        }
    }

    private Map<String, String> parseFormData(String formData) {
        Map<String, String> map = new HashMap<String, String>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            String value = null;
            try {
                value = new URLDecoder().decode(keyValue[1], "UTF-8");
                map.put(keyValue[0], value);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
