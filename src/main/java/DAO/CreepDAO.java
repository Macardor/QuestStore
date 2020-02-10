package DAO;

import SQL.PostgreSQLJDBC;
import models.Creep;
import models.Mentor;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreepDAO {

    private PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void editMentor(Mentor mentor, int id) {
        String orderForSql = ("UPDATE user_details SET login = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?");
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderForSql);

            preparedStatement.setString(1, mentor.getLogin());
            preparedStatement.setString(2, mentor.getPassword());
            preparedStatement.setString(3, mentor.getFirstname());
            preparedStatement.setString(4, mentor.getLastname());
            preparedStatement.setInt(5, id);


            int row = preparedStatement.executeUpdate();
            preparedStatement.close();
            postgreSQLJDBC.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        postgreSQLJDBC.disconnect();
    }

    public int getMentorDetails(User mentor) {
        String orderToSql = "SELECT * FROM users WHERE id = ?";
        int userDetailId = 0;
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, mentor.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userDetailId = resultSet.getInt("user_details_id");
//                return userDetailId;
            }
            preparedStatement.executeQuery();
            preparedStatement.close();
            postgreSQLJDBC.disconnect();
        } catch (SQLException e) {
            System.out.println(e);
        }
        postgreSQLJDBC.disconnect();
        return userDetailId;
    }

    public void setMentorToUnactive(int id) {
        String orderToSql = "UPDATE users SET is_active = ? WHERE id = ? and user_type_id = 2 ";
        try {

            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);

            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);

            System.out.println(preparedStatement.toString()); //test method

            preparedStatement.executeUpdate();
            preparedStatement.close();
            postgreSQLJDBC.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        postgreSQLJDBC.disconnect();
    }

    public Mentor getMentorById(int idToEdit) {
        String orderToSql = "SELECT * FROM users " +
                "join user_details " +
                "on users.user_details_id = user_details.id WHERE users.user_type_id = 2 and user_details_id = ?";
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
                //System.out.println(id + "| " + login+ "| " + password+ "| " + userTypeId2+ "| " + firstName+ "| " + lastName);
                mentor = new Mentor(id, login, password, userTypeId2, isActive, firstName, lastName);
                System.out.println(mentor.toString());
            }
            preparedStatement.close();
            postgreSQLJDBC.disconnect();
        }catch (SQLException e) {
            System.out.println(e);
        }
        postgreSQLJDBC.disconnect();
        return mentor;
    }

    public void editCreep(Creep creepToEdit, int creepDetailsId) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        String orderForSql = ("UPDATE user_details SET login = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?");

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            preparedStatement.setString(1, creepToEdit.getLogin());
            preparedStatement.setString(2, creepToEdit.getPassword());
            preparedStatement.setString(3, creepToEdit.getFirstname());
            preparedStatement.setString(4, creepToEdit.getLastname());
            preparedStatement.setInt(5, creepDetailsId);


            preparedStatement.executeUpdate();
            preparedStatement.close();
            postgreSQLJDBC.disconnect();
        } catch (SQLException e) {
            System.out.println(e);
        }
        postgreSQLJDBC.disconnect();
    }
}
