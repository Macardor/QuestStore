package helpers;

import com.sun.net.httpserver.HttpExchange;
import daoImplementation.CookieDAOImplementation;
import models.User;
import services.CookieService;

import java.net.HttpCookie;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CookieHandler {

    private CookieService cookieService = new CookieService();
    CookieHelper cookieHelper = new CookieHelper();
    String SESSION_COOKIE_NAME = "sesion_id";
    CookieDAOImplementation cookieDAOImplementation = new CookieDAOImplementation();

    public User cookieChecker(HttpExchange httpExchange) {

        Optional<HttpCookie> cookieList = getSessionIdCookie(httpExchange);
        User user = null;
        if (cookieList.isPresent()) {  // Cookie already exists
            System.out.println("test1");
            String cookieSessionId = cookieList.get().getValue();

            User userToCheck = cookieDAOImplementation.getUserByCookieSessionId(cookieSessionId);
            if (cookieService.checkIfCookieIsActive(cookieSessionId)){
                return userToCheck;
            }else{
                return user;
            }
        } else { // Create a new cookie
            System.out.println("test2");
            UUID uuid = UUID.randomUUID();
            HttpCookie cookie = new HttpCookie(SESSION_COOKIE_NAME, uuid.toString());
            httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
            String cookieSessionIdToAdd = uuid.toString();
            cookieSessionIdToAdd = '"'+cookieSessionIdToAdd+'"';

            cookieDAOImplementation.putNewCookieToDB(cookieSessionIdToAdd);
        }
        return user;

    }

    Optional<HttpCookie> getSessionIdCookie(HttpExchange httpExchange){
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        List<HttpCookie> cookies = cookieHelper.parseCookies(cookieStr);
        return cookieHelper.findCookieByName(SESSION_COOKIE_NAME, cookies);
    }

    public void setCookieNewExpireDateToActiveSession(HttpExchange httpExchange){
        Optional<HttpCookie> cookieList = getSessionIdCookie(httpExchange);
        String cookieSessionId = cookieList.get().getValue();
        cookieService.setCookieNewExpireDate(cookieSessionId);
    }

    public void setUserIdToCookieInDB(User user, HttpExchange httpExchange) {
        Optional<HttpCookie> cookieList = getSessionIdCookie(httpExchange);
        String cookieSessionId = cookieList.get().getValue();
        int studentId = user.getId();
        cookieDAOImplementation.putUserIdToCookieInDB(studentId, cookieSessionId);
    }

    public void logout (HttpExchange httpExchange){
        Optional<HttpCookie> cookieList = getSessionIdCookie(httpExchange);
        String cookieSessionId = cookieList.get().getValue();
        cookieDAOImplementation.setCookieForLogout(cookieSessionId);
    }
}
