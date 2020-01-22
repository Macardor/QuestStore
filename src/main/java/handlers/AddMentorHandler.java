package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.CreepDAOImplementation;
import models.Mentor;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import view.StaticUi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class AddMentorHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        CreepDAOImplementation creepDAOImplementation = new CreepDAOImplementation();
        //List<Mentor> addMentor = StaticUi.addMentor();

        if (method.equals("POST")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/addMentor.twig");
            JtwigModel model = JtwigModel.newModel();
            //model.with("addMentor", creepDAOImplementation.addMentor());
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
