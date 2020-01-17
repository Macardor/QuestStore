package httpHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.TestService;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class TestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        TestService testService = new TestService();
        String method = httpExchange.getRequestMethod();
        System.out.println(method);
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        InputStreamReader isr2 = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br2 = new BufferedReader(isr2);
        String response = "";


        if (method.equals("GET")) {


//            String formData = br.readLine();
//            Map inputs = parseFormData(formData);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/home/adam/Desktop/QuestStore/src/main/resources/templates/test.twig");

            JtwigModel model = JtwigModel.newModel();
//            model.with("login", inputs.get("login"));
//            model.with("password", inputs.get("password"));
//            model.with("firstName", inputs.get("firstname"));
//
//            model.with("lastName", inputs.get("lastname"));
//            model.with("studentList", testService.getFkinListOfIdiots());
//            model.with("studentLogin",1 );
            // TODO jak zrobić getter z listy w twiggu

            response = template.render(model);
            System.out.println(response);


        }

        // If the form was submitted, retrieve it's content.
        if (method.equals("POST")) {

            String formData = br2.readLine();

            System.out.println(response);
            System.out.println(formData);

            Map inputs = parseFormData(formData);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/submit.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("fName", inputs.get("firstname"));
            model.with("lName", inputs.get("lastname"));
            model.with("country", inputs.get("country"));
            model.with("message", inputs.get("subject"));
            response = template.render(model);
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
