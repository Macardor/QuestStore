package services;

import daoImplementation.CoincubatorDAOImplementation;
import models.Coincubator;

import java.util.ArrayList;
import java.util.List;

public class CoincubatorService {
    private CoincubatorDAOImplementation coincubatorDAOImplementation = new CoincubatorDAOImplementation();

    public List<Coincubator> showAllCoincubators(){
        List<Coincubator> coincubators;
            coincubators = coincubatorDAOImplementation.getAllCoincubators();
        return coincubators;
    };
}
