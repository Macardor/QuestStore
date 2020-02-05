package controllers;

import daoImplementation.CreepDAO;
import models.User;
import view.StaticUi;

import java.util.Scanner;

public class CreepController implements BaseController {

    private User thisUser;

    CreepDAO creepDAO = new CreepDAO();
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
                creepDAO.showAllMentors();
                break;
            case "2":
                //creepDAOImplementation.addMentor();
                //StaticUi.addMentor();
                break;
            case "3":
                creepDAO.showAllMentors();
                creepDAO.getMentorById(StaticUi.getIdInput());
                creepDAO.showAllMentors();
                break;
            case "4":
                creepDAO.showAllMentors();
                StaticUi.setMentorUnactive();
                int idMentor = scanner.nextInt();
                creepDAO.setMentorToUnactive(idMentor);
                break;
            case "0":
                creepMenu();
                break;
        }
    }
}
