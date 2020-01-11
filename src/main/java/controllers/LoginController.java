package controllers;

import view.StaticUi;

import java.util.Scanner;

public class LoginController implements BaseController{

    Scanner scanner = new Scanner(System.in);
    StaticUi staticUi = new StaticUi();

    @Override
    public void run() {
        loginMenu();
    }

    private void loginMenu(){
        staticUi.showLoginMenu();
        String option = scanner.next();
        switch (option){
            case "1":

                break;
            default:
                staticUi.errorMassage();
                loginMenu();
        }
    }

    private void login(){

    }
}

