import SQL.PostgreSQLJDBC;
import daoImplementation.ItemDAOImplementation;
import daoImplementation.QuestDAOImplementation;
import models.components.Quest;

public class Main {
    public static void main(String[] args) {
        QuestDAOImplementation qdi = new QuestDAOImplementation();
        qdi.getQuests();
    }
}
