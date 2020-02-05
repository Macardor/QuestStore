package controllers;

import daoImplementation.ItemDAO;
import daoImplementation.StudentDAO;
import models.User;
import view.StaticUi;

import java.util.Scanner;

public class StudentController implements BaseController {
    private User thisUser;

    StudentDAO studentDAO = new StudentDAO();
    ItemDAO itemDAO = new ItemDAO();
    StaticUi staticUi = new StaticUi();
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
                itemDAO.getItemsList();
                buyItemMenu();
                break;
            case "2":
                studentDAO.showCoincubatos();
                staticUi.printStudentCoincubatorMenu();
                option = scanner.next();
                switch (option){
                    case "1":
                        staticUi.coincubatorPayMenu(thisUser.getId());
                        break;
                    case "0":
                        studentMenu();
                        break;
                }
                break;
            case "3":
                studentDAO.showUserCoins(studentDAO.getStudentId(thisUser));
                break;
            case "4":
                itemDAO.getUserItemsList(studentDAO.getStudentId(thisUser));
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
                studentDAO.buyItem(itemId, thisUser.getId());
                break;
            case "2":
                studentMenu();
                break;
        }
    }

    private void useCard(){
        itemDAO.getUserItemsList(thisUser.getId());
        StaticUi.printUseCardId();
        int itemId = scanner.nextInt();
        studentDAO.useCard(itemId, thisUser.getId());
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

