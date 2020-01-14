import SQL.PostgreSQLJDBC;
import controllers.QuestController;
import daoImplementation.ItemDAOImplementation;
import models.Item;
import models.Quest;

public class Main {
    public static void main(String[] args) {
        QuestController questController = new QuestController();
        questController.questMenu();
    }
}
