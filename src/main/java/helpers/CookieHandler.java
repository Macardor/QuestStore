package helpers;

import com.sun.net.httpserver.HttpExchange;

import java.net.HttpCookie;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CookieHandler {


    CookieHelper cookieHelper = new CookieHelper();
    String SESSION_COOKIE_NAME = "sesion_id";


    public void cookieChecker(HttpExchange httpExchange) {

        Optional<HttpCookie> cookieList = getSessionIdCookie(httpExchange);

        if (cookieList.isPresent()) {  // Cookie already exists

        } else { // Create a new cookie
            UUID uuid = UUID.randomUUID();
            HttpCookie cookie = new HttpCookie(SESSION_COOKIE_NAME, uuid.toString());
            httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
        }

    }

    Optional<HttpCookie> getSessionIdCookie(HttpExchange httpExchange){
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        List<HttpCookie> cookies = cookieHelper.parseCookies(cookieStr);
        return cookieHelper.findCookieByName(SESSION_COOKIE_NAME, cookies);
    }
}
