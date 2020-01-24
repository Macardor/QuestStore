package handlers.mentor.students;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.CreepDAOImplementation;
import helpers.CookieHandler;
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

public class EditStudentHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    private int postIndex=1;
    private int studentDetailsId;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 2){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }

        StudentService studentService = new StudentService();
        List<Student> studentList = studentService.getActiveStudentList();

        String method = httpExchange.getRequestMethod();
        String response = "";

        if (method.equals("GET")){
            postIndex = 1;
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/edit-student-menu.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("studentList", studentList);
            response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        if (method.equals("POST") && postIndex == 1) {
            postIndex = 2;

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);
            String id = inputs.get("studentId").toString();
            int userId = Integer.parseInt(id);

            Student student = studentService.getStudentByUserId(userId);
            studentDetailsId = studentService.getUserDetailsId(student);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/edit-student.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("student", student);
            response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        if (method.equals("POST") && postIndex == 2) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map inputs = parseFormData(formData);

            studentService.editStudent(new Student(inputs.get("login").toString() ,inputs.get("password").toString(),2,true, inputs.get("firstName").toString(), inputs.get("lastName").toString()), studentDetailsId);

            httpExchange.getResponseHeaders().set("Location", "/mentor/edit-student");
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

