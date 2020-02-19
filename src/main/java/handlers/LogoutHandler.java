package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.CookieHandler;

import java.io.IOException;

public class LogoutHandler implements HttpHandler  {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        CookieHandler cookieHandler = new CookieHandler();
        cookieHandler.logout(httpExchange);
        httpExchange.getResponseHeaders().add("Location", "/login");
        httpExchange.sendResponseHeaders(303, 0);
    }
}
