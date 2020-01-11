package services;

import daoImplementation.LoginDAOImplementation;

public class LoginService {
    LoginDAOImplementation loginDAOImplementation = new LoginDAOImplementation();

    public boolean loginChecker(String login, String password){
        loginDAOImplementation.isLoginAndPasswordInDB(login,password);
        return true;
        //TODO
    }
}
