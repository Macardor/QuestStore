package services;

import daoImplementation.CookieDAOImplementation;
import models.User;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;

public class CookieService {
private CookieDAOImplementation cookieDAOImplementation = new CookieDAOImplementation();

    public boolean checkIfCookieIsActive(String cookieSessionId) {
        Date currentDate = getCurrentDate();
        Date cookieExpireDate =  cookieDAOImplementation.getCookieExpireDate(cookieSessionId);
        if ((cookieExpireDate.getTime() - currentDate.getTime()) >= 0){
            return true;
        }else {
            return false;
        }
    }

    private Date getCurrentDate() {
        return new Date(Calendar.getInstance().getTime().getTime());
    }

    public void setCookieNewExpireDate(String cookieSessionId){
        Date currentDate = getCurrentDate();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 1);

        Date expireDate = new Date(c.getTimeInMillis());

        cookieDAOImplementation.setNewExpireDateForCookie(cookieSessionId, expireDate);
    }
}
