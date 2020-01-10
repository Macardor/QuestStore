package interfaces;

import models.Item;

import java.util.List;

public interface ItemDAO {
    void addItem(Item item);
    void deleteItem(int id);
    void editItem(Item  item);
    List<Item> getEquipment();
}
