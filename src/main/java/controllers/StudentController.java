package controllers;

import daoImplementation.StudentDAOImplementation;

import java.util.Scanner;

public class StudentController implements BaseController {


    StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
//        studentMenu();
    }

    private void studentMenu(){
//        printStudentMenu();
//        String option = scanner.next();
//        switch (option){
//            case "1":
//                studentDAOImplementation.showItems();
//                break;
//            case "2":
//                studentDAOImplementation.showCoincubatos();
//                break;
//            case "3":
//                showUserCoins();
//                break;
//            case "4":
//                studentDAOImplementation.showUserItems();
//        }
    }

    private void printStudentMenu(){
        System.out.println("Select number to: \n" +
                "1. Show all items in the store\n" +
                "2. Show all coincubators in the store\n" +
                "3. Show all your coins\n" +
                "4. Show user's items");
    }

    private void showUserCoins(){
//        studentDAOImplementation.showUserCoins();
    }
//
//    private void enterNewUser(){
//
//        System.out.println("Insert login: ");
//        String login =  scanner.next();
//        System.out.println("Insert password: ");
//        String password = scanner.next();
//        System.out.println("Insert first name: ");
//        String firstName = scanner.next();
//        System.out.println("Insert last name: ");
//        String lastName = scanner.next();
//
//        //studentDAOImplementation.addStudent(login, password, firstName, lastName);
//    }

//    private void extractStudents(){
//        studentDAOImplementation.extractStudent();
//    }
//
//    private void editStudentByStudentId(){
//        //extractStudents();
//        int studentId = scanner.nextInt();
//        studentDAOImplementation.editStudent(studentId);
//    }
//
//    private void deteleStudentByStudentId(){
//        System.out.println("Insert id of student you want to be removed: ");
//        int studentId = scanner.nextInt();
//        studentDAOImplementation.removeStudent(studentId);
//    }
}
