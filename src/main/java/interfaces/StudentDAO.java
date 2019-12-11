package interfaces;

public interface StudentDAO extends ReadableDAO {
    @Override
    Object read(String table);

}
