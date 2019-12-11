package models.users;

public class Student extends User {
    public Student(int id, String login, String password, int userType, String firstname, String lastname) {
        super(id, login, password, userType, firstname, lastname);
    }
}
