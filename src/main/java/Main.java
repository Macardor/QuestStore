import controllers.CreepController;
import controllers.LoginController;
import controllers.MentorController;
import services.StudentService;
import view.StaticUi;

public class Main {
    public static void main(String[] args) {

//        LoginController loginController = new LoginController();
//        loginController.run();
//        MentorController mentorController = new MentorController();
//        StudentService studentService = new StudentService();
//        boolean isRunning = true;
//        while (isRunning) {
//            StaticUi.displayAllStudents(studentService.getStudentList());
//            break;
//        }

        CreepController creepController = new CreepController();
        creepController.run();
    }
}
