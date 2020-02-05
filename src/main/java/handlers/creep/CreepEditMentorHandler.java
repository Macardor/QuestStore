package handlers.creep;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.CreepDAO;
import helpers.CookieHandler;
import models.Mentor;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreepEditMentorHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    private int postIndex=1;
    private int mentorDetailsId;
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 3){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }

        CreepDAO creepDAO = new CreepDAO();
        List<User>showMentorsList = creepDAO.showAllMentors();
        String method = httpExchange.getRequestMethod();
        String response = "";

        if (method.equals("GET")){
            postIndex = 1;
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/creep/editMentorMenu.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("mentorsList", showMentorsList);
            response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        if (method.equals("POST") && postIndex == 1) {
            postIndex = 2;

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
//
            Map inputs = parseFormData(formData);
            String id = inputs.get("mentorId").toString();
//            System.out.println(id);
//            String password = inputs.get("password").toString();
//            String firstName = inputs.get("firstName").toString();
//            String lastName = inputs.get("lastName").toString();
//            System.out.println(login + password + firstName + lastName);
            int userId = Integer.parseInt(id);

            Mentor mentorId = creepDAO.getMentorById(userId);
            mentorDetailsId = creepDAO.getUserDetailsId(mentorId);


            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/creep/editMentor.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("mentor", mentorId);
            response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

            //Mentor mentorToAdd = new Mentor(login, password, 2, true, firstName, lastName);
            //System.out.println(mentorToAdd.getLastname());
            //creepDAOImplementation.addMentor(mentorToAdd);

        }

        if (method.equals("POST") && postIndex == 2) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            String login = inputs.get("login").toString();
            String password = inputs.get("password").toString();
            String firstName = inputs.get("firstName").toString();
            String lastName = inputs.get("lastName").toString();

            creepDAO.editMentor(new Mentor(login,password,2,true,firstName,lastName), mentorDetailsId);


            httpExchange.getResponseHeaders().set("Location", "/cyberStore/creep/editMentor");
            httpExchange.sendResponseHeaders(303, 0);
        }

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

