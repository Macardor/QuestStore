package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class loginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        if(method.equals("GET")) {

            // get a template file
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/loginPage.twig");

            // create a model that will be passed to a template
            JtwigModel model = JtwigModel.newModel();

            // fill the model with values
//        model.with("test", testText);


            // render a template to a string
            String response = template.render(model);

            // send the results to a the client
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

    }


}
