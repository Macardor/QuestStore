package handlers.mentor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.CookieHandler;
import models.Mentor;
import models.Student;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.MentorService;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class MentorEditProfile implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    MentorService mentorService = new MentorService();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 2){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }

        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/mentorEditProfile.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("user", user);
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        if (method.equals("POST")){
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            String login = inputs.get("login").toString();
            String password = inputs.get("password").toString();
            String firstName = inputs.get("firstName").toString();
            String lastName = inputs.get("lastName").toString();
            int mentorDetailsId = mentorService.getUserDetailsId(user);

            Mentor mentorToEdit = new Mentor(login,password,2,true,firstName,lastName);
            mentorService.editMentor(mentorToEdit, mentorDetailsId);

            httpExchange.getResponseHeaders().set("Location", "/mentor/homepage");
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
