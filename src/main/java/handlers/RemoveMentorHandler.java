package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.CreepDAOImplementation;
import helpers.CookieHandler;
import models.Mentor;
import models.Student;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.StudentService;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveMentorHandler implements HttpHandler {
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


        List<User> mentorList = creepDAOImplementation.showAllMentors() ;
        String response = "";


        if (method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/removeMentor.twig");
            JtwigModel model = JtwigModel.newModel();

            model.with("mentorList", mentorList);

            response = template.render(model);
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            int userIdToDelete = Integer.parseInt(inputs.get("userId").toString());

            creepDAOImplementation.setMentorToUnactive(userIdToDelete);
            httpExchange.getResponseHeaders().set("Location", "/creep/showMentors");
            httpExchange.sendResponseHeaders(303,0);
//            System.out.println(Integer.parseInt(inputs.get("userId").toString()));
        }
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


    private Map<String, String> parseFormData(String formData) {
        Map<String, String> map = new HashMap<String, String>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
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

