import SQL.PostgreSQLJDBC;
import controllers.QuestController;
import daoImplementation.ItemDAOImplementation;
import models.Item;

public class Main {
    public static void main(String[] args) {
        QuestController questController = new QuestController();
        questController.questMenu();
    }
}
