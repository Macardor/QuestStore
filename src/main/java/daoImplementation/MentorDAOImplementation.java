package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.CRUD;
import interfaces.MentorDAO;
import models.Student;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MentorDAOImplementation{
    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Scanner scanner = new Scanner(System.in);


    public void addStudent(Student student) {
        String insertIntoTwoTables = "WITH insertIntoUserDetails AS (\n" +
                "    INSERT INTO user_details (login, password, first_name, last_name) VALUES\n" +
                "    (?, ?, ?, ?)\n" +
                "    RETURNING id\n" +
                "),\n" +
                " inserIntoUsers AS (\n" +
                "    INSERT INTO users (user_type_id, is_active, user_details_id) VALUES\n" +
                "    (1, 'true', (SELECT id FROM insertIntoUserDetails))\n" +
                "    RETURNING id\n" +
                ")\n" +
                "INSERT INTO students (coins, user_id) VALUES\n" +
                "(0 , (SELECT id FROM inserIntoUsers));";

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(insertIntoTwoTables);
            preparedStatement.setString(1, student.getLogin());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getFirstname());
            preparedStatement.setString(4, student.getLastname());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void editStudent(Student student) {
        String orderToSqlDetails = ("UPDATE user_details SET login = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?");
        String orderToSqlUsers = ("UPDATE user SET is_active = ?");
        //TODO Bartek
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlDetails);
            System.out.println("Insert new student login: ");
            String login = scanner.next();
            System.out.println("Insert new student password: ");
            String password = scanner.next();
            System.out.println("Insert new student name: ");
            String firstName = scanner.next();
            System.out.println("Insert new student surname: ");
            String lastName = scanner.next();


            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);

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
            System.out.println("Set user category: press 1 to add as student" +
                    "press 2 to add as mentor" +
                    "press 3 to add as creep");
            int userTypeId = scanner.nextInt();
            System.out.println("set if student is active or unactive");
            boolean setIsActive = scanner.nextBoolean();
            int userDetailId = 1;
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlUsers);
            preparedStatement.setInt(1, userTypeId);
            preparedStatement.setBoolean(2,setIsActive);
            preparedStatement.setInt(3, userDetailId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteStudent(int id) {
        String orderToSql = "UPDATE users SET is_active = ? WHERE id = ? ";
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public List<User> getStudentsList() {
        String orderToSql = "SELECT * FROM users " +
                "join user_details " +
                "on users.user_details_id = user_details.id WHERE users.user_type_id = ?";
        List<User> studentList = new ArrayList<>();
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId = resultSet.getInt("user_type_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                User user = new Student(id, login, password, userTypeId, firstName, lastName);
                studentList.add(user);

                System.out.println(id + "| " + login + "| " + password + "| " + userTypeId + "| " + firstName + "| " + lastName);

            }
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return studentList;
    }
}
