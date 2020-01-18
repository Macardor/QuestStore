package httpHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import models.Student;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.TestService;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        TestService testService = new TestService();
        String method = httpExchange.getRequestMethod();
        List<Student> studentList = testService.getFkinListOfIdiots();
        System.out.println(method);

        String response = "";


        if (method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/test.twig");
            JtwigModel model = JtwigModel.newModel();

            model.with("studentList", studentList);

            response = template.render(model);
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/submit.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("login", inputs.get("login"));
            model.with("password", inputs.get("password"));
            model.with("firstName", inputs.get("firstname"));
            model.with("lastName", inputs.get("lastname"));
            response = template.render(model);
            testService.addIdiot(new Student(inputs.get("login").toString(), inputs.get("password").toString(), 1, true, inputs.get("firstname").toString(), inputs.get("lastname").toString()));
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
