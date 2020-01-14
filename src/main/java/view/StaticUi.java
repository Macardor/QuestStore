package view;

import models.Item;
import models.Quest;
import models.Student;

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

    public static void addStudentMenu() {
    }
    public static void displayAllStudents(List<Student> studentList){
        for (Student student : studentList) {
            System.out.println(student.getId() + " | " + student.getLogin() + " | " + student.getPassword() + " | " + student.getFirstname() + " | " + student.getLastname() + " | " + student.isActive());
        }
    }

    public static void displayMentorMenu(){
        System.out.println("Select number to: \n" +
                "1. Add new student\n" +
                "2. Delete student\n" +
                "3. Get All Students\n" +
                "4. Get all active students\n" +
                "5. Get student by user id");
    }

    public static int getIdInput(){
        System.out.println("Enter id: ");
        int idInput = scanner.nextInt();
        return idInput;
    }
    public static String getFirstNameInput(){
        System.out.println("Enter first name: ");
        String nameInput = scanner.next();
        return nameInput;
    }
    public static String getLastNameInput(){
        System.out.println("Enter last name: ");
        String lNameInput = scanner.next();
        return lNameInput;
    }
    public static int getUserTypeInput(){
        System.out.println("Enter user type: ");
        int userTypeInput = scanner.nextInt();
        return userTypeInput;
    }
    public static String getLoginInput(){
        System.out.println("Enter login: ");
        String loginInput = scanner.next();
        return loginInput;
    }
    public static String getPasswordInput(){
        System.out.println("Enter password: ");
        String passwordInput = scanner.next();
        return passwordInput;
    }
    public static boolean getBoolInput(){
        System.out.println("Enter is record active");
        String bool = scanner.next();
        if (bool == "t"){
            return true;
        }else {
            return false;
        }
    }
    public static void displayAllQuests(List<Quest> questList){
        for (Quest element : questList) {
            System.out.println(element.getId() + " | " + element.getName() + " | " + element.getDescription() + " | " + element.getReward() + " | " + element.isActive());
        }
    }

    public static String getNameInput(){
        System.out.println("Enter name: ");
        String nameInput = scanner.next();
        return nameInput;
    }
    public static String getDescriptionInput(){
        System.out.println("Enter description: ");
        String descriptionInput = scanner.next();
        return descriptionInput;
    }
    public static int getRewardInput(){
        System.out.println("Enter reward: ");
        int rewardInput = scanner.nextInt();
        return rewardInput;
    }
    public static void displayQuestMenu(){
        System.out.println("1. Add Quest\n" +
                "2. Edit Quest\n" +
                "3. Delete quest\n" +
                "4. Get all quests\n" +
                "5. Get quest by id\n");
    }

    public static void displayStudentSubmenu() {
        System.out.println("Edit Quest. What do you want to edit?\n" +
                "1. To edit name quest\n" +
                "2. To edit description quest\n" +
                "3. To edit reward quest\n" +
                "4. To activate or deactivate quest\n" +
                "6. To submit");
    }

    public static void displayEditQuestChoice() {
        System.out.println("Which quests do you want to edit?");
    }


    public static void displayItemMenu(){
        System.out.println("1. Add Item\n" +
                "2. Edit Item\n" +
                "3. Delete Item\n" +
                "4. Get all Item\n" +
                "5. Get Item by id\n");
    }

    public static void displayItemSubMenu() {
        System.out.println("Edit item. What do you want to edit?\n" +
                "1. To edit name item\n" +
                "2. To edit item price\n" +
                "3. To edit item description\n" +
                "4. To activate or deactivate quest\n" +
                "6. To submit");
    }

    public static void displayAllItems(List<Item> itemList){
        for (Item element : itemList) {
            System.out.println(element.getId() + " | " + element.getName() + " | " + element.getPrice() + " | " + element.getDescription() + " | " + element.isActive());
        }
    }
    public static int getPriceInput(){
        System.out.println("Enter price: ");
        int priceInput = scanner.nextInt();
        return priceInput;
    }

    public static void displayEditItemChoice() {
        System.out.println("Which item do you want to edit?");
    }
}
