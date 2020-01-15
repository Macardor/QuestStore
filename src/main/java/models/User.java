package models;

public abstract class User {
    private int id;
    private String login;
    private String password;
    private int userType;
    private String firstname;
    private String lastname;
    private boolean isActive;

    public User(int id, String login, String password, int userType, boolean isActive, String firstname, String lastname){

        this.id = id;
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.isActive = isActive;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public User(String login, String password, int userType, boolean isActive, String firstname, String lastname){
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.isActive = isActive;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
