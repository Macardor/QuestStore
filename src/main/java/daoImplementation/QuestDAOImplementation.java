package daoImplementation;

import SQL.PostgreSQLJDBC;
import models.Quest;
import models.Student;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestDAOImplementation{
    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement ps = null;
    public void addQuest(Quest quest) {

        String sqlQuery = "INSERT INTO quests (name, description, reward) VALUES(?, ?, ?)";

        try {
            ps = postgreSQLJDBC.connect().prepareStatement(sqlQuery);

            ps.setString(1, quest.getName());
            ps.setString(2, quest.getDescription());
            ps.setInt(3, quest.getReward());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteQuest(int id) {

        String orderForSql = "UPDATE quests SET is_active = ? WHERE id = ?";

        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            int row = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editQuest(Quest quest) {

        String orderForSql = ("UPDATE quests SET name = ?, description = ?, reward = ?, is_active = ? WHERE id = ?");
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
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
//                System.out.println(id + " | " + name + " | " + description + " | " + reward + " | " + isActive);
            }
            ps.close();
        }catch (SQLException e){
            System.out.println(e);
        }
        return quests;
    }
    public List<Quest> getAllActiveQuests(){
        String orderForSql = "SELECT * FROM quests where is_active = true; ";
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
//                System.out.println(id + " | " + name + " | " + description + " | " + reward + " | " + isActive);
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
//            ps.setInt(1, id);
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
    public Quest getQuestById(int id){
        String orderToSql =  "SELECT * FROM quests where id = ?";
        Quest quest = null;
        try {
            ps = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int reward = resultSet.getInt("reward");
                boolean isActive = resultSet.getBoolean("is_active");
                quest = new Quest(id, name, description, reward, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quest;
    }

    public List<Quest> getAllQuestsNotDoneByStudent(Student student) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        PreparedStatement ps = null;
        String orderForSql = ("select q.id, name, description, reward, is_active from quests as q\n" +
                "                join user_quests uq\n" +
                "                    on q.id = uq.quest_id\n" +
                "                join students s\n" +
                "                    on uq.student_id = s.id\n" +
                "where s.user_id != ?\n" +
                "except\n" +
                "select q.id, name, description, reward, is_active from quests as q\n" +
                "                join user_quests uq\n" +
                "                    on q.id = uq.quest_id\n" +
                "                join students s\n" +
                "                    on uq.student_id = s.id\n" +
                "where s.user_id = ?;");

        List<Quest> quests = new ArrayList<>();
        try{
            ps = postgreSQLJDBC.connect().prepareStatement(orderForSql);
            ps.setInt(1,student.getId());
            ps.setInt(2,student.getId());
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

    public void setQuestActiveForStudent(int studentId, Quest quest, Date date) {
        String sqlQuery = "INSERT INTO user_quests (student_id, quest_id, is_available, completion_date) VALUES(?, ?, ?, ?)";

        try {
            ps = postgreSQLJDBC.connect().prepareStatement(sqlQuery);

            ps.setInt(1, studentId);
            ps.setInt(2, quest.getId());
            ps.setBoolean(3, true);
            ps.setDate(4, date);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
