package interfaces;

import models.users.User;

import java.util.List;

public interface ExtractDAO {

    public List<User> extract(int userType);
}
