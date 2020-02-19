package handlers.mentor.quests;

import DAO.QuestDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.CookieHandler;
import models.Quest;
import models.Student;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.QuestService;

import java.io.*;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditQuestHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();
    QuestDAO questDAO = new QuestDAO();
    int notUpdatedQuestId;

    private int postIndex = 1;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if (user == null || user.getUserType() != 2) {
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }
        String method = httpExchange.getRequestMethod();
        String response = "";

        List<Quest> questList = questDAO.getAllQuests();

        if (method.equals("GET")) {
            postIndex = 1;

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/edit-quest-menu.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("questList", questList);
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

            Map inputs = parseFormData(formData);
            int questId = Integer.parseInt(inputs.get("questId").toString());
            notUpdatedQuestId = questId;
            Quest quest = questDAO.getQuestById(questId);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/edit-quest.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("quest", quest);
            response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        if (method.equals("POST") && postIndex == 2) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map inputs = parseFormData(formData);
            System.out.println(inputs.toString());
            questDAO.editQuest(new Quest(notUpdatedQuestId, inputs.get("name").toString(), inputs.get("description").toString(), Integer.parseInt(inputs.get("reward").toString()), Boolean.parseBoolean(inputs.get("isActive").toString())));

            httpExchange.getResponseHeaders().set("Location", "/mentor/edit-quest");
            httpExchange.sendResponseHeaders(303, 0);
        }

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
