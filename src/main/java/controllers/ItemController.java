package controllers;

import daoImplementation.ItemDAOImplementation;
import daoImplementation.QuestDAOImplementation;
import models.Item;
import models.Quest;

import java.util.List;
import java.util.Scanner;

public class ItemController {
    ItemDAOImplementation itemDAOImplementation = new ItemDAOImplementation();
    Scanner scanner = new Scanner(System.in);

    public void displayItemMenu(){
        System.out.println("1. Add Item\n" +
                "2. Edit Item\n" +
                "3. Delete Item\n" +
                "4. Get all Item\n" +
                "5. Get Item by id\n");
    }

    public void itemMenu(){
        displayItemMenu();
        String option = scanner.next();
        displayAllItems(itemDAOImplementation.getItemsList());
        switch (option) {
            case "1":
                itemDAOImplementation.addItem(new Item(getNameInput(), getPriceInput(), getDescriptionInput(), getBoolInput()));
                break;
            case "2":
                editItemSubmenu();
                break;
            case "3":
                itemDAOImplementation.deleteItem(getIdInput());
                break;
            case "4":
                itemDAOImplementation.getItemsList();
                break;
            case "5":
                itemDAOImplementation.getItemById(getIdInput());
                break;
        }
    }

    public void editItemSubmenu(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Which item do you want to edit?");
        int idToEdit = getIdInput();
        Item item = itemDAOImplementation.getItemById(idToEdit);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Edit item. What do you want to edit?\n" +
                    "1. To edit name item\n" +
                    "2. To edit item price\n" +
                    "3. To edit item description\n" +
                    "4. To activate or deactivate quest\n" +
                    "6. To submit");
            String submenu = sc.next();
            switch (submenu) {
                case "1":
                    String newName = getNameInput();
                    item.setName(newName);
                    break;
                case "2":
                    int newPrice = getPriceInput();
                    item.setPrice(newPrice);
                    break;
                case "3":
                    String newDescription = getDescriptionInput();
                    item.setDescription(newDescription);
                    break;
                case "4":
                    boolean newBool = getBoolInput();
                    item.setActive(newBool); // TODO nie przestawia się na true
                    break;
                case "6":
                    isRunning = false;
                    break;
            }
        }
        System.out.println("success");
        itemDAOImplementation.editItem(item);
    }

    public void displayAllItems(List<Item> itemList){
        for (Item element : itemList) {
            System.out.println(element.getId() + " | " + element.getName() + " | " + element.getPrice() + " | " + element.getDescription() + " | " + element.isActive());
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
    public int getPriceInput(){
        System.out.println("Enter price: ");
        int priceInput = scanner.nextInt();
        return priceInput;
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
