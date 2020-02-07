package services;

import DAO.LoginDAO;
import models.User;

public class LoginService {
    LoginDAO loginDAO = new LoginDAO();


    public User loginChecker(String login, String password){

        User user;
//        List<String> list = StaticUi.enterLogin();

//        String login = list.get(0);
//        String password = list.get(1);
        user = loginDAO.isLoginAndPasswordInDB(login,password);

        return user;
    }

}
