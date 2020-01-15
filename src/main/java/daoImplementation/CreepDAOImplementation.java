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
                boolean isActive = resultSet.getBoolean("is_active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
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

    //@Override
    public void editMentor2(int id) {
        showAllMentors();
        String orderToSqlDetails = ("UPDATE user_details SET login = ?, password = ?, student_type_id = ?, first_name = ?, last_name = ? WHERE id = ?");
        String orderToSqlUsers = ("UPDATE user SET is_active = ?");

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlDetails);
//            System.out.println("Insert new student login: ");
//            String login = scanner.next();
//            System.out.println("Insert new student password: ");
//            String password = scanner.next();
//            System.out.println("Insert new student name: ");
//            String firstName = scanner.next();
//            System.out.println("Insert new student surrname: ");
//            String lastName = scanner.next();
//            // tu brakuje sout'a moze dlatego nie działało
            System.out.println("Insert new mentor's login: ");
            String login = scanner.next();
            System.out.println("Insert new mentor's password: ");
            String password = scanner.next();
            System.out.println("Inesrt 1 to set as student, 2 to set as mentor: ");
            int studentTypeId = scanner.nextInt();
            boolean isActive2 = true;
            System.out.println("Insert new mentor's name: ");
            String firstName = scanner.next();
            System.out.println("Insert new mentor's last name: ");
            String lastName = scanner.next();


           // Mentor mentor = new Mentor(login, password, studentTypeId, isActive, firstName, lastName);


            preparedStatement.setString(1,login);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3,studentTypeId);
            preparedStatement.setBoolean(4, isActive2);
            preparedStatement.setString(5, firstName);
            preparedStatement.setString(6, lastName);

            int row = preparedStatement.executeUpdate();
            preparedStatement.executeUpdate();

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
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlUsers);
            System.out.println("Is active: ");
            boolean isActive = true;
            preparedStatement.setBoolean(1, isActive);
            int row = preparedStatement.executeUpdate();
            preparedStatement.executeUpdate();
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

}
