package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.ExtractDAO;
import models.users.Creep;
import models.users.Mentor;
import models.users.Student;
import models.users.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExtractDAOImplementation implements ExtractDAO {

    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    @Override
    public List<User> extract(int userType) {
        String orderToSql = "SELECT * FROM users" +
                "join user_details" +
                "on users.user_details_id = user_details.id WHERE users.user_type_id = ?";
        List<User> userList = new ArrayList<>();
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, userType);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId = resultSet.getInt("user_type");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                if (userType == 1) {
                    User user = new Student(id, login, password, userTypeId, firstName, lastName);
                    userList.add(user);
                    System.out.println(id+ "| " + login+ "| " + password+ "| " + userTypeId+ "| " + firstName+ "| " + lastName);
                } else if (userType == 2) {
                    User user = new Mentor(id, login, password, userTypeId, firstName, lastName);
                    userList.add(user);
                    System.out.println(id+ "| " + login+ "| " + password+ "| " + userTypeId+ "| " + firstName+ "| " + lastName);
                } else if (userType == 3) {
                    User user = new Creep(id, login, password, userTypeId, firstName, lastName);
                    userList.add(user);
                    System.out.println(id+ "| " + login+ "| " + password+ "| " + userTypeId+ "| " + firstName+ "| " + lastName);
                }
            }
        }catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }return userList;
    }
}
