package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBC {
    Connection connection = null;

    public Connection connect() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CyberStoreDB", "postgres", "admin");
            System.out.println("Opened database successfully");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return connection;
    }


    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
