package models;

public class Mentor extends User {
    public Mentor(int id, String login, String password, int userType, String firstname, String lastname) {
        super(id, login, password, userType, firstname, lastname);
    }
    public Mentor(String login, String password, int userType, String firstname, String lastname) {
        super(login, password, userType, firstname, lastname);
    }
}
