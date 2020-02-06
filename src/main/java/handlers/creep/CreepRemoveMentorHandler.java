package handlers.creep;

import DAO.MentorDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import DAO.CreepDAO;
import helpers.CookieHandler;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.CreepService;

import java.io.*;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreepRemoveMentorHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();
    CreepDAO creepDAO = new CreepDAO();
    MentorDAO mentorDAO = new MentorDAO();
    CreepService creepService = new CreepService();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 3){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }

        String method = httpExchange.getRequestMethod();

        ResultSet resultSet = mentorDAO.getAllMentorsFromDb() ;
        List<User> mentorList = creepService.getAllMentorsList(resultSet);
        String response = "";


        if (method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/creep/removeMentor.twig");
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

            creepDAO.setMentorToUnactive(userIdToDelete);
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

