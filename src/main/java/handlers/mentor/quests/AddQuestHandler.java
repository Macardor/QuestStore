package handlers.mentor.quests;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.CookieHandler;
import models.Item;
import models.Quest;
import models.Student;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.ItemService;
import services.QuestService;
import services.StudentService;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class AddQuestHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 2){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }
        QuestService questService = new QuestService();
        String method = httpExchange.getRequestMethod();
        System.out.println(method);

        String response = "";


        if (method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/add-quest.twig");
            JtwigModel model = JtwigModel.newModel();

            response = template.render(model);
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/add-quest.twig");
            JtwigModel model = JtwigModel.newModel();
            response = template.render(model);

            System.out.println(inputs.get("name").toString() + inputs.get("reward").toString() + inputs.get("description").toString());
            questService.addNewQuest(new Quest(inputs.get("name").toString(), inputs.get("description").toString(), Integer.parseInt(inputs.get("reward").toString()), true));

//            httpExchange.getResponseHeaders().set("Location", "/mentor/add-quest" );
//            httpExchange.sendResponseHeaders(303,0);
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
