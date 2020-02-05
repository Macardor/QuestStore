package services;

import daoImplementation.ItemDAOImplementation;
import models.Item;
import view.StaticUi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ItemService {
    private ItemDAOImplementation itemDAOImplementation = new ItemDAOImplementation();

    public void addNewItem() {
        itemDAOImplementation.addItem(new Item(StaticUi.getNameInput(), StaticUi.getPriceInput(), StaticUi.getDescriptionInput(), StaticUi.getBoolInput()));
    }

    public void addItem (PreparedStatement ps, Item item){
        try {
            ps.setString(1, item.getName());
            ps.setInt(2, item.getPrice());
            ps.setString(3, item.getDescription());
            ps.setBoolean(4, item.isActive());
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void turnOffItem(int id) {
        itemDAOImplementation.deleteItem(id);
    }

    public List<Item> getItemsList() {
        return itemDAOImplementation.getItemsList();
    }

    public void getItemById() {
        itemDAOImplementation.getItemById(StaticUi.getIdInput());
    }

    public void addNewItem(Item item) {
        itemDAOImplementation.addItem(item);
    }

    public List<Item> getActiveItemsList() {
        return itemDAOImplementation.getActiveItemsList();
    }
}
