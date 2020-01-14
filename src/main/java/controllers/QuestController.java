package controllers;

import daoImplementation.QuestDAOImplementation;
import models.Quest;
import view.StaticUi;

import java.util.List;
import java.util.Scanner;

public class QuestController {
    QuestDAOImplementation questDAOImplementation = new QuestDAOImplementation();
    Scanner scanner = new Scanner(System.in);

    public void questMenu(){
        StaticUi.displayQuestMenu();
        String option = scanner.next();
        StaticUi.displayAllQuests(questDAOImplementation.getAllQuests());
        switch (option){
            case "1":
                questDAOImplementation.addQuest(new Quest(StaticUi.getNameInput(), StaticUi.getDescriptionInput(), StaticUi.getRewardInput(), StaticUi.getBoolInput()));
                break;
            case "2":
                editStudentSubmenu();
                break;
            case "3":
                questDAOImplementation.deleteQuest(StaticUi.getIdInput());
                break;
            case "4":
                questDAOImplementation.getAllQuests();
                break;
            case "5":
                questDAOImplementation.getQuestById(StaticUi.getIdInput());
                break;
        }
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
}
