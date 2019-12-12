package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.QuestDAO;
import jdk.jshell.spi.ExecutionControl;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class QuestDAOImplementation implements QuestDAO {

    @Override
    public void addQuest(String name, String description, int reward, boolean isActive) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement preparedStatement = null;
        String sqlQuery = "INSERT INTO quests (name, description, reward, is_active) VALUES(?, ?, ?, ?) ";

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, reward);
            preparedStatement.setBoolean(4, isActive);
            int resultSet = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteQuest(int id) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement preparedStatement = null;
        String orderForSql = "UPDATE quests SET is_active = ? WHERE id = ?";

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
    public void editQuest(int id) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
        String orderForSql = ("UPDATE quests SET name = ?, description = ?, reward = ?, is_active = ? WHERE id = ?");

        try {
            Scanner scanner = new Scanner(System.in);
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);

            System.out.println("enter name: "); //
            String name = scanner.nextLine();

            System.out.println("enter description: ");
            String description = scanner.nextLine();

            System.out.println("enter reward: ");
            int reward = scanner.nextInt();

            System.out.println("is quest active: ");
            boolean isActive = scanner.nextBoolean();


            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, reward);
            ps.setBoolean(4, isActive);

            int row = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void markQuest(int id) {
        //waiting for changes in DB
    }
}
