package interfaces;

import models.Item;
import models.User;

import java.util.List;

public interface StudentDAO{

    public List<Item> showItems();
    public void showCoincubatos();
    public void showUserItems();
    public void showUserCoins();
    public void showUserQuests();


//    public void addStudent(String login, String password, String firstName, String lastName);
//    public List<User> extractStudent();
//    public void editStudent(int userId);
//    public void removeStudent(int userId);
}
