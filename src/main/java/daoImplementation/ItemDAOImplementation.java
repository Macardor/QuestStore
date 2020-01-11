package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.ItemDAO;
import models.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ItemDAOImplementation{
    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void addItem(Item item) {
        String sqlQuery = "INSERT INTO items (name, price, description, is_active) VALUES(?, ?, ?, ?) ";
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(sqlQuery);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getPrice());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setBoolean(4, item.isActive());
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int id) {
        String orderForSql = "UPDATE items SET is_active = ? WHERE id = ?";
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);
            int row = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editItem(Item item) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
        String orderForSql = ("UPDATE item SET name = ?, price = ?, description = ?, is_active = ? WHERE id = ?");

        try {
            ps.setString(1, item.getName());
            ps.setInt(2, item.getPrice());
            ps.setString(3, item.getDescription());
            ps.setBoolean(4, item.isActive());

            int row = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public List<Item> getItemsList() {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement preparedStatement = null;
        String orderForSql = ("SELECT * FROM items");
        List<Item> itemList = new ArrayList<>();
        try{
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                boolean isActive = resultSet.getBoolean("is_active");
                Item item = new Item(id, name, price, description, isActive);
                itemList.add(item);
                //test method
                System.out.println(id + " | " + name + " | " + price + " | " + description + " | " + isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public List<Item> getUserItemsList(int userId) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement preparedStatement = null;
        String orderForSql = ("SELECT * FROM items join user_items ui on items.id = ui.item_id where student_id = ?;");//TODO
        List<Item> itemList = new ArrayList<>();
        try{
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                boolean isActive = resultSet.getBoolean("is_active");
                //TODO what whit joined id, not ready for tests
                int idInUI = resultSet.getInt("id");
                boolean isAvailable = resultSet.getBoolean("is_available");
                Date boughtDate = resultSet.getDate("bought_date");
                Date usedDate = resultSet.getDate("used_date");
                int studentId = resultSet.getInt("student_id");


                Item item = new Item(id, name, price, description, isActive);
                itemList.add(item);
                //test method
                System.out.println(id + " | " + name + " | " + price + " | " + description + " | " + isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}