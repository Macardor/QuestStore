package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controllers.CreepController;
import helpers.CookieHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;

public class CreepHandler implements HttpHandler {

    private CreepController creepController = new CreepController();
    private CookieHandler cookieHandler = new CookieHandler();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        cookieHandler.cookieChecker(httpExchange);
        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/CreepMainPage.twig");
            JtwigModel model = JtwigModel.newModel();
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }



}


