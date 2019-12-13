package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.ItemDAO;
import models.components.Item;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ItemDAOImplementation implements ItemDAO {

    @Override
    public void addItem(String name, int price, String description, boolean isActive) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement preparedStatement = null;
        String sqlQuery = "INSERT INTO items (name, price, description, is_active) VALUES(?, ?, ?, ?) ";

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, price);
            preparedStatement.setString(3, description);
            preparedStatement.setBoolean(4, isActive);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(int id) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement preparedStatement = null;
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

    @Override
    public void editItem(int id) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
        String orderForSql = ("UPDATE item SET name = ?, price = ?, description = ? is_active WHERE id = ?");

        try {
            Scanner scanner = new Scanner(System.in);
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);

            System.out.println("enter name: "); //
            String name = scanner.nextLine();

            System.out.println("enter price: ");
            int price = scanner.nextInt();

            System.out.println("enter description: ");
            String description = scanner.nextLine();

            System.out.println("is item active: ");
            boolean isActive = scanner.nextBoolean();


            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setString(3, description);
            ps.setBoolean(4, isActive);

            int row = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Item> getEquipment() {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
        String orderForSql = ("SELECT * FROM user_items");



        return null;
    }
}
