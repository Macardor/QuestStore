package handlers.student;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import DAO.ItemDAO;
import DAO.StudentDAO;
import helpers.CookieHandler;
import models.ItemTransaction;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentInventoryHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    StudentDAO studentDAO = new StudentDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = cookieHandler.cookieChecker(httpExchange);
        if(user == null || user.getUserType() != 1){
            httpExchange.getResponseHeaders().set("Location", "/login");
            httpExchange.sendResponseHeaders(303, 0);
        }

        String method = httpExchange.getRequestMethod();
        ItemDAO itemDAO = new ItemDAO();
        List<ItemTransaction> userItemsList = itemDAO.getUserItemsList(studentDAO.getStudentId(user));
        int coins = studentDAO.getStudentCoins(user.getId());

        if (method.equals("GET")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/inventory.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("userItemsList", userItemsList);
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

            int itemId = Integer.parseInt(inputs.get("itemID").toString());
            Date currentDate = getCurrentDate();
            itemDAO.setTransactionItemActive(itemId, currentDate);


            httpExchange.getResponseHeaders().add("Location", "/student/inventory");
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

    private Date getCurrentDate() {
        return new Date(Calendar.getInstance().getTime().getTime());
    }
}
