package controllers;

import daoImplementation.ItemDAOImplementation;
import daoImplementation.QuestDAOImplementation;
import models.Item;
import models.Quest;
import view.StaticUi;

import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class ItemController {
    ItemDAOImplementation itemDAOImplementation = new ItemDAOImplementation();
    Scanner scanner = new Scanner(System.in);

    public void itemMenu(){
        StaticUi.displayItemMenu();
        String option = scanner.next();
        StaticUi.displayAllItems(itemDAOImplementation.getItemsList());
        switch (option) {
            case "1":
                itemDAOImplementation.addItem(new Item(StaticUi.getNameInput(), StaticUi.getPriceInput(), StaticUi.getDescriptionInput(), StaticUi.getBoolInput()));
                break;
            case "2":
                editItemSubmenu();
                break;
            case "3":
                itemDAOImplementation.deleteItem(StaticUi.getIdInput());
                break;
            case "4":
                itemDAOImplementation.getItemsList();
                break;
            case "5":
                itemDAOImplementation.getItemById(StaticUi.getIdInput());
                break;
        }
    }

    public void editItemSubmenu(){
        Scanner sc = new Scanner(System.in);
        StaticUi.displayEditItemChoice();
        int idToEdit = StaticUi.getIdInput();
        Item item = itemDAOImplementation.getItemById(idToEdit);

        boolean isRunning = true;
        while (isRunning) {
            StaticUi.displayItemSubMenu();
            String submenu = sc.next();
            switch (submenu) {
                case "1":
                    String newName = StaticUi.getNameInput();
                    item.setName(newName);
                    break;
                case "2":
                    int newPrice = StaticUi.getPriceInput();
                    item.setPrice(newPrice);
                    break;
                case "3":
                    String newDescription = StaticUi.getDescriptionInput();
                    item.setDescription(newDescription);
                    break;
                case "4":
                    boolean newBool = StaticUi.getBoolInput();
                    item.setActive(newBool); // TODO nie przestawia się na true
                    break;
                case "6":
                    isRunning = false;
                    break;
            }
        }
        itemDAOImplementation.editItem(item);
    }
}
