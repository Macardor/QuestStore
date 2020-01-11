package controllers;

import services.LoginService;
import view.StaticUi;

import java.util.Scanner;

public class LoginController implements BaseController{

    Scanner scanner = new Scanner(System.in);
    StaticUi staticUi = new StaticUi();
    LoginService loginService = new LoginService();

    @Override
    public void run() {
        loginMenu();
    }

    private void loginMenu(){
        staticUi.showLoginMenu();
        String option = scanner.next();
        switch (option){
            case "1":
                loginManager();
                break;
            default:
                staticUi.errorMassage();
                loginMenu();
        }
    }

    private void loginManager(){
        System.out.println(loginService.loginChecker());
        //TODO
    }

}

