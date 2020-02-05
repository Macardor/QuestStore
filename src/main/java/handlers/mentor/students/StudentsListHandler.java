package handlers.mentor.students;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.CookieHandler;
import models.Student;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.StudentService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class StudentsListHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    StudentService studentService = new StudentService();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 2){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }

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
