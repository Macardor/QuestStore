package controllers;

import daoImplementation.CreepDAOImplementation;
import models.Mentor;

import java.util.Scanner;

public class CreepController implements BaseController {


    CreepDAOImplementation creepDAOImplementation = new CreepDAOImplementation();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        creepMenu();
    }


    private void creepMenu(){
        printCreepMenu();
        String option = scanner.next();
        switch (option){
            case "1":
                creepDAOImplementation.showAllMentors();
                break;
            case "2":
                addMentor();
                break;
            case "3":

                break;
            case "4":
                deleteMentorById();
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
        boolean isActive = scanner.nextBoolean();
        int studentTypeId = 2;
        Mentor mentor = new Mentor(login, password, studentTypeId, isActive, firstName, lastName);

        creepDAOImplementation.addMentor(mentor);
        creepDAOImplementation.showAllMentors();
    }

    private void deleteMentorById(){
        creepDAOImplementation.showAllMentors();
        int mentorId = scanner.nextInt();
        creepDAOImplementation.deleteMentor(mentorId);
        creepDAOImplementation.showAllMentors();
    }



}
