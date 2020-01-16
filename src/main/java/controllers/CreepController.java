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
                int id = scanner.nextInt();
                creepDAOImplementation.editMentor(id);
                break;
            case "4":
                int idMentor = scanner.nextInt();
                System.out.println("insert id of metor to make unactive: ");
                creepDAOImplementation.setMentorToUnactive(idMentor);
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
