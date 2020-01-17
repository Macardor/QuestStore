package controllers;

import services.QuestService;
import view.StaticUi;

import java.util.Scanner;

public class QuestController {

    public void questMenu(){
        QuestService qs = new QuestService();
        Scanner scanner = new Scanner(System.in);
        StaticUi.displayQuestMenu();
        String option = scanner.next();
        switch (option){
            case "1":
                qs.addNewQuest();
                break;
            case "2":
                qs.editQuest();
                break;
            case "3":
                qs.deleteQuest();
                break;
            case "4":
                qs.getQuestList();
                break;
            case "5":
                qs.getQuestById();
                break;
        }
    }
}
