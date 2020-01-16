package services;

import daoImplementation.ItemDAOImplementation;
import models.Item;
import view.StaticUi;

import java.util.Scanner;

public class ItemService {
    private ItemDAOImplementation itemDAOImplementation = new ItemDAOImplementation();

    public void addNewItem() {
        itemDAOImplementation.addItem(new Item(StaticUi.getNameInput(), StaticUi.getPriceInput(), StaticUi.getDescriptionInput(), StaticUi.getBoolInput()));
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

    public void turnOffItem() {
        itemDAOImplementation.deleteItem(StaticUi.getIdInput());
    }

    public void getItemsList() {
        itemDAOImplementation.getItemsList();
    }

    public void getItemById() {
        itemDAOImplementation.getItemById(StaticUi.getIdInput());
    }
}
