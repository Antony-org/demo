package example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Ensure this matches your database table name
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // or whatever your primary key type is

    private String userName;
    private String email;
    private String password;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
               "id=" + id +
               ", userName='" + userName + '\'' +
               ", email='" + email + '\'' +
               '}';
    }

    public Users() {}
    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}