package daoImplementation;

import interfaces.CreepDAO;
import models.users.Mentor;

public class CreepDAOImplementation implements ModificableDAO, CreepDAO {
    @Override
    public Object create(Object user) {
        return null;
    }

    @Override
    public Object update(int id) {
        return null;
    }

    @Override
    public void delete(int id, boolean isActive) {

    }

    @Override
    public Object read(String table) {
        return null;
    }


    public Mentor read(int number){
        return null;
    }

}
