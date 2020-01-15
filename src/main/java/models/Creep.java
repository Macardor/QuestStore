package models;

public class Creep extends User {
    public Creep(int id, String login, String password, int userType, boolean isActive, String firstname, String lastname) {
        super(id, login, password, userType, isActive, firstname, lastname);
    }
    public Creep(String login, String password, int userType, boolean isActive, String firstname, String lastname) {
        super(login, password, userType, isActive, firstname, lastname);
    }

}