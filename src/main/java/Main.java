import controllers.MentorController;
import services.StudentService;
import view.StaticUi;

public class Main {
    public static void main(String[] args) {
        MentorController mentorController = new MentorController();
        StudentService studentService = new StudentService();
        boolean isRunning = true;
        while (isRunning) {
            StaticUi.displayAllStudents(studentService.getStudentList());
            break;
        }
    }
}
