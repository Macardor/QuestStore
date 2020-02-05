package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controllers.CreepController;
import controllers.MentorController;
import helpers.CookieHandler;
import models.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import services.LoginService;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class LoginHandler implements HttpHandler {
    private LoginService loginService = new LoginService();
    private CookieHandler cookieHandler = new CookieHandler();
    User user = null;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        user = null;
        user = cookieHandler.cookieChecker(httpExchange);
        if(user != null){
            if(user.getUserType() == 1){
                httpExchange.getResponseHeaders().add("Location", "/student");
                httpExchange.sendResponseHeaders(303, 0);
            }
            else if(user.getUserType() == 2){
                httpExchange.getResponseHeaders().add("Location", "/mentor/");
                httpExchange.sendResponseHeaders(303, 0);
            }else{
                httpExchange.getResponseHeaders().add("Location", "/creep");
                httpExchange.sendResponseHeaders(303, 0);
            }
        }


        String method = httpExchange.getRequestMethod();
        String login;
        String password;

        if(method.equals("POST")){
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map inputs = parseFormData(formData);
            login = inputs.get("login").toString();
            password = inputs.get("password").toString();

            if(login != null && password != null){
                User user = loginService.loginChecker(login,password);
                if(user != null){
                    if (user.getClass().getSimpleName().equals("Student")){
                        System.out.println("you log in as Student");
                        cookieHandler.setCookieNewExpireDateToActiveSession(httpExchange);
                        cookieHandler.setUserIdToCookieInDB(user, httpExchange);
                        httpExchange.getResponseHeaders().add("Location", "/student");
                        httpExchange.sendResponseHeaders(303, 0);
                    } else if (user.getClass().getSimpleName().equals("Mentor")){
                        System.out.println("you log in as Mentor");
                        cookieHandler.setCookieNewExpireDateToActiveSession(httpExchange);
                        cookieHandler.setUserIdToCookieInDB(user, httpExchange);
                        httpExchange.getResponseHeaders().add("Location", "/mentor/homepage");
                        httpExchange.sendResponseHeaders(303, 0);
                    }else {
                        System.out.println("you log in as Creep");
                        cookieHandler.setCookieNewExpireDateToActiveSession(httpExchange);
                        cookieHandler.setUserIdToCookieInDB(user, httpExchange);
                        httpExchange.getResponseHeaders().set("Location", "/creep");
                        httpExchange.sendResponseHeaders(303, 0);
                    }
                }
            }

        }

        if(method.equals("GET")) {


            // get a template file
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/loginPage.twig");

            // create a model that will be passed to a template
            JtwigModel model = JtwigModel.newModel();

            // render a template to a string
            String response = template.render(model);

            // send the results to a the client
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        if(method.equals("POST")) {
            // get a template file
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/loginPage.twig");

            // create a model that will be passed to a template
            JtwigModel model = JtwigModel.newModel();

            // fill the model with values
            String wrongInput = "style=\"background-color: pink\"";
            String wrongInputText = "<p style=\"color: hotpink\">Wrong Login or Password</p>";
            model.with("wrongInput",wrongInput );
            model.with("wrongInputText",wrongInputText );


            // render a template to a string
            String response = template.render(model);

            // send the results to a the client

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
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
