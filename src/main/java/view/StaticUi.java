package view;

import DAO.CoincubatorDAO;
import DAO.CreepDAO;
import DAO.StudentDAO;
import models.*;

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

    public static void errorMassageIfBadChoseInMenu(){
        System.out.println("You can chose only from menu options !!!");
    }

    public static void errorMassageIdNotInDB(){
        System.out.println("You can chose only Id available in DataBase !!!");
    }

    public static void printStudentCoincubatorMenu(){
        System.out.println("\nSelect number to: \n" +
                "1. Pay to Coincubator\n" +
                "0. Go back to menu");
    }

    public static void coincubatorPayMenu(int studentId){
        System.out.println("Enter coincubator ID: ");
        int coincubatorId = scanner.nextInt();
        System.out.println("Enter amount of coins you wish to pay: ");
        int coinAmount = scanner.nextInt();
        StudentDAO studentDAO = new StudentDAO();
        if (coinAmount >= studentDAO.getStudentCoins(studentId)){
            CoincubatorDAO coincubatorDAO = new CoincubatorDAO();
            coincubatorDAO.donateToCoincubatorDb(studentId, coincubatorId, coinAmount);
        }
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

    public static Coincubator coincubatorEditor(Coincubator coincubator){
        Scanner scannerForEdit = new Scanner(System.in);
        Scanner scannerForDescription = new Scanner(System.in);

        System.out.println("Pleas enter a new Name: ");
        String name = scannerForEdit.nextLine();
        coincubator.setName(name);
        System.out.println("Pleas enter a new Description: ");
        String description = scannerForDescription.nextLine();
        coincubator.setDescription(description);
        System.out.println("Pleas enter a new Donation price: ");
        int targetDonation = scanner.nextInt();
        coincubator.setTargetDonation(targetDonation);

        return coincubator;
    }

    public static Coincubator newCoincubator(){
        Scanner scannerForName = new Scanner(System.in);
        Scanner scannerForNew = new Scanner(System.in);
        Scanner scannerForInt = new Scanner(System.in);
        boolean isActive = true;
        int currentDonation = 0;

        System.out.println("Pleas enter a Name: ");
        String name = scannerForName.nextLine();

        System.out.println("Pleas enter a Description: ");
        String description = scannerForNew.nextLine();

        System.out.println("Pleas enter a Donation price: ");
        int targetDonation = scannerForInt.nextInt();

        Coincubator coincubator = new Coincubator(name,description,currentDonation,targetDonation,isActive);

        return coincubator;
    }

    public static void errorMessageBadLoginOrPassword(){
        System.out.println("Bad login or password. Please try again.");
    }

    public static int idToEdit(){
        System.out.println("Please Enter Id to Edit: ");
        int id = scanner.nextInt();
        return id;
    }
    public static void addStudentMenu() {
    }
    public static void displayAllStudents(List<Student> studentList){
        for (Student student : studentList) {
            System.out.println(student.getId() + " | " + student.getLogin() + " | " + student.getPassword() + " | " + student.getFirstname() + " | " + student.getLastname() + " | " + student.isActive());
        }
    }

    public static void displayMentorMenu(){
        System.out.println("\nSelect number to: \n" +
                "1. Add new student\n" +
                "2. Delete student\n" +
                "3. Get All Students\n" +
                "4. Get all active students\n" +
                "5. Get student by user id\n" +
                "6. Add new Coincubator\n" +
                "7. Show all Coincubators\n" +
                "8. Edit Coincubator\n" +
                "9. Delete Coincubator\n" +
                "10. Edit Student data/quest\n" +
                "0. Exit");
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


//    Creep controller
    public static String getMentorLoginInput(){
        System.out.println("Enter new mentor login: ");
        String login = scanner.next();
        return login;
    }

    public static String getMentorPasswordInput(){
        System.out.println("Enter new password: ");
        String password = scanner.next();
        return password;
    }

    public static String getMentorNameInput(){
        System.out.println("Enter new mentor name: ");
        String name = scanner.next();
        return name;
    }

    public static String getMentorLastnameInput(){
        System.out.println("Enter new mentor lastname: ");
        String lastname = scanner.next();
        return lastname;
    }

    public static void printCreepMenu(){
        System.out.println("Select number to: \n" +
                "1. Show all mentors\n" +
                "2. Add a new mentor\n" +
                "3. Edit mentor by id\n" +
                "4. Remove mentor by id\n" +
                "0. Back to menu");
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
        boolean isActive = true;
        Mentor mentor = new Mentor(login, password, studentTypeId, isActive, firstName, lastName);
        CreepDAO creepDAO = new CreepDAO();
        //creepDAOImplementation.addMentor(mentor);
        mentorsList.add(mentor);
        return mentorsList;
    }

    public static void displayCreepSubmenu() {
        System.out.println("Edit mentor. What do you want to edit? \n" +
                "1. To edit mentor login\n" +
                "2. To edit mentor password\n" +
                "3. To edit mentor name\n" +
                "4. To edit mentor last name");
    }

    public static Mentor editMentor(int id){
        System.out.println("Insert new mentor's login: ");
        String login = scanner.next();
        System.out.println("Insert new mentor's password: ");
        String password = scanner.next();
        System.out.println("Insert new mentor's name: ");
        String firstName = scanner.next();
        System.out.println("Insert new mentor's last name: ");
        String lastName = scanner.next();

        int studentTypeId = 2;
        boolean isActive = true;
        Mentor mentor = new Mentor(login, password, studentTypeId, isActive, firstName, lastName);
        return mentor;
    }

    public static void setMentorUnactive(){
        System.out.println("Choose id of mentor you want to remove: ");
    }

    public static int menuToChoseEditStudentOption() {
        Scanner editChose = new Scanner(System.in);
        boolean isRuning = true;
        while (isRuning) {
            String menu = "\nPleas Chose what do you want to edit:\n" +
                    "1. Student data\n" +
                    "2. Student Quest\n" +
                    "3. Back";

            System.out.println(menu);
            int result = editChose.nextInt();
            if (result == 1 || result == 2 || result == 3 ){
                isRuning = false;
                return result;
            }else {
                errorMassageIfBadChoseInMenu();
            }
        }

        return 0;
    }

    public static Student editStudent(Student student) {
        Scanner scannerForEdit = new Scanner(System.in);
        Scanner scannerFor= new Scanner(System.in);

        System.out.println("Pleas enter a new Login: ");
        String login = scanner.next();
        student.setLogin(login);
        System.out.println("Pleas enter a new Password: ");
        String password = scanner.next();
        student.setPassword(password);
        System.out.println("Pleas enter a new First Name: ");
        String firstName = scannerForEdit.nextLine();
        student.setFirstname(firstName);
        System.out.println("Pleas enter a new Last Name: ");
        String lasttName = scannerFor.nextLine();
        student.setLastname(lasttName);

        return student;
    }

    public static void printStudentMenu() {
        System.out.println("Select number to: \n" +
                "1. Show all items in the store\n" +
                "2. Show all coincubators in the store\n" +
                "3. Show all your coins\n" +
                "4. Show user's items\n" +
                "0. Back to menu");
    }

    public static void printItemMenu() {
        System.out.println("\n1. Buy item by id\n" +
                "2. Back to menu");
    }

    public static void chooseItemById(){
        System.out.println("choose item id to buy: ");
    }

    public static void printUseCardId(){
        System.out.println("Choose id of card you want to use: ");
    }

    public static void useCardMenu(){
        System.out.println("\n1.Use card" +
                "\n2.Back to menu");
    }

}
