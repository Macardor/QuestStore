package httpHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import models.Student;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.StudentService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class StudentHandler implements HttpHandler {
    private String response = null;
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        StudentService studentService = new StudentService();
        String method = httpExchange.getRequestMethod();
        System.out.println(method); //test method

        if (method.equals("GET")){
            List<Student> studentList = studentService.getStudentList();
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/student-list.twig");
            JtwigModel model = JtwigModel.newModel();
            response = template.render(model);
        }
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
