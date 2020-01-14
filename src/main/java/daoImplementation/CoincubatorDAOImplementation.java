package daoImplementation;

import SQL.PostgreSQLJDBC;
import models.Coincubator;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoincubatorDAOImplementation {
    PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public List<Coincubator> getAllCoincubators(){
        List<Coincubator> coincubators = new ArrayList<>();
        String orderToSql = "SELECT * FROM coincubators " +
                "WHERE is_active = true;";

        try {

            preparedStatement = postgreSQLJDBC.connect().prepareStatement(orderToSql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int currentDonation = resultSet.getInt("current_donation");
                int targetDonation = resultSet.getInt("target_donation");
                boolean isActive = resultSet.getBoolean("is_active");
                Coincubator coincubator = new Coincubator(id,name,description,currentDonation, targetDonation,isActive);

                System.out.println(id+ "| " + name+ "| " + description+ "| " + currentDonation+ "| " + targetDonation + "| " + isActive);
                coincubators.add(coincubator);

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

        return  coincubators;
    }
}
