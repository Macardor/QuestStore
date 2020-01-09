import SQL.PostgreSQLJDBC;
import controllers.StudentController;
import daoImplementation.QuestDAOImplementation;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test");
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.connect();

        StudentController studentController = new StudentController();
        studentController.run();


//        QuestDAOImplementation qdi = new QuestDAOImplementation();
//        qdi.getQuests();
    }
}
