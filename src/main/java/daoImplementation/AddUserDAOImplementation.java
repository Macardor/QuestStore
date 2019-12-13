package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.AddUserDAO;
import models.users.Creep;
import models.users.Mentor;
import models.users.Student;
import models.users.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddUserDAOImplementation implements AddUserDAO {

    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void add(int userType) {
        String orderToSqlUsers = "INSERT INTO user_details (login, password, first_name, last_name) VALUES (?,?,?,?)";
        String orderToSqlDetails = "INSERT INTO users (user_type_id, is_active, user_details_id) VALUES (?,?,?)";

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert login: ");
            String login = scanner.next();
            System.out.println("insert password: ");
            String password = scanner.next();
            System.out.println("insert first name: ");
            String firstName = scanner.next();
            System.out.println("insert last name: ");
            String lastName = scanner.next();

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
            Scanner scanner =new Scanner(System.in);
            Boolean b = scanner.hasNext();
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlDetails);
            int i = scanner.nextInt();
            preparedStatement.setInt(1, userType);
            preparedStatement.setBoolean(2,b);
            preparedStatement.setInt(3,i);
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
