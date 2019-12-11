import SQL.PostgreSQLJDBC;

public class Main {
    public static void main(String[] args) {
        System.out.println("Dupka");
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.connectWithDB();
    }
}
