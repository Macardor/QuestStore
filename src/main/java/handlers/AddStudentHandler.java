package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import models.Student;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.StudentService;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class AddStudentHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        StudentService studentService = new StudentService();
        String method = httpExchange.getRequestMethod();
        System.out.println(method);

        String response = "";


        if (method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/add-student.twig");
            JtwigModel model = JtwigModel.newModel();

            response = template.render(model);
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/add-student.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("login", inputs.get("login"));
            model.with("password", inputs.get("password"));
            model.with("firstName", inputs.get("firstname"));
            model.with("lastName", inputs.get("lastname"));
            response = template.render(model);
            System.out.println(inputs.get("login").toString() + inputs.get("password").toString() + inputs.get("firstname").toString() + inputs.get("lastname").toString());
            studentService.addNewStudent(new Student(inputs.get("login").toString(), inputs.get("password").toString(), 1, true, inputs.get("firstname").toString(), inputs.get("lastname").toString()));
        }
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
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
