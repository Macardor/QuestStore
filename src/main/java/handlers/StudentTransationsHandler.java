package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.ItemDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import helpers.CookieHandler;
import models.Item;
import models.ItemTransaction;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class StudentTransationsHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("enter2");
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 1) {
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }
        System.out.println("enter3");
        String method = httpExchange.getRequestMethod();
        ItemDAOImplementation itemDAOImplementation = new ItemDAOImplementation();
        List<ItemTransaction> transactionItemsList = itemDAOImplementation.getUsertransactionList(studentDAOImplementation.getStudentId(user));
        int coins = studentDAOImplementation.showUserCoins(user.getId());
        System.out.println("enter4");
        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/transactions.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("itemsTransactionsLists", transactionItemsList);
            model.with("coins", coins);
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

    }
}

