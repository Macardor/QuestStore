package handlers.mentor.students;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import models.Student;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.StudentService;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveStudentHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        StudentService studentService = new StudentService();
        String method = httpExchange.getRequestMethod();
        System.out.println(method);

        List<Student> studentList = studentService.getActiveStudentsList() ;
        String response = "";


        if (method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/remove-student.twig");
            JtwigModel model = JtwigModel.newModel();

            model.with("studentList", studentList);

            response = template.render(model);
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/remove-student.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("studentList", studentList);

            response = template.render(model);
            studentService.deleteStudent(Integer.parseInt(inputs.get("userId").toString()));
            httpExchange.getResponseHeaders().set("Location", "/mentor/remove-student" );
            httpExchange.sendResponseHeaders(303,0);
            System.out.println(Integer.parseInt(inputs.get("userId").toString()));
        }
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


    private Map<String, String> parseFormData(String formData) {
        Map<String, String> map = new HashMap<String, String>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
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