package services;

import daoImplementation.LoginDAOImplementation;
import models.Creep;
import models.Mentor;
import models.Student;
import models.User;
import view.StaticUi;

import java.util.List;
import java.util.Scanner;

public class LoginService {
    LoginDAOImplementation loginDAOImplementation = new LoginDAOImplementation();


    public User loginChecker(){

        User user;
        List<String> list = StaticUi.enterLogin();
        String login = list.get(0);
        String password = list.get(1);
        user = loginDAOImplementation.isLoginAndPasswordInDB(login,password);

        return user;
    }

}
