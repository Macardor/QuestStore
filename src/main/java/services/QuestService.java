package services;

import DAO.QuestDAO;
import models.Quest;
import view.StaticUi;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class QuestService {
    QuestDAO questDAO = new QuestDAO();
    Scanner scanner = new Scanner(System.in);
    public void addNewQuest(Quest quest){
        questDAO.addQuest(quest);

    }
    public void editQuest(){
        editStudentSubmenu();

    }
    public void deleteQuest(int id){
        questDAO.deleteQuest(id);

    }
    public List<Quest> getQuestList(){
        return questDAO.getAllQuests();
    }
    public void getQuestById(){
        questDAO.getQuestById(StaticUi.getIdInput());

    }
    public void editStudentSubmenu(){
        Scanner sc = new Scanner(System.in);
        StaticUi.displayEditQuestChoice();
        int idToEdit = StaticUi.getIdInput();

        Quest quest = questDAO.getQuestById(idToEdit);
        boolean isRunning = true;
        while (isRunning) {
            StaticUi.displayStudentSubmenu();
            String submenu = sc.next();
            switch (submenu) {
                case "1":
                    String newName = StaticUi.getNameInput();
                    quest.setName(newName);
                    break;
                case "2":
                    String newDescription = StaticUi.getDescriptionInput();
                    quest.setDescription(newDescription);
                    break;
                case "3":
                    int newReward = StaticUi.getRewardInput();
                    quest.setReward(newReward);
                    break;
                case "4":
                    boolean newBool = StaticUi.getBoolInput();
                    quest.setActive(newBool); // TODO nie przestawia się na true
                    break;
                case "6":
                    isRunning = false;
                    break;
            }
        }
        questDAO.editQuest(quest);
    }

    public List<Quest> getAllActiveQuestList() {
        return questDAO.getAllActiveQuests();
    }

    public void addQuest(PreparedStatement ps, Quest quest){
        ps = questDAO.getPsForAddQuest();
        try {
            ps.setString(1, quest.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
