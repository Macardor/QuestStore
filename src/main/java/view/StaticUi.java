package view;

import models.Coincubator;

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

    public static void errorMassageIdNotInDB(){
        System.out.println("You can chose only Id available in DataBase !!!");
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

        System.out.println("Pleas enter a new Name: ");
        String name = scanner.next();
        coincubator.setName(name);
        System.out.println("Pleas enter a new Description: ");
        String description = scannerForEdit.next();
        coincubator.setDescription(description);
        System.out.println("Pleas enter a new Donation price: ");
        int targetDonation = scanner.nextInt();
        coincubator.setTargetDonation(targetDonation);

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
}
