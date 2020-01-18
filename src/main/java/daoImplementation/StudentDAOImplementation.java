package daoImplementation;

import SQL.PostgreSQLJDBC;
import models.Coincubator;
import models.Student;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDAOImplementation {

    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement ps = null;
    ResultSet resultSet = null;

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
        String orderToSql = "UPDATE users SET is_active = ? WHERE id = ? and user_type_id = 1 ";
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);

            ps.setBoolean(1, false);
            ps.setInt(2, id);

            System.out.println(ps.toString()); //test method

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Student> getStudentsList() {
        String orderToSql = "SELECT * FROM users " +
                "join user_details " +
                "on users.user_details_id = user_details.id WHERE users.user_type_id = ?";
        List<Student> studentList = new ArrayList<>();
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            ps.setInt(1, 1);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId = resultSet.getInt("user_type_id");
                boolean isActive = resultSet.getBoolean("is_active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                Student student = new Student(id, login, password, userTypeId, isActive, firstName, lastName);
                studentList.add(student);

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return studentList;
    }

    public List<Student> getActiveStudentsList() {
        String orderToSql = "SELECT * FROM users " +
                "join user_details " +
                "on users.user_details_id = user_details.id WHERE users.user_type_id = ? and users.is_active = ?";
        List<Student> studentList = new ArrayList<>();
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            ps.setInt(1, 1);
            ps.setBoolean(2, true);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId = resultSet.getInt("user_type_id");
                boolean isActive = resultSet.getBoolean("is_active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                Student student = new Student(id, login, password, userTypeId, isActive, firstName, lastName);
                studentList.add(student);

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return studentList;
    }

    public Student getStudentByUserId(int userId) {
        Student student = null;
        String orderToSql = "SELECT * FROM users " +
                "join user_details " +
                "on users.user_details_id = user_details.id WHERE users.user_type_id = ? and users.id = ?";
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            ps.setInt(1, Student.userType);
            ps.setInt(2, userId);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId = resultSet.getInt("user_type_id");
                boolean isActive = resultSet.getBoolean("is_active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                student = new Student(id, login, password, userTypeId, isActive, firstName, lastName);

            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }


    public List<Coincubator> showCoincubatos() {
        String orderToSql = "SELECT * FROM coincubators";
        List<Coincubator> coincubatorsList = new ArrayList<>();
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int currentDonation = resultSet.getInt("current_donation");
                int targetDonation = resultSet.getInt("target_donation");
                boolean isActive = resultSet.getBoolean("is_active");
                Coincubator coincubator = new Coincubator(id, name, description, currentDonation, targetDonation, isActive);
                coincubatorsList.add(coincubator);
                System.out.println(id + "| " + name + "| " + description + "| " + currentDonation + "| " + targetDonation + "| " + isActive);
            }
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
        return coincubatorsList;
    }

    public void editStudent(Student student, int userDetailsId) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        String orderForSql = ("UPDATE user_details SET login = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?");

        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            ps.setString(1, student.getLogin());
            ps.setString(2, student.getPassword());
            ps.setString(3, student.getFirstname());
            ps.setString(4, student.getLastname());
            ps.setInt(5, userDetailsId);


            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getUserDetailsId(Student student) {
        String orderToSql = "SELECT * FROM users WHERE id = ?";
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            ps.setInt(1, student.getId());
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int userDetailId = resultSet.getInt("user_details_id");
                return userDetailId;
            }
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
        return 0;
    }

    public int getStudentId(Student student) {
        String orderToSql = "SELECT * FROM students WHERE user_id = ?";
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            ps.setInt(1, student.getId());
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int userDetailId = resultSet.getInt("id");
                System.out.println(userDetailId);
                return userDetailId;
            }
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
        return 0;
    }


    public Student isStudentWithIdInDB(int id) {
        Student student = null;

        String orderToSql = "SELECT * FROM users " +
                "join user_details " +
                "on users.user_details_id = user_details.id WHERE users.user_type_id = ?";

        try {

            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {

                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId = resultSet.getInt("user_type_id");
                boolean isActive = resultSet.getBoolean("is_active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                student = new Student(id, login, password, userTypeId, isActive, firstName, lastName);

            }
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
        return student;
    }

    public int showUserCoins(int id) {
        String orderToSql = "SELECT coins FROM students WHERE id = ?";
        int coins = 0;
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                coins = resultSet.getInt("coins");

                System.out.println("Here is your coins: " + coins);
            }
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }return coins;
    }

    public void buyItem(int itemId, int studentId){
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
        String orderToSql = "INSERT INTO user_items (item_id, is_available, bought_date, used_date, student_id) VALUES (?,?,?,?,?)";
        int itemPrice = getItemPrice(itemId);
        int studentCoins = showUserCoins(studentId);
        if (itemPrice > studentCoins) {
            try {
                ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
                ps.setInt(1, itemId);
                ps.setBoolean(2, true);
                ps.setDate(3, DateNow());
                ps.setDate(4, null);
                ps.setInt(5, studentId);
                resultSet = ps.executeQuery();
                ps.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }else {
            System.out.println("you have not enough coins!");
        }
    }

    public Date DateNow(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        return null;
    }

    public int getItemPrice(int itemId) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
        String getItemPrice = "SELECT price FROM items WHERE id = ?";
        int price = 0;
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(getItemPrice);
            ps.setInt(1, itemId);
            resultSet = ps.executeQuery();
            while(resultSet.next()){
                int itemPrice = resultSet.getInt("price");
                System.out.println(itemPrice);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return price;
    }
//
//    public void useCard(){
//        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
//        PreparedStatement ps = null;
//        String orderToSql = "INSERT INTO user_items (item_id, is_available, bought_date, used_date, student_id) VALUES (?,?,?,?,?)";
//        int itemPrice = getItemPrice(itemId);
//        int studentCoins = showUserCoins(studentId);
//        if (itemPrice > studentCoins) {
//            try {
//                ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
//                ps.setInt(1, itemId);
//                ps.setBoolean(2, true);
//                ps.setDate(3, DateNow());
//                ps.setDate(4, null);
//                ps.setInt(5, studentId);
//                resultSet = ps.executeQuery();
//                ps.close();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//        }else {
//            System.out.println("you have not enough coins!");
//        }
//    }

}

