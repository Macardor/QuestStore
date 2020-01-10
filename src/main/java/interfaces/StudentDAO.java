package interfaces;

import models.User;

import java.util.List;

public interface StudentDAO{

    public void addStudent(String login, String password, String firstName, String lastName);
    public List<User> extractStudent();
    public void editStudent(int userId);
    public void removeStudent(int userId);
}
