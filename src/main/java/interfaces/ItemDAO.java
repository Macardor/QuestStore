package interfaces;

public interface ItemDAO {
    void addItem(String name, int price, String description, boolean isActive);
    void deleteItem(int id);
    void editItem(int id);
}
