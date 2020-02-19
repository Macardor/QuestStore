package helpers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import models.User;

import static org.graalvm.compiler.options.OptionType.User;

public class CommonHelper {
    public Map<String, String> parseFormData(String formData) {
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

    public User validateCookie(User user, HttpExchange httpExchange) {
        try {
            if (user == null || user.getUserType() != 2) {
                httpExchange.getResponseHeaders().set("Location", "/login");
                httpExchange.sendResponseHeaders(303, 0);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return user;
    }
}
