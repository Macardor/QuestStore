package controllers;

import daoImplementation.QuestDAOImplementation;
import models.Quest;

import javax.xml.transform.Source;
import java.util.List;
import java.util.Scanner;

public class QuestController {
    QuestDAOImplementation questDAOImplementation = new QuestDAOImplementation();
    Scanner scanner = new Scanner(System.in);

    public void displayQuestMenu(){
        System.out.println("1. Add Quest\n" +
                "2. Edit Quest\n" +
                "3. Delete quest\n" +
                "4. Get all quests\n" +
                "5. Get quest by id\n");
    }
    public void questMenu(){
        displayQuestMenu();
        String option = scanner.nextLine();
        displayAllQuests(questDAOImplementation.getAllQuests());
        switch (option){
            case "1":
                questDAOImplementation.addQuest(new Quest(getNameInput(), getDescriptionInput(), getRewardInput(), getBoolInput(option)));
                break;
            case "2":
                displayAllQuests(questDAOImplementation.getAllQuests());
                System.out.println("Which quests do you want to edit?");
                Quest quest = questDAOImplementation.getQuestById(getIdInput());

                System.out.println("Edit Quest. What do you want to edit?\n" +
                        "1. To edit name quest\n" +
                        "2. To edit description quest\n" +
                        "3. To edit reward quest\n" +
                        "4. To activate or deactivate quest\n");
                switch (option){
                    case "1":
                        String newName = getNameInput();
                        quest.setName(newName);
                        break;
                    case "2":
                        String newDescription = getDescriptionInput();
                        quest.setDescription(newDescription);
                        break;
                    case "3":
                        int newReward = getRewardInput();
                        quest.setReward(newReward);
                        break;
                    case "4":
                        boolean newBool = getBoolInput();
                        quest.setActive(newBool);
                        break;
                    default:
                        break;
                }
                questDAOImplementation.editQuest(quest);
                break;
            case "3":
                questDAOImplementation.deleteQuest(getIdInput());
                break;
            case "4":
                questDAOImplementation.getAllQuests();
                break;
            case "5":
                questDAOImplementation.getQuestById(getIdInput());
        }
    }

    public void displayAllQuests(List<Quest> questList){
        for (Quest element : questList) {
            System.out.println(element.getId() + " | " + element.getName() + " | " + element.getDescription() + " | " + element.getReward() + " | " + element.isActive());
        }
    }

    public int getIdInput(){
        System.out.println("Enter id: ");
        return scanner.nextInt();
    }
    public String getNameInput(){
        System.out.println("Enter name: ");
        String nameInput = scanner.nextLine();
        return nameInput;
    }
    public String getDescriptionInput(){
        System.out.println("Enter description: ");
        String descriptionInput = scanner.nextLine();
        return descriptionInput;
    }
    public int getRewardInput(){
        System.out.println("Enter reward: ");
        int rewardInput = scanner.nextInt();
        return rewardInput;
    }
    public boolean getBoolInput(){
        System.out.println("Enter is quest active");
        String bool = scanner.nextLine();
        if (bool == "t"){
            return true;
        }else {
            return false;
        }
    }
}
