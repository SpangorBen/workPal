package main.java.com.workPal.services.impl;

import main.java.com.workPal.config.ConnectionPool;
import main.java.com.workPal.dto.UserDto;
import main.java.com.workPal.model.User;
import main.java.com.workPal.repository.impl.UserRepository;
import main.java.com.workPal.utils.PasswordUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;
    private final ConnectionPool connectionPool;

    public UserService(UserRepository userRepository, ConnectionPool connectionPool) {
        this.userRepository = userRepository;
        this.connectionPool = connectionPool;
    }

    public UserDto getUserById(int id) throws SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            return userRepository.findById(id, "id", UserDto.class, connection).orElse(null);
        }
    }

    public List<UserDto> getAllUsers() throws SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            return userRepository.findAll(UserDto.class, connection);
        }
//        return userRepository.findAll(UserDto.class);
    }

    public UserDto registerUser(String name, String email, String password) throws SQLException {
        byte[] salt = PasswordUtil.generateSalt();
        String hashedPassword = PasswordUtil.hashPassword(password, salt);

        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(hashedPassword);
        newUser.setEmail(email);
        newUser.setEncodedSalt(Base64.getEncoder().encodeToString(salt));

        try (Connection connection = connectionPool.getConnection()) {
            return userRepository.save(newUser, UserDto.class, connection);
        }
//        return userRepository.save(newUser, UserDto.class);
    }

    public void deleteUserById(int id) throws SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            userRepository.deleteById(id, "id", connection);
        }
    }
}
