package interfaces;

public interface ReadableDAO<T> {
    T read(String table);
}
