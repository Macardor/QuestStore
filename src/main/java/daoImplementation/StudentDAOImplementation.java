package daoImplementation;

import SQL.PostgreSQLJDBC;
import models.Coincubator;
import models.Student;
import models.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StudentDAOImplementation {

    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;
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

    public void deleteStudent(int id) {
        String orderToSql = "UPDATE users SET is_active = ? WHERE id = ? and user_type_id = 1 ";
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

    public List<Student> getStudentsList() {
        String orderToSql = "SELECT * FROM users " +
                "join user_details " +
                "on users.user_details_id = user_details.id WHERE users.user_type_id = ? ORDER BY user_details.id ";
        List<Student> studentList = new ArrayList<>();
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, 1);

            resultSet = preparedStatement.executeQuery();

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
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return studentList;
    }

    public List<Student> getActiveStudentsList() {
        String orderToSql = "SELECT * FROM users " +
                "join user_details " +
                "on users.user_details_id = user_details.id WHERE users.user_type_id = ? and users.is_active = ? ORDER BY user_details.id ";
        List<Student> studentList = new ArrayList<>();
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setBoolean(2, true);

            resultSet = preparedStatement.executeQuery();

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
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return studentList;
    }

    public Student getStudentByUserId(int userId) {
        Student student = null;
        String orderToSql = "SELECT * FROM users " +
                "join user_details " +
                "on users.user_details_id = user_details.id WHERE users.user_type_id = ? and users.id = ? ";
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, userId);
            resultSet = preparedStatement.executeQuery();
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
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }


    public List<Coincubator> showCoincubatos() {
        String orderToSql = "SELECT * FROM coincubators";
        List<Coincubator> coincubatorsList = new ArrayList<>();
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            resultSet = preparedStatement.executeQuery();
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
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                preparedStatement.close();
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
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            preparedStatement.setString(1, student.getLogin());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getFirstname());
            preparedStatement.setString(4, student.getLastname());
            preparedStatement.setInt(5, userDetailsId);


            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getUserDetailsId(User student) {
        String orderToSql = "SELECT * FROM users WHERE id = ?";
        int userDetailId = 0;
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, student.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userDetailId = resultSet.getInt("user_details_id");
            }
            preparedStatement.executeQuery();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userDetailId;
    }

    public int getStudentId(User student) {
        String orderToSql = "SELECT * FROM students WHERE user_id = ?";
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, student.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userDetailId = resultSet.getInt("id");
                System.out.println(userDetailId);
                return userDetailId;
            }
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                preparedStatement.close();
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

            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId = resultSet.getInt("user_type_id");
                boolean isActive = resultSet.getBoolean("is_active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                student = new Student(id, login, password, userTypeId, isActive, firstName, lastName);

            }
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
        return student;
    }

    public int showUserCoins(int id) {
        String orderToSql = "SELECT * FROM students AS s \n" +
        "join users as u \n" +
        "on s.user_id = u.id \n" +
        "WHERE u.id = ?;";
        int coins = 0;
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                coins = resultSet.getInt("coins");

                System.out.println("Your current coins: " + coins);
            }
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }return coins;
    }

    public void buyItem(int itemId, int studentId){
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        String orderToSql = "INSERT INTO user_items (item_id, is_available, bought_date, used_date, student_id) VALUES (?,?,?,?,?)";
        int itemPrice = getItemPrice(itemId);
        int studentCoins = showUserCoins(studentId);

        if (studentCoins >= itemPrice) {
            try {
                editStudentCoins(studentCoins,itemPrice,studentId);
                Date date = getCurrentDate();
                preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
                preparedStatement.setInt(1, itemId);
                preparedStatement.setBoolean(2, true);
                preparedStatement.setDate(3, date);
                preparedStatement.setDate(4, null);
                preparedStatement.setInt(5, studentId);
                resultSet = preparedStatement.executeQuery();
                preparedStatement.close();


            } catch (SQLException e) {
                System.out.println(e);
            }
        }else {
            System.out.println("you have not enough coins!");
        }
    }


    private int getItemPrice(int itemId) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
        String getItemPrice = "SELECT price FROM items WHERE id = ?";
        int price = 0;
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(getItemPrice);
            ps.setInt(1, itemId);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                price= resultSet.getInt("price");
                return price;
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

    public void useCard(int itemId, int studentId){
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
        String orderToSql = "UPDATE user_items SET is_available = ?, used_date = ? WHERE item_id = ? AND student_id = ?;";
        Date date = getCurrentDate();

        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            ps.setBoolean(1, false);
            ps.setDate(2, date);
            ps.setInt(3, itemId);
            ps.setInt(4, studentId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void editStudentCoins(int studentCoins, int itemPrice, int studentId) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();

        String updateCoins = "UPDATE students SET coins = ? WHERE user_id = ?";
        try{
            int reduceCoins = (studentCoins - itemPrice);
            System.out.println( "now student price -> " + reduceCoins);
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(updateCoins);
            preparedStatement.setInt(1, reduceCoins);
            preparedStatement.setInt(2, studentId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void takeCoinsFromStudent(int id, int coinAmount){
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        String orderToSql ="UPDATE students SET coins = ? WHERE id = ?";
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
            int newCoins = studentDAOImplementation.showUserCoins(id) - coinAmount;
            System.out.println("Your coins after transaction: " + newCoins);
            preparedStatement.setInt(1, newCoins);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private Date getCurrentDate() {
        return new Date(Calendar.getInstance().getTime().getTime());
    }


    public void setStudentCoins(int coinsToSet, int studentId) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        String orderToSql ="UPDATE students SET coins = ? WHERE id = ?";
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);

            preparedStatement.setInt(1, coinsToSet);
            preparedStatement.setInt(2, studentId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

