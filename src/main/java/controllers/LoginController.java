package controllers;

import models.User;
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
            case "2":
                break;
            default:
                staticUi.errorMassageIfBadChoseInMenu();
                loginMenu();
        }
    }

    private void loginManager(){
        User user = loginService.loginChecker();
        if (user == null){
            StaticUi.errorMessageBadLoginOrPassword();
            loginMenu();
        } else {
            if (user.getClass().getSimpleName().equals("Student")){
                System.out.println("you log in as Student");
                StudentController studentController = new StudentController();
                studentController.run();
            } else if (user.getClass().getSimpleName().equals("Mentor")){
                System.out.println("you log in as Mentor");
                MentorController mentorController = new MentorController();
                mentorController.run();
            }else {
                System.out.println("you log in as Creep");
                CreepController creepController = new CreepController();
                creepController.run();
            }
        }
    }

}

