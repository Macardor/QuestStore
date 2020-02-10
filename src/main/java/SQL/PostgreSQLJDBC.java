package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBC {
    private Connection connection = null;

    public Connection connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql://ec2-46-137-177-160.eu-west-1.compute.amazonaws.com:5432/d449bhf64b4qnv?sslmode=require", "ngmvkoyjbkwpnz", "3791f96278b81172e905a99ed7f9d29a1266dfb549ee7768ee3592cda1b26c07");
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
            if(connection.isClosed()){
                System.out.println("DB Closed");
            }else {
                System.out.println("Cant't Close DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
