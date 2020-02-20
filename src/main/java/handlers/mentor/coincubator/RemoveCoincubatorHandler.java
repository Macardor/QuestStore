package handlers.mentor.coincubator;

import DAO.CoincubatorDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.CookieHandler;
import models.Coincubator;
import models.Quest;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.CoincubatorService;
import services.QuestService;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveCoincubatorHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 2){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }
        CoincubatorService coincubatorService = new CoincubatorService();
        CoincubatorDAO coincubatorDAO = new CoincubatorDAO();
        String method = httpExchange.getRequestMethod();
        List<Coincubator> coincubatorList = coincubatorService.getAllCoincubators(coincubatorDAO.getAllCoincubatorsFromDb());
        String response = "";


        if (method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/remove-coincubator.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("coincubatorList", coincubatorList);
            response = template.render(model);
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/remove-coincubator.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("coincubatorList", coincubatorList);
            response = template.render(model);
            coincubatorDAO.deleteCoincubator(Integer.parseInt(inputs.get("coincubatorId").toString()));

            httpExchange.getResponseHeaders().set("Location", "/mentor/remove-coincubator" );
            httpExchange.sendResponseHeaders(303,0);
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
