package services;

import daoImplementation.CookieDAO;

import java.sql.Date;
import java.util.Calendar;

public class CookieService {
private CookieDAO cookieDAO = new CookieDAO();

    public boolean checkIfCookieIsActive(String cookieSessionId) {
        Date currentDate = getCurrentDate();
        Date cookieExpireDate =  cookieDAO.getCookieExpireDate(cookieSessionId);

        if(cookieExpireDate == null){
            return false;
        }
        else if ((cookieExpireDate.getTime() - currentDate.getTime()) >= 0){
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

        cookieDAO.setNewExpireDateForCookie(cookieSessionId, expireDate);
    }
}
