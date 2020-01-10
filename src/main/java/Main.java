import SQL.PostgreSQLJDBC;
import controllers.StudentController;
import daoImplementation.QuestDAOImplementation;
import daoImplementation.StudentDAOImplementation;

public class Main {
    public static void main(String[] args) {
        System.out.println("Dupddddka");
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.connect();

        StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
        studentDAOImplementation.extractStudent();

//        StudentController studentController = new StudentController();
//        studentController.run();
//
//        QuestDAOImplementation qdi = new QuestDAOImplementation();
    }
}
