import SQL.PostgreSQLJDBC;
import controllers.StudentController;
import daoImplementation.ExtractDAOImplementation;
import daoImplementation.StudentDAOImplementation;
import models.users.Student;

public class Main {
    public static void main(String[] args) {
        System.out.println("Dupddddka");
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.connect();

        StudentController studentController = new StudentController();
        studentController.run();


    }
}
