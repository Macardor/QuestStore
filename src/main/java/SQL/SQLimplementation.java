package SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLimplementation {

    PostgreSQLJDBC connect;
    Connection connection;
    Statement statement;

    public SQLimplementation(){
        this.connect = new PostgreSQLJDBC();
    }

    public ResultSet selectQuery(String sql){
        ResultSet resultSet = null;
        try {
            connection = connect.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;

    }

    public void closeQuery(){
        try {
            this.statement.close();
            this.connection.close();
            this.connect.disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}