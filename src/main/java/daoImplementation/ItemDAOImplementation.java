package daoImplementation;

import SQL.PostgreSQLJDBC;
import models.Item;
import models.ItemTransaction;

import java.sql.Date;
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
        String orderForSql = ("SELECT * FROM items order by id ");
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
    public List<Item> getActiveItemsList() {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        String orderForSql = ("SELECT * FROM items WHERE is_active = true");
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

    public List<ItemTransaction> getUserItemsList(int userId) {
        String orderForSql = ("SELECT i.id as itemid, ui.id as transactionid, i.name, i.price, i.description, i.is_active, ui.is_available FROM items as i join user_items ui on i.id = ui.item_id join students s on ui.student_id = s.id join users u on s.user_id = u.id where s.id = ?");//TODO
        List<ItemTransaction> itemList = new ArrayList<>();
        try{
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            ps.setInt(1,userId);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                int transactionId = resultSet.getInt("transactionid");
                int itemId = resultSet.getInt("itemid");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                boolean isActive = resultSet.getBoolean("is_active");
                boolean isUsed = resultSet.getBoolean("is_available");


                ItemTransaction itemTransaction = new ItemTransaction(transactionId, itemId, name, price, description, isActive, isUsed);
                itemList.add(itemTransaction);
                //test method
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

    public List<ItemTransaction> getUsertransactionList(int studentId) {
        String orderForSql = ("SELECT ui.id as transaction_id, i.id as item_id, name, description,bought_date,used_date,is_available FROM user_items as ui\n" +
                "join items as i \n" +
                "    on ui.item_id = i.id \n" +
                "where student_id = ? order by transaction_id;");
        List<ItemTransaction> itemTransactionListList = new ArrayList<>();
        try{
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            ps.setInt(1, studentId);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("transaction_id");
                int itemId = resultSet.getInt("item_id");
                String itemName = resultSet.getString("name");
                String itemDescription = resultSet.getString("description");
                Date boughtDate = resultSet.getDate("bought_date");
                Date useDate = resultSet.getDate("used_date");
                boolean isActive = resultSet.getBoolean("is_available");


                ItemTransaction itemTransaction = new ItemTransaction(id, itemId, itemName, itemDescription , boughtDate, useDate, isActive );
                itemTransactionListList.add(itemTransaction);
                //test method
                System.out.println(itemTransaction.toString());
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemTransactionListList;
    }

    public int getItemPriceById(int itemID) {
        String orderToSql =  "SELECT * FROM items where id = ?";
        int price = 0;
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            ps.setInt(1, itemID);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getInt("price");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    public void setTransactionItemActive(int itemId, Date usedDate) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        String orderForSql = ("UPDATE user_items SET is_available = ?, used_date = ? WHERE id = ?");

        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            ps.setBoolean(1, false);
            ps.setDate(2, usedDate);
            ps.setInt(3, itemId);

//            System.out.println(ps.toString()); //test method

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
