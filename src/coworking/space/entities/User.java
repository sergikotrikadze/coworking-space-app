package coworking.space.entities;

public class User {
    
    private long id;
    private String login;
    private String password;
    private UserRole userRole;
    private static long autoImplementedId = 1;
    
    
    public User(String login, String password,UserRole userRole) {
        this.login = login;
        this.password = password;
        this.userRole = userRole;
        this.id = autoImplementedId;
        autoImplementedId++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    
    
}
