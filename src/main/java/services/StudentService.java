package services;

import daoImplementation.StudentDAOImplementation;
import models.Student;
import view.StaticUi;

import java.util.List;

public class StudentService {
    StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();

    public void addNewStudent(){
        studentDAOImplementation.addStudent(new Student(StaticUi.getFirstNameInput(), StaticUi.getLastNameInput(), Student.userType, true, StaticUi.getLoginInput(), StaticUi.getPasswordInput()));
    }
    public void editStudent(){
        //TODO Bartek
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
}
