package services;

import daoImplementation.CoincubatorDAOImplementation;
import models.Coincubator;
import view.StaticUi;

import java.util.ArrayList;
import java.util.List;

public class CoincubatorService {
    private CoincubatorDAOImplementation coincubatorDAOImplementation = new CoincubatorDAOImplementation();

    public List<Coincubator> showAllCoincubators(){
        List<Coincubator> coincubators;
            coincubators = coincubatorDAOImplementation.getAllCoincubators();
        return coincubators;
    };

    public void editCoincubatorById() {
        int coincubatorIdToEdit = StaticUi.idToEdit();
        Coincubator coincubator = coincubatorDAOImplementation.isCoincubatorWithIdInDB(coincubatorIdToEdit);
        if (coincubator != null){
            Coincubator newCoincubator;
            newCoincubator = StaticUi.coincubatorEditor(coincubator);
            coincubatorDAOImplementation.ediCoincubator(newCoincubator);
        }else{
            StaticUi.errorMassageIdNotInDB();
        }

    }
}
