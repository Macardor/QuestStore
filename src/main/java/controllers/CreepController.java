package controllers;

import daoImplementation.CreepDAOImplementation;
import models.User;
import view.StaticUi;

import java.util.Scanner;

public class CreepController implements BaseController {

    private User thisUser;

    CreepDAOImplementation creepDAOImplementation = new CreepDAOImplementation();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run(User user) {
        this.thisUser = user;
        creepMenu();
    }


    private void creepMenu(){
        StaticUi.printCreepMenu();
        String option = scanner.next();
        switch (option){
            case "1":
                creepDAOImplementation.showAllMentors();
                break;
            case "2":
                creepDAOImplementation.addMentor(thisUser.);
                //StaticUi.addMentor();
                break;
            case "3":
                creepDAOImplementation.showAllMentors();
                creepDAOImplementation.getMentorById(StaticUi.getIdInput());
                creepDAOImplementation.showAllMentors();
                break;
            case "4":
                creepDAOImplementation.showAllMentors();
                StaticUi.setMentorUnactive();
                int idMentor = scanner.nextInt();
                creepDAOImplementation.setMentorToUnactive(idMentor);
                break;
            case "0":
                creepMenu();
                break;
        }
    }
}
