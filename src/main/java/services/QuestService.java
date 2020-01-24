package services;

import daoImplementation.QuestDAOImplementation;
import models.Quest;
import view.StaticUi;

import java.util.List;
import java.util.Scanner;

public class QuestService {
    QuestDAOImplementation questDAOImplementation = new QuestDAOImplementation();
    Scanner scanner = new Scanner(System.in);
    public void addNewQuest(Quest quest){
        questDAOImplementation.addQuest(quest);

    }
    public void editQuest(){
        editStudentSubmenu();

    }
    public void deleteQuest(int id){
        questDAOImplementation.deleteQuest(id);

    }
    public List<Quest> getQuestList(){
        return questDAOImplementation.getAllQuests();
    }
    public void getQuestById(){
        questDAOImplementation.getQuestById(StaticUi.getIdInput());

    }
    public void editStudentSubmenu(){
        Scanner sc = new Scanner(System.in);
        StaticUi.displayEditQuestChoice();
        int idToEdit = StaticUi.getIdInput();

        Quest quest = questDAOImplementation.getQuestById(idToEdit);
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
        questDAOImplementation.editQuest(quest);
    }

    public List<Quest> getAllActiveQuestList() {
        return questDAOImplementation.getAllActiveQuests();
    }
}
