import SQL.PostgreSQLJDBC;
import controllers.CreepController;
import controllers.LoginController;
import controllers.StudentController;
import daoImplementation.CreepDAOImplementation;
import daoImplementation.QuestDAOImplementation;
import daoImplementation.StudentDAOImplementation;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Dupddddka");
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.connect();
//
//        LoginController loginController = new LoginController();
//        loginController.run();
//        StudentController studentController = new StudentController();
//        studentController.run();


//        QuestDAOImplementation qdi = new QuestDAOImplementation();
//        qdi.getQuests();

//        CreepController creepController = new CreepController();
//        creepController.run();

        StudentController studentController = new StudentController();
        studentController.run();

    }
}
