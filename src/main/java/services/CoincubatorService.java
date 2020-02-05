package services;

import daoImplementation.CoincubatorDAO;
import models.Coincubator;
import view.StaticUi;

import java.util.List;

public class CoincubatorService {
    private CoincubatorDAO coincubatorDAO = new CoincubatorDAO();

    public List<Coincubator> showAllCoincubators(){
        List<Coincubator> coincubators;
            coincubators = coincubatorDAO.getAllCoincubators();
        return coincubators;


    };

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
