import SQL.PostgreSQLJDBC;
import controllers.ItemController;
import controllers.LoginController;
import controllers.QuestController;
import daoImplementation.ItemDAOImplementation;
import models.Item;
import models.Quest;

public class Main {
    public static void main(String[] args) {
//        ItemController itemController = new ItemController();
//        itemController.itemMenu();
        LoginController loginController = new LoginController();
        loginController.run();
    }
}
