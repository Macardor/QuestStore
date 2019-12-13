package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.QuestDAO;
import models.components.Quest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class QuestDAOImplementation implements QuestDAO {

    @Override
    public void addQuest(String name, String description, int reward, boolean isActive) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement preparedStatement = null;
        String sqlQuery = "INSERT INTO quests (name, description, reward, is_active) VALUES(?, ?, ?, ?)";

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(sqlQuery);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, reward);
            preparedStatement.setBoolean(4, isActive);

            preparedStatement.executeUpdate();
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


            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, reward);
            ps.setBoolean(4, true);

            int row = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void markQuest(int id) {
        //waiting for changes in DB
    }

    public List<Quest> getQuests(){
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
        String orderForSql = ("select u.id, ud.first_name, ud.last_name, q.name, q.description, q.reward from students as s\n" +
                "\n" +
                "join users u\n" +
                "on s.user_id = u.id\n" +
                "\n" +
                "join user_details ud\n" +
                "on u.user_details_id = ud.id\n" +
                "\n" +
                "join user_quests uq\n" +
                "on s.id = uq.student_id\n" +
                "\n" +
                "join quests q\n" +
                "on uq.quest_id = q.id\n" +
                "\n");
        try{
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            //ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int user = resultSet.getInt("u.id");
                String firstName = resultSet.getString("ud.first_name");
                String lastName = resultSet.getString("ud.last_name");
                String questName = resultSet.getString("q.name");
                String description = resultSet.getString("q.description");
                int reward = resultSet.getInt("q.reward");
                System.out.println(user + " | " + firstName + " | " + lastName + " | " + questName + " | " + description);
            }
            ps.close();
        }catch (SQLException e){
            System.out.println(e);
        }



        return null;
    }
}
