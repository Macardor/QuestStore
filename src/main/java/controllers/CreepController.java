package controllers;

import daoImplementation.CreepDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import models.Mentor;
import services.CreepService;
import view.StaticUi;

import java.util.Scanner;

public class CreepController implements BaseController {


    CreepDAOImplementation creepDAOImplementation = new CreepDAOImplementation();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
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
                StaticUi.addMentor();
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
            case "5":

                break;
        }
    }



//    private void deleteMentorById(){
//        creepDAOImplementation.showAllMentors();
//        int mentorId = scanner.nextInt();
//        creepDAOImplementation.deleteMentor(mentorId);
//        creepDAOImplementation.showAllMentors();
//    }
//TODO


}
