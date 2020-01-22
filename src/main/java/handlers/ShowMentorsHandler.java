package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.CreepDAOImplementation;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ShowMentorsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        CreepDAOImplementation creepDAOImplementation = new CreepDAOImplementation();
        List<User> showMentorsList = creepDAOImplementation.showAllMentors();
        System.out.println(showMentorsList.size());
        System.out.println("dupa1");

        if (method.equals("GET")){
            System.out.println("dupa2");
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/showMentors.twig");
            JtwigModel model = JtwigModel.newModel();
            System.out.println("dupa3");
            model.with("mentorsList", showMentorsList);
            String response = template.render(model);

            System.out.println("dupa3");
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            System.out.println("dupe4");
            os.write(response.getBytes());
            os.close();
            System.out.println("dupa5");
        }
    }


}
