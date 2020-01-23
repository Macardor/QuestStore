package daoImplementation;

import SQL.PostgreSQLJDBC;
import models.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImplementation{
    private PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    private PreparedStatement ps = null;
    private ResultSet resultSet = null;

    public void addItem(Item item) {
        String sqlQuery = "INSERT INTO items (name, price, description, is_active) VALUES(?, ?, ?, ?) ";
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(sqlQuery);

            ps.setString(1, item.getName());
            ps.setInt(2, item.getPrice());
            ps.setString(3, item.getDescription());
            ps.setBoolean(4, item.isActive());

//            System.out.println(ps.toString()); //test method

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int id) {
        String orderForSql = "UPDATE items SET is_active = ? WHERE id = ?";
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            ps.setBoolean(1, false);
            ps.setInt(2, id);

//            System.out.println(ps.toString()); //test method

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editItem(Item item) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        String orderForSql = ("UPDATE items SET name = ?, price = ?, description = ?, is_active = ? WHERE id = ?");

        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            ps.setString(1, item.getName());
            ps.setInt(2, item.getPrice());
            ps.setString(3, item.getDescription());
            ps.setBoolean(4, item.isActive());
            ps.setInt(5, item.getId());

//            System.out.println(ps.toString()); //test method

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public List<Item> getItemsList() {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        String orderForSql = ("SELECT * FROM items");
        List<Item> itemList = new ArrayList<>();
        try{
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                boolean isActive = resultSet.getBoolean("is_active");

//                System.out.println(id + " | " + name + " | " + price + " | " + description + " | " + isActive);  //test method

                Item item = new Item(id, name, price, description, isActive);
                itemList.add(item);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public List<Item> getUserItemsList(int userId) {
        String orderForSql = ("SELECT i.id, i.name, i.price, i.description, i.is_active FROM items as i join user_items ui on i.id = ui.item_id join students s on ui.student_id = s.id join users u on s.user_id = u.id where u.id = ? and ui.is_available = true ;");//TODO
        List<Item> itemList = new ArrayList<>();
        try{
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            ps.setInt(1,userId);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                boolean isActive = resultSet.getBoolean("is_active");


                Item item = new Item(id, name, price, description, isActive);
                itemList.add(item);
                //test method
                System.out.println(id + " | " + name + " | " + price + " | " + description + " | " + isActive +"");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public Item getItemById(int id) {
        String orderToSql =  "SELECT * FROM items where id = ?";
        Item item = null;
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                boolean isActive = resultSet.getBoolean("is_active");
                item = new Item(id, name, price, description, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}
