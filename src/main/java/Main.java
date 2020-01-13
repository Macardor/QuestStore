import SQL.PostgreSQLJDBC;
import controllers.LoginController;
import controllers.StudentController;
import daoImplementation.QuestDAOImplementation;
import daoImplementation.StudentDAOImplementation;

public class Main {
    public static void main(String[] args) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.connect();
    }
}
