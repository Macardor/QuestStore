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
    Scanner scanner = new Scanner(System.in);

    @Override
    public List<User> showAllMentors() {
        String orderToSql = "SELECT * FROM users " +
                "join user_details " +
                "on users.user_details_id = user_details.id WHERE users.user_type_id = ?";
        List<User> mentorsList = new ArrayList<>();
        try {
            int userTypeId = 2;
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, userTypeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId2 = resultSet.getInt("user_type_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                boolean isActive = true;
                User user = new Mentor(id, login, password, userTypeId, isActive, firstName, lastName);
                mentorsList.add(user);
                System.out.println(id+ "| " + login+ "| " + password+ "| " + userTypeId2+ "| " + firstName+ "| " + lastName);

            }
            preparedStatement.executeQuery();
        }catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }return mentorsList;
    }


    @Override
    public void addMentor(Mentor mentor) {
        String insertIntoTwoTables = "WITH insertIntoUserDetails AS (\n" +
                "    INSERT INTO user_details (login, password, first_name, last_name) VALUES\n" +
                "    (?, ?, ?, ?)\n" +
                "    RETURNING id\n" +
                "),\n" +
                " inserIntoUsers AS (\n" +
                "    INSERT INTO users (user_type_id, is_active, user_details_id) VALUES\n" +
                "    (2, 'true', (SELECT id FROM insertIntoUserDetails))\n" +
                "    RETURNING id\n" +
                ")\n" +
                "INSERT INTO mentors (user_id) VALUES\n" +
                "(SELECT id FROM inserIntoUsers);";

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(insertIntoTwoTables);
            preparedStatement.setString(1, mentor.getLogin());
            preparedStatement.setString(2, mentor.getPassword());
            preparedStatement.setString(3, mentor.getFirstname());
            preparedStatement.setString(4, mentor.getLastname());
            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void editMentor(Mentor mentor) {

    }

    @Override
    public void deleteMentor(int id) {
        String orderToSql = "DELETE FROM user_details WHERE id = ?";
        String orderToSqlUser = "DELETE FROM users WHERE id = ?";
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlUser);
            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1,id);
            int row = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
