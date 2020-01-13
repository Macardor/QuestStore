package daoImplementation;

import SQL.PostgreSQLJDBC;
import interfaces.QuestDAO;
import models.Quest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void markQuest(int id) {
        //waiting for changes in DB
    }

    public List<Quest> getAllQuests(){
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
//        String orderForSql = ("select u.id, ud.first_name, ud.last_name, q.name, q.description, q.reward from students as s\n" +
//                "join users u\n" +
//                "on s.user_id = u.id\n" +
//                "join user_details ud\n" +
//                "on u.user_details_id = ud.id\n" +
//                "join user_quests uq\n" +
//                "on s.id = uq.student_id\n" +
//                "join quests q\n" +
//                "on uq.quest_id = q.id\n where u.id = ?");
        String orderForSql = "SELECT * FROM quests";
        List<Quest> quests = new ArrayList<>();
        try{
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            //ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int reward = resultSet.getInt("reward");
                boolean isActive = resultSet.getBoolean("is_active");
                Quest quest = new Quest(id, name, description, reward, isActive);
                quests.add(quest);
                System.out.println(id + " | " + name + " | " + description + " | " + reward + " | " + isActive);
            }
            ps.close();
        }catch (SQLException e){
            System.out.println(e);
        }
        return quests;
    }

    public List<Quest> getAllStudentQuests(int studentId){
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
                "on uq.quest_id = q.id\n where u.id = ?");
        List<Quest> quests = new ArrayList<>();
        try{
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            //ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int reward = resultSet.getInt("reward");
                boolean isActive = resultSet.getBoolean("is_active");
                Quest quest = new Quest(id, name, description, reward, isActive);
                quests.add(quest);
                System.out.println(id + " | " + name + " | " + description + " | " + reward + " | " + isActive);
            }
            ps.close();
        }catch (SQLException e){
            System.out.println(e);
        }
        return quests;
    }
}