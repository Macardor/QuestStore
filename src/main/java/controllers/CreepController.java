package controllers;

import daoImplementation.CreepDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import models.Mentor;
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
                //creepDAOImplementation.editMentor();
                break;
            case "4":
                deleteMentorById();
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
