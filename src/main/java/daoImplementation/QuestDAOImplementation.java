package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.QuestDAO;
import models.Quest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class QuestDAOImplementation{

    public void addQuest(Quest quest) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement preparedStatement = null;
        String sqlQuery = "INSERT INTO quests (name, description, reward) VALUES(?, ?, ?)";

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(sqlQuery);

            preparedStatement.setString(1, quest.getName());
            preparedStatement.setString(2, quest.getDescription());
            preparedStatement.setInt(3, quest.getReward());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteQuest(Quest quest) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement preparedStatement = null;
        String orderForSql = "UPDATE quests SET is_active = ? WHERE id = ?";

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, quest.getId());
            int row = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editQuest(Quest quest) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
        String orderForSql = ("UPDATE quests SET name = ?, description = ?, reward = ?, is_active = ? WHERE id = ?");

        try {
            ps.setString(1, quest.getName());
            ps.setString(2, quest.getDescription());
            ps.setInt(3, quest.getReward());
            ps.setBoolean(4, quest.isActive());
            ps.setInt(5, quest.getId());

            int row = ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void markQuest(int id) {
        //waiting for changes in DB
    }

    public List<Quest> getQuests(){
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
        String orderForSql = ("select u.id, ud.first_name, ud.last_name, q.name, q.description, q.reward from students as s\n" +
                "join users u\n" +
                "on s.user_id = u.id\n" +
                "join user_details ud\n" +
                "on u.user_details_id = ud.id\n" +
                "join user_quests uq\n" +
                "on s.id = uq.student_id\n" +
                "join quests q\n" +
                "on uq.quest_id = q.id\n");
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