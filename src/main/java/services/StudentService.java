package services;
import daoImplementation.MentorDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import models.Student;
import view.StaticUi;
import java.util.List;

public class StudentService {
    StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
    MentorDAOImplementation mentorDAOImplementation = new MentorDAOImplementation();

    public void addNewStudent(){
        studentDAOImplementation.addStudent(new Student(StaticUi.getFirstNameInput(), StaticUi.getLastNameInput(), Student.userType, true, StaticUi.getLoginInput(), StaticUi.getPasswordInput()));
    }
    public void deleteStudent(){
        studentDAOImplementation.deleteStudent(StaticUi.getIdInput());
    }
    public List<Student> getStudentList(){
        return studentDAOImplementation.getStudentsList();
    }
    public List<Student> getActiveStudentList(){
        return studentDAOImplementation.getActiveStudentsList();
    }
    public Student getStudentByUserId(int id){
        return studentDAOImplementation.getStudentByUserId(id);
    }

    public void editChoseToStudent() {
        mentorDAOImplementation.getActiveStudentsList();
        int id = StaticUi.getIdInput();
        int option = StaticUi.menuToChoseEditStudentOption();
        Student student = studentDAOImplementation.isStudentWithIdInDB(id);
        if(student != null){
            if (option == 1){
                Student newStudent = StaticUi.editStudent(student);
                System.out.println("test1");
                int userDetailsId = studentDAOImplementation.getUserDetailsId(newStudent);
                System.out.println("test2");
                studentDAOImplementation.editStudent(newStudent, userDetailsId);

            }else if (option == 2){
                System.out.println("option2");

        }else {
                StaticUi.errorMassageIdNotInDB();
            }
        }
    }
}
