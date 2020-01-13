package controllers;

import daoImplementation.MentorDAOImplementation;
import models.Student;
import view.StaticUi;

import java.util.Scanner;

public class MentorController implements BaseController {
    Scanner scanner = new Scanner(System.in);
    MentorDAOImplementation mentorDAOImplementation;

    private void mentorMenu(){
        displayMentorMenu();
        String option = scanner.next();
        int idInput = scanner.nextInt();
        switch (option){
            case "1":
                mentorDAOImplementation.getStudentsList();
                enterNewStudent();
                mentorDAOImplementation.getStudentsList();
                break;
            case "2":
                mentorDAOImplementation.getStudentsList();
                mentorDAOImplementation.deleteStudent(idInput);
                mentorDAOImplementation.getStudentsList();
                break;
            case "3":
                mentorDAOImplementation.getStudentsList().toString();
                break;
            case "4":
                break;
        }
    }

    private void displayMentorMenu(){
        System.out.println("Select number to: \n" +
                "1. Add new student\n" +
                "2. Delete student\n" +
                "3. Get All Students\n" +
                "4. Delete student\n");
    }

    private void enterNewStudent(){

        System.out.println("Insert login: ");
        String login =  scanner.next();
        System.out.println("Insert password: ");
        String password = scanner.next();
        System.out.println("Insert first name: ");
        String firstName = scanner.next();
        System.out.println("Insert last name: ");
        String lastName = scanner.next();

        mentorDAOImplementation.addStudent(new Student(login, password, Student.userType, firstName, lastName));
    }


    public static void main(String[] args) {
        MentorController mentorController = new MentorController();
        mentorController.mentorMenu();
    }


    @Override
    public void run() {

    }
}
