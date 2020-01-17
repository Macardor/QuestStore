package services;

import daoImplementation.TestDaoImpl;
import models.Student;

import java.util.List;

public class TestService {
    TestDaoImpl testDao = new TestDaoImpl();

    public List<Student> getFkinListOfIdiots(){
        return testDao.getFkinStudentsList();
    }
    public void addIdiot(Student student){
        testDao.addStudent(student);
    }
}
