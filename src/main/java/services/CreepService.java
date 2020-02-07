package services;

import DAO.CreepDAO;
import models.Creep;
import models.Mentor;
import models.User;
import view.StaticUi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreepService {
    CreepDAO creepDAO = new CreepDAO();

    public void editMentorSubmenu(){
        CreepDAO creepDAO = new CreepDAO();
        int idToEdit = StaticUi.getIdInput();
        Scanner sc = new Scanner(System.in);
        StaticUi.displayEditQuestChoice();
        Mentor mentor = creepDAO.getMentorById(idToEdit);
        boolean isRunning = true;
        while (isRunning) {
            StaticUi.displayCreepSubmenu();
            String submenu = sc.next();
            switch (submenu) {
                case "1":
                    String newLogin = StaticUi.getMentorLoginInput();
                    mentor.setLogin(newLogin);
                    break;
                case "2":
                    String newPassword = StaticUi.getMentorPasswordInput();
                    mentor.setPassword(newPassword);
                    break;
                case "3":
                    String newFirstName = StaticUi.getMentorNameInput();
                    mentor.setFirstname(newFirstName);
                    break;
                case "4":
                    String newLastName = StaticUi.getMentorLastnameInput();
                    mentor.setLastname(newLastName); // TODO nie przestawia się na true
                    break;
                case "6":
                    isRunning = false;
                    break;
            }
        }
        //creepDAOImplementation.editMentor(mentor);
    }

    //Refactored creep
    public List<User> getAllMentorsList(ResultSet resultSet){
        List<User> mentorsList = new ArrayList<>();
        try {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId2 = resultSet.getInt("user_type_id");
                boolean isActive = resultSet.getBoolean("is_active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Mentor mentor = new Mentor(id, login, password, userTypeId2, isActive, firstName, lastName);
                mentorsList.add(mentor);
            }
            resultSet.close();
        }
        catch (SQLException e){
            e.getMessage();
        }
        return mentorsList;
    }

    public int getUserDetailsId(User mentor) {
        return creepDAO.getMentorDetails(mentor);
    }

    public void editCreep(Creep creepToEdit, int creepDetailsId) {
        creepDAO.editCreep(creepToEdit, creepDetailsId);
    }
}

