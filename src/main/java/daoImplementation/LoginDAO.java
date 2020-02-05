package daoImplementation;

import SQL.PostgreSQLJDBC;
import models.Creep;
import models.Mentor;
import models.Student;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    public User isLoginAndPasswordInDB (String login, String password){
        String orderToSql = "SELECT * FROM users " +
                "join user_details " +
                "on users.user_details_id = user_details.id " +
                "where login = ? and password = ?";

        User result = null;
        try {

            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                int id = resultSet.getInt("id");
                int userTypeId = resultSet.getInt("user_type_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                boolean isActive = resultSet.getBoolean("is_active");

                System.out.println(id+ "| " + login+ "| " + password+ "| " + userTypeId+ "| " + firstName+ "| " + lastName);

                result = userCreatorByUserType(id, login ,password ,userTypeId , isActive, firstName, lastName);
            }
            preparedStatement.executeQuery();
        }catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }

        return result;
    }

    public User userCreatorByUserType (int id, String login, String password, int userType, boolean isActive, String firstName, String lastName){
        User user;
        if(userType == 1){
            user = new Student(id, login, password, userType, isActive, firstName, lastName);
        }
        else if(userType == 2){
            user = new Mentor(id, login, password, userType, isActive,firstName,lastName);
        }
        else{
            user = new Creep(id, login, password,userType, isActive, firstName, lastName);
        }
        return user;
    }


}
