package interfaces;

import models.users.User;

public interface CRUD<T> {
    T create(User user);
    T read(String table);
    T update(int id, T value);
    void delete(int id, boolean isActive);
}
