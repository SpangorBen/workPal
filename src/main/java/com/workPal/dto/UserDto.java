package main.java.com.workPal.dto;

import main.java.com.workPal.model.User;

public class UserDto implements Dto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String encodedSalt;

    public UserDto() {
    }

    public UserDto(String name, String email, String password, String encodedSalt) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.encodedSalt = encodedSalt;
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

    public String[] getAttributes() {
        return new String[] {"name", "email", "password", "encodedSalt"};
    }

    public static UserDto fromEntity(User user) {
        return new UserDto(user.getName(), user.getPassword(), user.getEmail(), user.getEncodedSalt());
    }
}
