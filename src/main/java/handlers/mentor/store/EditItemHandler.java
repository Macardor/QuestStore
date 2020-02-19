package handlers.mentor.store;

import DAO.ItemDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.CookieHandler;
import models.Item;
import models.Student;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.ItemService;

import java.io.*;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditItemHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();
    ItemDAO itemDAO = new ItemDAO();
    int notUpdatedItemId;

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

        List<Item> itemList = itemDAO.getItemsList();

        if (method.equals("GET")) {
            postIndex = 1;

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/edit-item-menu.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("itemList", itemList);
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
            int itemId = Integer.parseInt(inputs.get("itemId").toString());
            notUpdatedItemId = itemId;
            Item item = itemDAO.getItemById(itemId);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor/edit-item.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("item", item);
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
            itemDAO.editItem(new Item(notUpdatedItemId, inputs.get("name").toString(), Integer.parseInt(inputs.get("price").toString()),inputs.get("description").toString(), Boolean.parseBoolean(inputs.get("isActive").toString())));

            httpExchange.getResponseHeaders().set("Location", "/mentor/edit-item");
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
