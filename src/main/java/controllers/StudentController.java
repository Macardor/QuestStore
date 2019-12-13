package controllers;

import daoImplementation.StudentDAOImplementation;

import java.util.Scanner;

public class StudentController implements BaseController {

    StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        studentMenu();
    }

    private void studentMenu(){
        printStudentMenu();
        String option = scanner.next();
        switch (option){
            case "1":
                enterNewUser();
                break;
            case "2":
                extractStudents();
                break;
            case "3":
                editStudentByStudentId();
                break;
        }
    }

    private void printStudentMenu(){
        System.out.println("Select number to: " +
                "1. Add new student" +
                "2. Show all students" +
                "3. Edit student");
    }

    private void enterNewUser(){

        System.out.println("Insert login: ");
        String login =  scanner.next();
        System.out.println("Insert password: ");
        String password = scanner.next();
        System.out.println("Insert first name: ");
        String firstName = scanner.next();
        System.out.println("Insert last name: ");
        String lastName = scanner.next();

        studentDAOImplementation.addStudent(login, password, firstName, lastName);
    }

    private void extractStudents(){
        studentDAOImplementation.extractStudent();
    }

    private void editStudentByStudentId(){
        //extractStudents();
        int studentId = scanner.nextInt();
        studentDAOImplementation.editStudent(studentId);
    }

}
