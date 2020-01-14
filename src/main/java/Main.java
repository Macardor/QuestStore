import SQL.PostgreSQLJDBC;
import daoImplementation.ItemDAOImplementation;
import models.Item;

public class Main {
    public static void main(String[] args) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.connect();
    }
}
