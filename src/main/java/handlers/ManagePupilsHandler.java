package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import models.Student;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.StudentService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ManagePupilsHandler implements HttpHandler {
    StudentService studentService = new StudentService();
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        List<Student> studentList = studentService.getStudentList() ;
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/students.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("studentList", studentList);

        String response = template.render(model);



        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
