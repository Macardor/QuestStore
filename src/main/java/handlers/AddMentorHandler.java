package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.CreepDAOImplementation;
import helpers.CookieHandler;
import models.Mentor;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class AddMentorHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 3){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }

        CreepDAOImplementation creepDAOImplementation = new CreepDAOImplementation();
        String method = httpExchange.getRequestMethod();
        String response = "";

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/addMentor.twig");
            JtwigModel model = JtwigModel.newModel();
            response = template.render(model);
        }

        if (method.equals("POST")){
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);
            String login = inputs.get("login").toString();
            String password = inputs.get("password").toString();
            String firstName = inputs.get("firstName").toString();
            String lastName = inputs.get("lastName").toString();
            System.out.println(login +password+ firstName+lastName);
            Mentor mentorToAdd = new Mentor(login,password,2,true,firstName,lastName);
            System.out.println(mentorToAdd.getLastname());
            creepDAOImplementation.addMentor(mentorToAdd);


            httpExchange.getResponseHeaders().set("Location", "/cyberStore/creep/showMentors");
            httpExchange.sendResponseHeaders(303, 0);

        }

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Map<String, String> parseFormData(String formData) {
        Map<String, String> map = new HashMap<String, String>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            String value = null;
            try {
                value = new URLDecoder().decode(keyValue[1], "UTF-8");
                map.put(keyValue[0], value);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
