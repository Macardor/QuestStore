package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.QuestDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import models.Quest;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class StudentQuestsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        QuestDAOImplementation questDAOImplementation = new QuestDAOImplementation();
        StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
        List<Quest> questsList = questDAOImplementation.getAllQuestsNotDoneByStudent(studentDAOImplementation.getStudentByUserId(1));

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/quests.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("questsList", questsList);
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
