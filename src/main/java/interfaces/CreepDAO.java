package interfaces;

public interface CreepDAO extends ModificableDAO, ReadableDAO {
    @Override
    Object create(Object user);

    @Override
    Object update(int id);

    @Override
    void delete(int id, boolean isActive);



}
