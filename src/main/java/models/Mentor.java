package models;

public class Mentor extends User {
    public Mentor(int id, String login, String password, int userType, boolean isActive, String firstname, String lastname) {
        super(id, login, password, userType, isActive, firstname, lastname);
    }
    public Mentor(String login, String password, int userType, boolean isActive, String firstname, String lastname) {
        super(login, password, userType, isActive, firstname, lastname);
    }
}