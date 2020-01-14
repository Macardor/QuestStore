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
        String option = scanner.next();
        displayAllQuests(questDAOImplementation.getAllQuests());
        switch (option){
            case "1":
                questDAOImplementation.addQuest(new Quest(getNameInput(), getDescriptionInput(), getRewardInput(), getBoolInput()));
                break;
            case "2":
                editStudentSubmenu();
                break;
            case "3":
                questDAOImplementation.deleteQuest(getIdInput());
                break;
            case "4":
                questDAOImplementation.getAllQuests();
                break;
            case "5":
                questDAOImplementation.getQuestById(getIdInput());
                break;
        }
    }

    public void editStudentSubmenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Which quests do you want to edit?");
        int idToEdit = getIdInput();
        Quest quest = questDAOImplementation.getQuestById(idToEdit);
        System.out.println(quest.getName());
        boolean isRunning = true;


        while (isRunning) {
            System.out.println("Edit Quest. What do you want to edit?\n" +
                    "1. To edit name quest\n" +
                    "2. To edit description quest\n" +
                    "3. To edit reward quest\n" +
                    "4. To activate or deactivate quest\n" +
                    "6. To submit");
            String submenu = sc.next();
            switch (submenu) {
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
                    quest.setActive(newBool); // TODO nie przestawia się na true
                    break;
                case "6":
                    isRunning = false;
                    break;
            }
        }
        System.out.println("end of method");
        questDAOImplementation.editQuest(quest);
    }

    public void displayAllQuests(List<Quest> questList){
        for (Quest element : questList) {
            System.out.println(element.getId() + " | " + element.getName() + " | " + element.getDescription() + " | " + element.getReward() + " | " + element.isActive());
        }
    }

    public int getIdInput(){
        System.out.println("Enter id: ");
        int idInput = scanner.nextInt();
        return idInput;
    }
    public String getNameInput(){
        System.out.println("Enter name: ");
        String nameInput = scanner.next();
        return nameInput;
    }
    public String getDescriptionInput(){
        System.out.println("Enter description: ");
        String descriptionInput = scanner.next();
        return descriptionInput;
    }
    public int getRewardInput(){
        System.out.println("Enter reward: ");
        int rewardInput = scanner.nextInt();
        return rewardInput;
    }
    public boolean getBoolInput(){
        System.out.println("Enter is quest active");
        String bool = scanner.next();
        if (bool == "t"){
            return true;
        }else {
            return false;
        }
    }
}
