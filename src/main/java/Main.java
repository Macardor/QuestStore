import SQL.PostgreSQLJDBC;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test");
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.connect();

    }
}
