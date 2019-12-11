package interfaces;

public interface MentorDAO extends ModificableDAO {
    @Override
    Object create(Object user);

    @Override
    Object update(int id);

    @Override
    void delete(int id, boolean isActive);

}
