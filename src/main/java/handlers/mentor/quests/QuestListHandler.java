package handlers.mentor.quests;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import models.Quest;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.ItemService;
import services.QuestService;

import java.io.IOException;
import java.io.OutputStream;

public class QuestListHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        String response = null;
        QuestService qs = new QuestService();

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/quest-list.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("questList", qs.getQuestList());
            response = template.render(model);
        }

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
