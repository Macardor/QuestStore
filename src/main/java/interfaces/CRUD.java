package interfaces;

public interface CRUD<T> {
    void add(T t);
    void edit(T t);
    void delete(int id);
}
