import SQL.PostgreSQLJDBC;
import controllers.CreepController;
import controllers.LoginController;
import controllers.StudentController;
import daoImplementation.QuestDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import daoImplementation.ItemDAOImplementation;
import models.Item;

public class Main {
    public static void main(String[] args) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.connect();
//
//        LoginController loginController = new LoginController();
//        loginController.run();
//        StudentController studentController = new StudentController();
//        studentController.run();


//        QuestDAOImplementation qdi = new QuestDAOImplementation();
//        qdi.getQuests();

        StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
        studentDAOImplementation.showItems();
        CreepController creepController = new CreepController();
        creepController.run();
    }
}
