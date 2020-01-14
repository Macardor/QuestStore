package view;

import daoImplementation.CreepDAOImplementation;
import models.Mentor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StaticUi {
    // tutaj dodajemy souty i inputy do poglądowej wersji
    public static Scanner scanner = new Scanner(System.in);

    public static void showLoginMenu(){
        String loginMenu = "Login menu\n" +
                            "1) Login.\n" +
                            "2) Exit.";
        System.out.println(loginMenu);

    }

    public static void errorMassage(){
        System.out.println("You can chose only from menu options !!!");
    }

    public static List<String> enterLogin(){
        List<String> list = new ArrayList<>();
        System.out.println("please enter login: ");
        String login = scanner.next();
        System.out.println("please enter password: ");
        String password = scanner.next();
        list.add(login);
        list.add(password);
        return list;
    }

    public static void errorMessageBadLoginOrPassword(){
        System.out.println("Bad login or password. Please try again.");
    }



//    Creep controller
    public static void printCreepMenu(){
        System.out.println("Select number to: \n" +
                "1. Show all mentors\n" +
                "2. Add a new mentor\n" +
                "3. Edit mentor by id\n" +
                "4. Remove mentor by id");
    }

    public static List<Mentor> addMentor(){
        List<Mentor> mentorsList = new ArrayList<>();
        System.out.println("Insert mentor's login: ");
        String login = scanner.next();
        System.out.println("Insert mentor's password: ");
        String password = scanner.next();
        System.out.println("Insert mentor's name: ");
        String firstName = scanner.next();
        System.out.println("Insert mentor's last name: ");
        String lastName = scanner.next();
        int studentTypeId = 2;
        Mentor mentor = new Mentor(login, password, studentTypeId, firstName, lastName);
        CreepDAOImplementation creepDAOImplementation = new CreepDAOImplementation();
        creepDAOImplementation.addMentor(mentor);
        mentorsList.add(mentor);
        return mentorsList;
    }
}
