package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.StudentDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDAOImplementation {

    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;

    public void addUser(String login, String password, String firstName, String lastName) {
        String orderToSqlUsers = "INSERT INTO user_details (login, password, first_name, last_name) VALUES (?,?,?,?)";
        String orderToSqlDetails = "INSERT INTO users (user_type_id, is_active, user_details_id) VALUES (?,?,?)";

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlUsers);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            int row = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            int userTypeId = 1;
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlDetails);
            preparedStatement.setInt(1, userTypeId);
            preparedStatement.setBoolean(2,true);
            preparedStatement.setInt(3, 1);
            int row = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
