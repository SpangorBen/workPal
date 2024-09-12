package main.java.com.workPal.model;

public class User implements Entity<Integer> {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String encodedSalt;
    private UserRole role;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
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

    public String getEncodedSalt() {
        return encodedSalt;
    }

    public void setEncodedSalt(String encodedSalt) {
        this.encodedSalt = encodedSalt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
