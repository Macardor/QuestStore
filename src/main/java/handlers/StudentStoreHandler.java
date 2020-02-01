package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import daoImplementation.ItemDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import helpers.CookieHandler;
import models.Item;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentStoreHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 1){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }

        String method = httpExchange.getRequestMethod();
        ItemDAOImplementation itemDAOImplementation = new ItemDAOImplementation();
        List<Item> itemsList = itemDAOImplementation.getItemsList();
        StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
        int coins = studentDAOImplementation.showUserCoins(user.getId());

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/store.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("itemsList", itemsList);
            model.with("coins", coins);
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

            int itemID = Integer.parseInt(inputs.get("itemID").toString());
            System.out.println( "------> " + itemID);
            int itemPrice = itemDAOImplementation.getItemPriceById(itemID);
            if (coins >= itemPrice && coins >= 0){
                System.out.println("you buy item");
                int coinsToSet = coins - itemPrice;
                studentDAOImplementation.buyItem(itemID, studentDAOImplementation.getStudentId(user));
                studentDAOImplementation.setStudentCoins(coinsToSet, studentDAOImplementation.getStudentId(user));
            }

            httpExchange.getResponseHeaders().add("Location", "/student/store");
            httpExchange.sendResponseHeaders(303, 0);
        }
    }

    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            // We have to decode the value because it's urlencoded. see: https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}
