import controllers.CreepController;
import controllers.LoginController;
import controllers.MentorController;
import controllers.StudentController;
import daoImplementation.CreepDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import services.StudentService;
import view.StaticUi;

public class Main {
    public static void main(String[] args) {

//        LoginController loginController = new LoginController();
//        loginController.run();

        CreepDAOImplementation creepDAOImplementation = new CreepDAOImplementation();
        creepDAOImplementation.showAllMentors();

//        MentorController mentorController = new MentorController();
//        StudentService studentService = new StudentService();
//        boolean isRunning = true;
//        while (isRunning) {
//            StaticUi.displayAllStudents(studentService.getStudentList());
//            break;
//        }

//        CreepController creepController = new CreepController();
//        creepController.run();
       /* StudentController studentController = new StudentController();
        studentController.run();*/
//
//        StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
//        studentDAOImplementation.getItemPrice(5);

    }
}
