package services;

import daoImplementation.MentorDAOImplementation;
import daoImplementation.QuestDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import models.Coincubator;
import models.Quest;
import models.Student;
import view.StaticUi;

import java.util.List;

public class StudentService {
    StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
    MentorDAOImplementation mentorDAOImplementation = new MentorDAOImplementation();
    QuestDAOImplementation questDAOImplementation = new QuestDAOImplementation();

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
                List<Quest> questsList= questDAOImplementation.getAllQuestsNotDoneByStudent(student);
                int questID = StaticUi.getIdInput();

        }else {
                StaticUi.errorMassageIdNotInDB();
            }
        }
    }
}
