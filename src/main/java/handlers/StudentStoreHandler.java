package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.ItemDAOImplementation;
import models.Item;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class StudentStoreHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        ItemDAOImplementation itemDAOImplementation = new ItemDAOImplementation();
        List<Item> itemsList = itemDAOImplementation.getItemsList();

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/store.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("itemsList", itemsList);
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
