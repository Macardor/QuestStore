package services;

import daoImplementation.MentorDAOImplementation;
import daoImplementation.QuestDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import models.Quest;
import models.Student;
import view.StaticUi;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class StudentService {
    StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
    MentorDAOImplementation mentorDAOImplementation = new MentorDAOImplementation();
    QuestDAOImplementation questDAOImplementation = new QuestDAOImplementation();


    public void editChooseToStudent() {
        mentorDAOImplementation.getActiveStudentsList();
        int id = StaticUi.getIdInput();
        int option = StaticUi.menuToChoseEditStudentOption();
        Student student = studentDAOImplementation.isStudentWithIdInDB(id);
        if (student != null) {
            if (option == 1) {
                Student newStudent = StaticUi.editStudent(student);
                int userDetailsId = studentDAOImplementation.getUserDetailsId(newStudent);
                studentDAOImplementation.editStudent(newStudent, userDetailsId);
            } else if (option == 2) {
                List<Quest> questsList = questDAOImplementation.getAllQuestsNotDoneByStudent(student);

                System.out.println("(" + questsList.size() + ") quest more to finish!");
                if (!questsList.isEmpty()) {
                    int questID = StaticUi.getIdInput();
                    int studentId = studentDAOImplementation.getStudentId(student);
                    Quest questToActive = getGuestFromListById(questsList, questID);
                    Date date = getCurrentDate();
                    questDAOImplementation.setQuestActiveForStudent(studentId, questToActive, date);
                } else {
                    System.out.println("no more quests for this student");
                }

            } else {
                StaticUi.errorMassageIdNotInDB();
            }
        }
    }

    public void addNewStudent(Student student){
        studentDAOImplementation.addStudent(student);
    }
    public void deleteStudent(int id){
        studentDAOImplementation.deleteStudent(id);
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

    private Date getCurrentDate() {
        return new Date(Calendar.getInstance().getTime().getTime());
    }

    private Quest getGuestFromListById(List<Quest> questsList, int questID) {
        Quest result = null;
        for (Quest quest : questsList) {
            if (quest.getId() == questID){
                result = quest;
            }
        }
        return result;
    }


    public List<Student> getActiveStudentsList() {
        return studentDAOImplementation.getActiveStudentsList();
    }
}
