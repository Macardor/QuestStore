package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.StudentDAO;
import models.Coincubator;
import models.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDAOImplementation implements StudentDAO {

    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Scanner scanner = new Scanner(System.in);

    @Override
    public List<Item> showItems() {
        String orderToSql = "SELECT * FROM items";
        List<Item> itemList = new ArrayList<>();
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                boolean isActive = resultSet.getBoolean("is_active");
                Item item = new Item(id, name, price, description, isActive);
                itemList.add(item);
                System.out.println(id+ "| " + name+ "| " + price+ "| " + description+ "| " + isActive);

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

        }return itemList;
    }


    @Override
    public List<Coincubator> showCoincubatos() {
        String orderToSql = "SELECT * FROM coincubators";
        List<Coincubator> coincubatorsList = new ArrayList<>();
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int currentDonation = resultSet.getInt("current_donation");
                int targetDonation = resultSet.getInt("target_donation");
                boolean isActive = resultSet.getBoolean("is_active");
                Coincubator coincubator = new Coincubator(id, name, description, currentDonation, targetDonation, isActive);
                coincubatorsList.add(coincubator);
                System.out.println(id+ "| " + name+ "| " + description+ "| " + currentDonation + "| " + targetDonation+ "| " + isActive);
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

        }return coincubatorsList;
    }

    @Override
    public void showUserItems() {

    }

    @Override
    public void showUserCoins() {

    }

    @Override
    public void showUserQuests() {

    }
}
//
//    public void addStudent(String login, String password, String firstName, String lastName) {
//        String orderToSqlUsers = "INSERT INTO user_details (login, password, first_name, last_name) VALUES (?,?,?,?)";
//        String orderToSqlDetails = "INSERT INTO users (user_type_id, is_active, user_details_id) VALUES (?,?,?)";
//
//        try {
//            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlUsers);
//            preparedStatement.setString(1, login);
//            preparedStatement.setString(2, password);
//            preparedStatement.setString(3, firstName);
//            preparedStatement.setString(4, lastName);
//            int row = preparedStatement.executeUpdate();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            try {
//                preparedStatement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        try {
//            int userTypeId = 1;
//            boolean setIsActive = true;
//            int userDetailId = 1;
//            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlDetails);
//            preparedStatement.setInt(1, userTypeId);
//            preparedStatement.setBoolean(2,setIsActive);
//            preparedStatement.setInt(3, userDetailId);
//            int row = preparedStatement.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            try {
//                preparedStatement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    @Override
//    public List<User> extractStudent() {
//        String orderToSql = "SELECT * FROM users " +
//                "join user_details " +
//                "on users.user_details_id = user_details.id WHERE users.user_type_id = ?";
//        List<User> studentList = new ArrayList<>();
//        try {
//            int studentTypeId = 1;
//            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
//            preparedStatement.setInt(1, studentTypeId);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                int id = resultSet.getInt("id");
//                String login = resultSet.getString("login");
//                String password = resultSet.getString("password");
//                int userTypeId = resultSet.getInt("user_type_id");
//                String firstName = resultSet.getString("first_name");
//                String lastName = resultSet.getString("last_name");
//                User user = new Student(id, login, password, userTypeId, firstName, lastName);
//                studentList.add(user);
//                System.out.println(id+ "| " + login+ "| " + password+ "| " + userTypeId+ "| " + firstName+ "| " + lastName);
//
//            }
//            preparedStatement.executeQuery();
//        }catch (SQLException e) {
//            System.out.println(e);
//        } finally {
//            try {
//                preparedStatement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//
//            }
//
//        }return studentList;
//    }
//
//    @Override
//    public void editStudent(int studentId) {
//        String orderToSqlDetails = ("UPDATE user_details SET login = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?");
//        String orderToSqlUsers = ("UPDATE user SET is_active = ?");
//
//        try {
//            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlDetails);
//            System.out.println("Insert new student login: ");
//            String login = scanner.next();
//            System.out.println("Insert new student password: ");
//            String password = scanner.next();
//            System.out.println("Insert new student name: ");
//            String firstName = scanner.next();
//            System.out.println("Insert new student surrname: ");
//            String lastName = scanner.next();
//            // tu brakuje sout'a moze dlatego nie działało
//            int studentTypeId = scanner.nextInt();
//
//            preparedStatement.setString(1, login);
//            preparedStatement.setString(2, password);
//            preparedStatement.setString(3, firstName);
//            preparedStatement.setString(4, lastName);
//
//            int row = preparedStatement.executeUpdate();
//
//        }catch (Exception e){
//            System.out.println(e);
//        }finally {
//            try {
//                preparedStatement.close();
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//
//        try {
//            System.out.println("Set user category: press 1 to add as student" +
//                    "press 2 to add as mentor" +
//                    "press 3 to add as creep");
//            int userTypeId = scanner.nextInt();
//            System.out.println("set if student is active or unactive");
//            boolean setIsActive = scanner.nextBoolean();
//            int userDetailId = 1;
//            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlUsers);
//            preparedStatement.setInt(1, userTypeId);
//            preparedStatement.setBoolean(2,setIsActive);
//            preparedStatement.setInt(3, userDetailId);
//            int row = preparedStatement.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            try {
//                preparedStatement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void removeStudent(int userId) {
//        String orderToSql = "DELETE FROM user_details WHERE id = ?";
//        String orderToSqlUser = "DELETE FROM users WHERE id = ?";
//        try {
//            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
//            preparedStatement.setInt(1, userId);
//            int row = preparedStatement.executeUpdate();
//        }catch (Exception e){
//            System.out.println(e);
//        }finally {
//            try {
//                preparedStatement.close();
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//        try {
//            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSqlUser);
//            preparedStatement.setInt(1,userId);
//            int row = preparedStatement.executeUpdate();
//        }catch (Exception e){
//            System.out.println(e);
//        }finally {
//            try {
//                preparedStatement.close();
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//    }
//}
