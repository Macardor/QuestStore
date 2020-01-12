package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.StudentDAO;
import models.Coincubator;
import models.Item;
import models.Student;

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
    public void showUserCoins(Student student) {

    }

    // @Override
    public void showUserCoins(/*Student student*/ int id) {
        String orderToSql = "SELECT coins FROM students WHERE id = ?";
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int coins = resultSet.getInt("coins");
                System.out.println("Your coins amount is: " + coins);
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

        }
    }


    @Override
    public void showUserQuests() {

    }
}
//
//
