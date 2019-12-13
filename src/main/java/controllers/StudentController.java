package controllers;

import daoImplementation.StudentDAOImplementation;

import java.util.Scanner;

public class StudentController implements BaseController {
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert login: ");
        String login =  scanner.next();
        System.out.println("Insert password: ");
        String password = scanner.next();
        System.out.println("Insert first name: ");
        String firstName = scanner.next();
        System.out.println("Insert last name: ");
        String lastName = scanner.next();
        StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
        studentDAOImplementation.addUser(login, password, firstName, lastName);
    }
}
