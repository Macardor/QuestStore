package daoImplementation;

import interfaces.ModificableDAO;
import interfaces.StudentDAO;
import models.users.Mentor;
import models.users.User;

public class StudentDAOImplementation implements StudentDAO, ModificableDAO {

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

}
