package controllers;

import services.ItemService;
import view.StaticUi;

import java.util.Scanner;

class ItemController {
    void itemMenu(){
        ItemService itemService = new ItemService();
        Scanner scanner = new Scanner(System.in);

        StaticUi.displayItemMenu();
        String option = scanner.next();
        switch (option) {
            case "1":
                itemService.addNewItem();
                break;
            case "2":
                itemService.editItemSubmenu();
                break;
            case "3":
//                itemService.turnOffItem();
                break;
            case "4":
                itemService.getItemsList();
                break;
            case "5":
                itemService.getItemById();
                break;
        }
    }
}
