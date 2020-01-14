package controllers;
import daoImplementation.MentorDAOImplementation;
import models.Coincubator;
import models.Student;
import services.CoincubatorService;

import java.util.Scanner;

public class MentorController implements BaseController {
    Scanner scanner = new Scanner(System.in);
    MentorDAOImplementation mentorDAOImplementation;
    private CoincubatorService coincubatorService = new CoincubatorService();
    Student student;

    private void mentorMenu(){
        displayMentorMenu();
        String option = scanner.next();
        switch (option){
            case "1":
                mentorDAOImplementation.getStudentsList();
                enterNewStudent();
                mentorDAOImplementation.getStudentsList();
                break;
            case "2":
                mentorDAOImplementation.getStudentsList();
                int idInput = scanner.nextInt();
                mentorDAOImplementation.deleteStudent(idInput);
                mentorDAOImplementation.getStudentsList();
                break;
            case "3":
                mentorDAOImplementation.getStudentsList();
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                coincubatorService.showAllCoincubators();
                break;
            case "7":
                coincubatorService.showAllCoincubators();
                coincubatorService.editCoincubatorById();
                break;
            case "8":
                coincubatorService.showAllCoincubators();
                coincubatorService.deleteCoincubatorById();
        }
        scanner.close();
    }

    private void displayMentorMenu(){
        System.out.println("Select number to: \n" +
                "1. Add new student\n" +
                "2. Delete student\n" +
                "3. Get All Students\n" +
                "4. Delete student\n" +
                "5. Add new Coincubator\n" +
                "6. Show all Coincubators\n" +
                "7. Edit Coincubator\n" +
                "8. Delete Coincubator");
    }

    private void enterNewStudent(){

        System.out.println("Insert login: ");
        String login =  scanner.next();
        System.out.println("Insert password: ");
        String password = scanner.next();
        System.out.println("Insert first name: ");
        String firstName = scanner.next();
        System.out.println("Insert last name: ");
        String lastName = scanner.next();

        mentorDAOImplementation.addStudent(new Student(login, password, Student.userType, firstName, lastName));
    }


//    public static void main(String[] args) {
//        MentorController mentorController = new MentorController();
//        mentorController.mentorMenu();
//    }


    @Override
    public void run() {
        mentorMenu();
    }


}
