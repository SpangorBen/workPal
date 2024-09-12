package main.java.com.workPal.dto;

import main.java.com.workPal.model.*;

public class UserDto implements Dto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String encodedSalt;
    private UserRole role;

    public UserDto() {
    }

    public UserDto(String name, String email, String password, String encodedSalt, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.encodedSalt = encodedSalt;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String[] getAttributes() {
        return new String[] {"name", "email", "password", "encodedSalt", "role"};
    }

    public static UserDto fromEntity(User user) {
        return new UserDto(user.getName(), user.getEmail(), user.getPassword(), user.getEncodedSalt(), user.getRole());
    }

    public static UserDto fromEntity(Member user) {
        return new UserDto(user.getName(), user.getEmail(), user.getPassword(), user.getEncodedSalt(), user.getRole());
    }

    public static UserDto fromEntity(Manager user) {
        return new UserDto(user.getName(), user.getEmail(), user.getPassword(), user.getEncodedSalt(), user.getRole());
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", encodedSalt='" + encodedSalt + '\'' +
                ", role=" + role +
                '}';
    }

    public static UserDto fromEntity(Admin user) {
        return new UserDto(user.getName(), user.getEmail(), user.getPassword(), user.getEncodedSalt(), user.getRole());
    }
}
