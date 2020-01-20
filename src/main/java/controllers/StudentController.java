package controllers;

import daoImplementation.ItemDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import models.User;
import view.StaticUi;

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
        StaticUi.printStudentMenu();
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
                studentDAOImplementation.showUserCoins(studentDAOImplementation.getStudentId(thisUser));
                break;
            case "4":
                itemDAOImplementation.getUserItemsList(studentDAOImplementation.getStudentId(thisUser));
                useCardMenu();
                break;
            case "0":
                studentMenu();
                break;
        }
    }

    private void buyItemMenu() {
        StaticUi.printItemMenu();
        String option = scanner.next();
        switch (option) {
            case "1":
                StaticUi.chooseItemById();
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
        StaticUi.printUseCardId();
        int itemId = scanner.nextInt();
        studentDAOImplementation.useCard(itemId, thisUser.getId());
    }

    private void useCardMenu(){
        StaticUi.useCardMenu();
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

