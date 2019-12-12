package interfaces;

import models.components.Item;

import java.util.List;

public interface ItemDAO {
    void addItem(String name, int price, String description, boolean isActive);
    void deleteItem(int id);
    void editItem(int id);
    List<Item> getEquipment();
}
