package handlers.mentor.store;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.CookieHandler;
import models.User;

import java.io.IOException;

public class EditItemHandler implements HttpHandler {
    User user = null;
    CookieHandler cookieHandler = new CookieHandler();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
