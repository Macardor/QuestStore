package interfaces;

import models.users.User;

public interface ModificableDAO<T> {
    T create(T user);
    T update(int id);
    void delete(int id, boolean isActive);
}
