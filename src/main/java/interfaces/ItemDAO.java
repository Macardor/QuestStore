package interfaces;

import models.components.Item;

import java.util.List;

public interface ItemDAO {
    void addItem(Item item);
    void deleteItem(int id);
    void editItem(Item  item);
    List<Item> getEquipment();
}
