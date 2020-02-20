package handlers.student;

import DAO.QuestDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import DAO.ItemDAO;
import DAO.StudentDAO;
import helpers.CookieHandler;
import models.ItemTransaction;
import models.Quest;
import models.Student;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class StudentNotDoneQuestHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    StudentDAO studentDAO = new StudentDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 1) {
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }
        String method = httpExchange.getRequestMethod();

        QuestDAO questDAO = new QuestDAO();
        List<Quest> availableQuestList = questDAO.getAllQuestsNotDoneByStudent((Student) user);
        int coins = studentDAO.getStudentCoins(user.getId());



        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/transactions.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("availableQuestsList", availableQuestList);
            model.with("coins", coins);
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

    }
}

