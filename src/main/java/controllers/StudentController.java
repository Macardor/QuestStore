package controllers;

import daoImplementation.ItemDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import models.User;

import java.util.Scanner;

public class StudentController implements BaseController {
    private User thisUser;


    StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
    ItemDAOImplementation itemDAOImplementation = new ItemDAOImplementation();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run(User user) {
        this.thisUser = user;
        studentMenu();
    }

    private void studentMenu() {
        printStudentMenu();
        String option = scanner.next();
        switch (option) {
            case "1":
                itemDAOImplementation.getItemsList();
                buyItemMenu();
                break;
            case "2":
                studentDAOImplementation.showCoincubatos();
                break;
            case "3":
                studentDAOImplementation.showUserCoins(thisUser.getId());
                break;
            case "4":
                itemDAOImplementation.getUserItemsList(thisUser.getId());
                useCardMenu();
                break;
        }
    }

    private void printStudentMenu() {
        System.out.println("Select number to: \n" +
                "1. Show all items in the store\n" +
                "2. Show all coincubators in the store\n" +
                "3. Show all your coins\n" +
                "4. Show user's items");
    }

    private void showUserItems() {
        itemDAOImplementation.getUserItemsList(thisUser.getId());
    }

    private void buyItemMenu() {
        System.out.println("\n1. Buy item by id\n" +
                "2. Back to menu");
        String option = scanner.next();
        switch (option) {
            case "1":
                System.out.println("choose item id to buy: ");
                int itemId = scanner.nextInt();
                studentDAOImplementation.buyItem(itemId, thisUser.getId());
                break;
            case "2":
                studentMenu();
                break;
        }
    }

    private void useCard(){
        itemDAOImplementation.getUserItemsList(thisUser.getId());
        System.out.println("Choose id of card you want to use: ");
        int itemId = scanner.nextInt();
        studentDAOImplementation.useCard(itemId, thisUser.getId());
    }

    private void useCardMenu(){
        System.out.println("\n1.Use card" +
                "\n2.Back to menu");
        String option = scanner.next();
        switch (option) {
            case "1":
                useCard();
                break;
            case "2":
                studentMenu();
                break;
        }
    }
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

