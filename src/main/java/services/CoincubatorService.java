package services;

import DAO.CoincubatorDAO;
import models.Coincubator;
import view.StaticUi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoincubatorService {
    private CoincubatorDAO coincubatorDAO = new CoincubatorDAO();

    public List<Coincubator> getAllCoincubators(ResultSet resultSet){
        List<Coincubator> coincubators = new ArrayList<>();
        try {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int currentDonation = resultSet.getInt("current_donation");
                int targetDonation = resultSet.getInt("target_donation");
                boolean isActive = resultSet.getBoolean("is_active");
                Coincubator coincubator = new Coincubator(id,name,description,currentDonation, targetDonation,isActive);
                coincubators.add(coincubator);
            }
            resultSet.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return coincubators;
    }

    public void addNewCoincubator() {
        Coincubator coincubator;
        coincubator = StaticUi.newCoincubator();
        coincubatorDAO.addCoincubator(coincubator);
    }

    public void editCoincubatorById() {
        int coincubatorIdToEdit = StaticUi.idToEdit();
        Coincubator coincubator = coincubatorDAO.isCoincubatorWithIdInDB(coincubatorIdToEdit);
        if (coincubator != null){
            Coincubator newCoincubator;
            newCoincubator = StaticUi.coincubatorEditor(coincubator);
            coincubatorDAO.ediCoincubator(newCoincubator);
        }else{
            StaticUi.errorMassageIdNotInDB();
        }

    }

    public void deleteCoincubatorById() {
        int coincubatorIdToEdit = StaticUi.idToEdit();
        Coincubator coincubator = coincubatorDAO.isCoincubatorWithIdInDB(coincubatorIdToEdit);
        if (coincubator != null){
            coincubator.setActive(false);
            coincubatorDAO.deleteCoincubator(coincubator);
        }else{
            StaticUi.errorMassageIdNotInDB();
        }
    }
}
