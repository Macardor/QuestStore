package DAO;

import SQL.PostgreSQLJDBC;
import models.Student;
import models.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CookieDAO {

    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public User getUserByCookieSessionId(String cookieSessionId) {

        String orderToSql = "SELECT u.id, ud.login, ud.password, ud.first_name, ud.last_name, u.is_active, u.user_type_id FROM cookies as c JOIN users as u on u.id = c.user_id JOIN user_details as ud on u.user_details_id = ud.id WHERE c.sesion_id = ?";
        User user = null;
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setString(1, cookieSessionId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                System.out.println("test");
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int userTypeId = resultSet.getInt("user_type_id");
                boolean isActive = resultSet.getBoolean("is_active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                System.out.println(id+ "| " + login+ "| " + password+ "| " + userTypeId+ "| " + firstName+ "| " + lastName);

                user = new Student(id, login, password, userTypeId, isActive, firstName, lastName);

            }
            preparedStatement.executeQuery();
            preparedStatement.close();
        }catch (SQLException e) {
            System.out.println(e);
        }return user;
    }



    public Date getCookieExpireDate(String cookieSessionId) {
        String orderToSql = "SELECT c.expire_date FROM cookies as c JOIN users as u on u.id = c.user_id WHERE c.sesion_id = ?";
        Date date = null;
        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setString(1, cookieSessionId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                date = resultSet.getDate("expire_date");
                return date;

            }
            preparedStatement.executeQuery();
            preparedStatement.close();
        }catch (SQLException e) {
            System.out.println(e);
        }return date;


    }


    public void setNewExpireDateForCookie(String cookieSessionId, Date expireDate) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        String orderForSql = ("UPDATE cookies SET expire_date = ?  WHERE sesion_id = ? ");

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderForSql);

            preparedStatement.setDate(1, expireDate);

            preparedStatement.setString(2, cookieSessionId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void putNewCookieToDB(String cookieSessionIdToAdd) {
        System.out.println("test3");
        String sqlQuery = "INSERT INTO cookies (sesion_id, expire_date, user_id) VALUES(?, ?, null )";

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(sqlQuery);

            preparedStatement.setString(1, cookieSessionIdToAdd);
            preparedStatement.setDate(2, null);



            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putUserIdToCookieInDB(int userId, String cookieSessionId) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        String orderForSql = ("UPDATE cookies SET user_id = ?  WHERE sesion_id = ? ");

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderForSql);

            preparedStatement.setInt(1, userId);

            preparedStatement.setString(2, cookieSessionId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setCookieForLogout(String cookieSessionId) {
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        String orderForSql = ("UPDATE cookies SET expire_date = ?  WHERE sesion_id = ? ");

        try {
            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderForSql);

            preparedStatement.setDate(1, null);

            preparedStatement.setString(2, cookieSessionId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
