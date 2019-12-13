import SQL.PostgreSQLJDBC;
import controllers.StudentController;
import daoImplementation.AddUserDAOImplementation;
import daoImplementation.ExtractDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import models.users.Student;
import daoImplementation.ItemDAOImplementation;
import daoImplementation.QuestDAOImplementation;
import models.components.Quest;

public class Main {
    public static void main(String[] args) {
        System.out.println("Dupddddka");
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.connect();

        StudentController studentController = new StudentController();
        studentController.run();


        QuestDAOImplementation qdi = new QuestDAOImplementation();
        qdi.getQuests();
    }
}
