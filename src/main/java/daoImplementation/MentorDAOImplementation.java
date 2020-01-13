package daoImplementation;

import SQL.PostgreSQLJDBC;
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
    PreparedStatement ps = null;
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
            ps = postgreSQLJDBC.connect().prepareStatement(insertIntoTwoTables);

            ps.setString(1, student.getLogin());
            ps.setString(2, student.getPassword());
            ps.setString(3, student.getFirstname());
            ps.setString(4, student.getLastname());

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void editStudent(Student student) {
        //TODO Bartek to kupa
    }

    public void deleteStudent(int id) {
        String orderToSql = "UPDATE users SET is_active = ? WHERE id = ? ";
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);

            ps.setBoolean(1, false);
            ps.setInt(2, id);

            System.out.println(ps.toString()); //test method

            ps.executeUpdate();
            ps.close();
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
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            ps.setInt(1, 1);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId = resultSet.getInt("user_type_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                User student = new Student(id, login, password, userTypeId, firstName, lastName);
                studentList.add(student);

                System.out.println(id + "| " + login + "| " + password + "| " + userTypeId + "| " + firstName + "| " + lastName); //test method

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return studentList;
    }
}
