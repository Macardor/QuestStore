package controllers;

import daoImplementation.MentorDAOImplementation;
import models.Student;
import services.CoincubatorService;
import services.StudentService;
import view.StaticUi;

import java.util.Scanner;

public class MentorController implements BaseController {
    public void mentorMenu(){
        MentorDAOImplementation mentorDAOImplementation = new MentorDAOImplementation();
        Scanner scanner = new Scanner(System.in);
        StudentService ss = new StudentService();
        CoincubatorService coincubatorService = new CoincubatorService();

        StaticUi.displayMentorMenu();
        String option = scanner.next();
            switch (option) {
                case "1":
                    ss.addNewStudent(new Student(StaticUi.getFirstNameInput(), StaticUi.getLastNameInput(), Student.userType, true, StaticUi.getLoginInput(), StaticUi.getPasswordInput()));
                    break;
                case "2":
                    ss.deleteStudent(StaticUi.getIdInput());
                    break;
                case "3":
                    StaticUi.displayAllStudents(ss.getStudentList());
                    break;
                case "4":
                    StaticUi.displayAllStudents(ss.getActiveStudentList());
                    break;
                case "5":
                    ss.getStudentByUserId(StaticUi.getIdInput());
                    break;
                case "6":
                    coincubatorService.addNewCoincubator();
                    break;
                case "7":
                    coincubatorService.showAllCoincubators();
                    break;
                case "8":
                    coincubatorService.showAllCoincubators();
                    coincubatorService.editCoincubatorById();
                    break;
                case "9":
                    coincubatorService.showAllCoincubators();
                    coincubatorService.deleteCoincubatorById();
                    break;
                case "10":
                    ss.editChooseToStudent();
                    break;
                case "0":
//                    isRunning = false;
                    System.out.println("Bay bay");
                    break;
                default:
                    StaticUi.errorMassageIfBadChoseInMenu();
            }
        }

    @Override
    public void run() {
        mentorMenu();
    }
}


//    private void displayMentorMenu(){
//        System.out.println("Select number to: \n" +
//                "1. Add new student\n" +
//                "2. Delete student\n" +
//                "3. Get All Students\n" +
//                "4. Delete student\n" +
//                "5. Add new Coincubator\n" +
//                "6. Show all Coincubators\n" +
//                "7. Edit Coincubator\n" +
//                "8. Delete Coincubator");
//    }

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
