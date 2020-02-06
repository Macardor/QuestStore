package services;

import DAO.ItemDAO;
import models.Item;
import view.StaticUi;

import java.util.List;
import java.util.Scanner;

public class ItemService {
    private ItemDAO itemDAO = new ItemDAO();

    public void addNewItem() {
        itemDAO.addItem(new Item(StaticUi.getNameInput(), StaticUi.getPriceInput(), StaticUi.getDescriptionInput(), StaticUi.getBoolInput()));
    }
    public void editItemSubmenu(){
        Scanner sc = new Scanner(System.in);
        StaticUi.displayEditItemChoice();
        int idToEdit = StaticUi.getIdInput();
        Item item = itemDAO.getItemById(idToEdit);

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
        itemDAO.editItem(item);
    }

    public void turnOffItem(int id) {
        itemDAO.deleteItem(id);
    }

    public List<Item> getItemsList() {
        return itemDAO.getItemsList();
    }

    public void getItemById() {
        itemDAO.getItemById(StaticUi.getIdInput());
    }

    public void addNewItem(Item item) {
        itemDAO.addItem(item);
    }

    public List<Item> getActiveItemsList() {
        return itemDAO.getActiveItemsList();
    }
}
