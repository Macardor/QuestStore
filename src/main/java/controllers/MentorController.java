package controllers;
import daoImplementation.MentorDAOImplementation;
import models.Student;
import view.StaticUi;

import java.util.List;
import java.util.Scanner;

public class MentorController implements BaseController {
    Scanner scanner = new Scanner(System.in);
    MentorDAOImplementation mentorDAOImplementation;

    public void mentorMenu(){
        StaticUi.displayMentorMenu();
        String option = scanner.next();
        boolean isRunning = true;
        while (isRunning) {
            switch (option) {
                case "1":
                    mentorDAOImplementation.addStudent(new Student(StaticUi.getFirstNameInput(), StaticUi.getLastNameInput(), Student.userType, true, StaticUi.getLoginInput(), StaticUi.getPasswordInput()));
                    break;
                case "2":
                    mentorDAOImplementation.deleteStudent(StaticUi.getIdInput());
                    break;
                case "3":
                    StaticUi.displayAllStudents(mentorDAOImplementation.getStudentsList());
                    break;
                case "4":
                    StaticUi.displayAllStudents(mentorDAOImplementation.getActiveStudentsList());
                    break;
                case "5":
                    mentorDAOImplementation.getStudentByUserId(StaticUi.getIdInput());
                    break;
                case "6":
                    isRunning = false;
            }
        }
    }

    @Override
    public void run() {
        mentorMenu();
    }
}




//    public int getIdInput(){
//        System.out.println("Enter id: ");
//        int idInput = scanner.nextInt();
//        return idInput;
//    }
//    public String getFirstNameInput(){
//        System.out.println("Enter first name: ");
//        String nameInput = scanner.next();
//        return nameInput;
//    }
//    public String getLastNameInput(){
//        System.out.println("Enter last name: ");
//        String lNameInput = scanner.next();
//        return lNameInput;
//    }
//    public int getUserTypeInput(){
//        System.out.println("Enter user type: ");
//        int userTypeInput = scanner.nextInt();
//        return userTypeInput;
//    }
//    public String getLoginInput(){
//        System.out.println("Enter login: ");
//        String loginInput = scanner.next();
//        return loginInput;
//    }
//    public String getPasswordInput(){
//        System.out.println("Enter password: ");
//        String passwordInput = scanner.next();
//        return passwordInput;
//    }
//    public boolean getBoolInput(){
//        System.out.println("Enter is student active");
//        String bool = scanner.next();
//        if (bool == "t"){
//            return true;
//        }else {
//            return false;
//        }
//    }








//    public void editMentorSubmenu(){
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Which student do you want to edit?");
//        int idToEdit = getIdInput();
//        Student student = mentorDAOImplementation.getStudentById(idToEdit);
//
//        boolean isRunning = true;
//        while (isRunning) {
//            System.out.println("Edit student. What do you want to edit?\n" +
//                    "1. To edit name student\n" +
//                    "2. To edit student price\n" +
//                    "3. To edit student description\n" +
//                    "4. To activate or deactivate quest\n" +
//                    "6. To submit");
//            String submenu = sc.next();
//            switch (submenu) {
//                case "1":
//                    String newName = getNameInput();
//                    student.setName(newName);
//                    break;
//                case "2":
//                    int newPrice = getPriceInput();
//                    student.setPrice(newPrice);
//                    break;
//                case "3":
//                    String newDescription = getDescriptionInput();
//                    student.setDescription(newDescription);
//                    break;
//                case "4":
//                    boolean newBool = getBoolInput();
//                    student.setActive(newBool); // TODO nie przestawia się na true
//                    break;
//                case "6":
//                    isRunning = false;
//                    break;
//            }
//        }
//        System.out.println("success");
//        mentorDAOImplementation.editStudent(student);
//    }