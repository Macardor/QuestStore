import SQL.PostgreSQLJDBC;
import controllers.ItemController;
import controllers.MentorController;
import controllers.QuestController;
import daoImplementation.ItemDAOImplementation;
import models.Item;
import models.Mentor;
import models.Quest;

public class Main {
    public static void main(String[] args) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.connect();
        MentorController mentorController = new MentorController();
        mentorController.mentorMenu();
    }
}
