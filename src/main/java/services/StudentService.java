package services;

import DAO.MentorDAO;
import DAO.QuestDAO;
import DAO.StudentDAO;
import models.Quest;
import models.Student;
import models.User;
import view.StaticUi;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StudentService {
    StudentDAO studentDAO = new StudentDAO();
    MentorDAO mentorDAO = new MentorDAO();
    QuestDAO questDAO = new QuestDAO();




    //Student Refactor
    public List<Student> getActiveStudentsList(ResultSet resultSet){
        List<Student> studentsList = new ArrayList<>();
        try{
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId = resultSet.getInt("user_type_id");
                boolean isActive = resultSet.getBoolean("is_active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                Student student = new Student(id, login, password, userTypeId, isActive, firstName, lastName);
                studentsList.add(student);

            }
            resultSet.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return  studentsList;
    }


    public void addNewStudent(Student student){
        studentDAO.addStudent(student);
    }
    public void deleteStudent(int id){
        studentDAO.deleteStudent(id);
    }
    public List<Student> getStudentList(){
        return studentDAO.getStudentsList();
    }
//    public List<Student> getActiveStudentList(){
//        return studentDAO.getActiveStudentsFromDb();
//    }
    public Student getStudentByUserId(int id){
        return studentDAO.getStudentByUserId(id);
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
//
//
//    public List<Student> getActiveStudentsList() {
//        return studentDAO.getActiveStudentsFromDb();
//    }

    public int getUserDetailsId(User student){
        return studentDAO.getUserDetailsId(student);
    }

    public void editStudent(Student student, int studentDetailsId) {
        studentDAO.editStudent(student, studentDetailsId);
    }
    public boolean checkIfUserHasEnoughCoins(int studentId, int coinsToPay){
        int studentCoins = studentDAO.getStudentCoins(studentId);
        if (studentCoins < coinsToPay){
            return false;
        }
        return true;
    }
}
//    public void editChooseToStudent() {
//        mentorDAO.getActiveStudentsFromDb();
//        int id = StaticUi.getIdInput();
//        int option = StaticUi.menuToChoseEditStudentOption();
//        Student student = studentDAO.isStudentWithIdInDB(id);
//        if (student != null) {
//            if (option == 1) {
//                Student newStudent = StaticUi.editStudent(student);
//                int userDetailsId = studentDAO.getUserDetailsId(newStudent);
//                studentDAO.editStudent(newStudent, userDetailsId);
//            } else if (option == 2) {
//                List<Quest> questsList = questDAO.getAllQuestsNotDoneByStudent(student);
//
//                System.out.println("(" + questsList.size() + ") quest more to finish!");
//                if (!questsList.isEmpty()) {
//                    int questID = StaticUi.getIdInput();
//                    int studentId = studentDAO.getStudentId(student);
//                    Quest questToActive = getGuestFromListById(questsList, questID);
//                    Date date = getCurrentDate();
//                    questDAO.setQuestActiveForStudent(studentId, questToActive, date);
//                } else {
//                    System.out.println("no more quests for this student");
//                }
//
//            } else {
//                StaticUi.errorMassageIdNotInDB();
//            }
//        }
//    }