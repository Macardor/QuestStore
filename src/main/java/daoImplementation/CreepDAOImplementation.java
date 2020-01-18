package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.CreepDAO;
import models.Mentor;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreepDAOImplementation implements CreepDAO {

    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public List<User> showAllMentors() {
        String orderToSql = "SELECT * FROM user_details FULL OUTER JOIN users on user_details.id = users.user_details_id WHERE user_type_id = 2";
        List<User> mentorsList = new ArrayList<>();
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId2 = resultSet.getInt("user_type_id");
                boolean isActive = resultSet.getBoolean("is_active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                System.out.println(id+ "| " + login+ "| " + password+ "| " + userTypeId2+ "| " + firstName+ "| " + lastName);

            }
            preparedStatement.executeQuery();
            preparedStatement.close();
        }catch (SQLException e) {
            System.out.println(e);
        }return mentorsList;
    }

    @Override
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
    }

    @Override
    public void editMentor(Mentor mentor) {
        String orderForSql = ("UPDATE user_details SET login = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?");
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderForSql);

            preparedStatement.setString(1, mentor.getLogin());
            preparedStatement.setString(2, mentor.getPassword());
            preparedStatement.setString(3, mentor.getFirstname());
            preparedStatement.setString(4, mentor.getLastname());

            int row = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void setMentorToUnactive(int id) {
        String orderToSql = "UPDATE users SET is_active = ? WHERE id = ? and user_type_id = 2 ";
        try {

            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);

            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);

            System.out.println(preparedStatement.toString()); //test method

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Mentor getMentorById(int idToEdit) {
        String orderToSql = "SELECT * FROM user_details JOIN users on user_details.id = users.user_details_id WHERE user_type_id = 2 and users.id =?";
        Mentor mentor = null;
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, idToEdit);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId2 = resultSet.getInt("user_type_id");
                boolean isActive = resultSet.getBoolean("is_active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                System.out.println(id + "| " + login+ "| " + password+ "| " + userTypeId2+ "| " + firstName+ "| " + lastName);
                mentor = new Mentor(id, login, password, userTypeId2, isActive, firstName, lastName);
                System.out.println(mentor.toString());
            }
            preparedStatement.close();
        }catch (SQLException e) {
            System.out.println(e);
        }return mentor;
    }



}
