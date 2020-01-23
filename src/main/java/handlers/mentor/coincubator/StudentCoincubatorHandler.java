package handlers.mentor.coincubator;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.CoincubatorDAOImplementation;
import models.Coincubator;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class StudentCoincubatorHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        CoincubatorDAOImplementation coincubatorDAOImplementation = new CoincubatorDAOImplementation();
        List<Coincubator> coincubatorsList = coincubatorDAOImplementation.getAllCoincubators();

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/coincubator.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("coincubatorsList", coincubatorsList);
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
