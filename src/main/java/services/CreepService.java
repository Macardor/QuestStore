package services;

import daoImplementation.CreepDAOImplementation;
import models.Creep;
import models.Mentor;
import models.Quest;
import models.User;
import view.StaticUi;

import java.awt.*;
import java.util.Scanner;

public class CreepService {
    CreepDAOImplementation creepDAOImplementation = new CreepDAOImplementation();

    public void editMentorSubmenu(){
        CreepDAOImplementation creepDAOImplementation = new CreepDAOImplementation();
        int idToEdit = StaticUi.getIdInput();
        Scanner sc = new Scanner(System.in);
        StaticUi.displayEditQuestChoice();
        Mentor mentor = creepDAOImplementation.getMentorById(idToEdit);
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

    public int getUserDetailsId(User mentor) {
        return creepDAOImplementation.getUserDetailsId(mentor);
    }

    public void editCreep(Creep creepToEdit, int creepDetailsId) {
        creepDAOImplementation.editCreep(creepToEdit, creepDetailsId);
    }
}

