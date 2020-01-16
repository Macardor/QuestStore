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

    private void printCreepMenu(){
        System.out.println("Select number to: \n" +
                "1. Show all mentors\n" +
                "2. Add a new mentor\n" +
                "3. Edit mentor by id\n" +
                "4. Remove mentor by id");
    }

    private void addMentor(){
        System.out.println("Insert mentor's login: ");
        String login = scanner.next();
        System.out.println("Insert mentor's password: ");
        String password = scanner.next();
        System.out.println("Insert mentor's name: ");
        String firstName = scanner.next();
        System.out.println("Insert mentor's last name: ");
        String lastName = scanner.next();
        boolean isActive = true;
        int studentTypeId = 2;
        Mentor mentor = new Mentor(login, password, studentTypeId, isActive, firstName, lastName);



//    private void deleteMentorById(){
//        creepDAOImplementation.showAllMentors();
//        int mentorId = scanner.nextInt();
//        creepDAOImplementation.deleteMentor(mentorId);
//        creepDAOImplementation.showAllMentors();
//    }
//TODO


}
