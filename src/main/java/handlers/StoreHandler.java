package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.ItemService;

import java.io.IOException;
import java.io.OutputStream;

public class StoreHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        String response = null;
        ItemService is = new ItemService();

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/store.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("itemList", is.getItemsList());
            System.out.println("dupa1");
            response = template.render(model);
            System.out.println("dupa2");
        }


        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
