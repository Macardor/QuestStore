package models;

public class Student extends User {


    public Student(int id, String login, String password, int userType, String firstname, String lastname) {
        super(id, login, password, userType, firstname, lastname);
    }
    public Student(String login, String password, int userType, String firstname, String lastname) {
        super(login, password, userType, firstname, lastname);
    }

    public Student(int id) {
        super(id);
    }

    @Override
    public int getUserType() {
        return super.getUserType();
    }

}
