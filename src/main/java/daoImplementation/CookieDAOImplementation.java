package daoImplementation;

import SQL.PostgreSQLJDBC;
import models.Mentor;
import models.Student;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CookieDAOImplementation {

    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public User getUserByCookieSessionId(String cookieSessionId) {

        String orderToSql = "SELECT u.id, ud.login, ud.password, ud.first_name, ud.last_name, u.is_active, u.user_type_id FROM cookies as c JOIN users as u on u.id = c.user_id JOIN user_details as ud on u.user_details_id = ud.id WHERE c.sesion_id = ?";
        User user = null;
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setString(1, cookieSessionId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId = resultSet.getInt("user_type_id");
                boolean isActive = resultSet.getBoolean("is_active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                System.out.println(id+ "| " + login+ "| " + password+ "| " + userTypeId+ "| " + firstName+ "| " + lastName);

                user = new Student(id, login, password, userTypeId, isActive, firstName, lastName);

            }
            preparedStatement.executeQuery();
            preparedStatement.close();
        }catch (SQLException e) {
            System.out.println(e);
        }return user;
    }

    public void addMentor(Mentor mentor) {
        String insertIntoTwoTables = "WITH insertIntoUserDetails AS (INSERT INTO user_details (login, password, first_name, last_name) " +
                "VALUES (?, ?, ?, ?) RETURNING id), inserIntoUsers AS (INSERT INTO users (user_type_id, is_active, user_details_id) " +
                "VALUES (2, 'true', (SELECT id FROM insertIntoUserDetails)) RETURNING id) INSERT INTO mentors (user_id) VALUES ((SELECT id FROM inserIntoUsers))";
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(insertIntoTwoTables);

            preparedStatement.setString(1, mentor.getLogin());
            preparedStatement.setString(2, mentor.getPassword());
            preparedStatement.setString(3, mentor.getFirstname());
            preparedStatement.setString(4, mentor.getLastname());
            preparedStatement.executeQuery();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        //TODO
    }


}
