package services;

import daoImplementation.LoginDAOImplementation;
import models.User;
import view.StaticUi;

import java.util.List;

public class LoginService {
    LoginDAOImplementation loginDAOImplementation = new LoginDAOImplementation();


    public User loginChecker(String login, String password){

        User user;
//        List<String> list = StaticUi.enterLogin();

//        String login = list.get(0);
//        String password = list.get(1);
        user = loginDAOImplementation.isLoginAndPasswordInDB(login,password);

        return user;
    }

}
