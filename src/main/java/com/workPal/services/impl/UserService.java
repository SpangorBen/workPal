package main.java.com.workPal.services.impl;

import main.java.com.workPal.config.ConnectionPool;
import main.java.com.workPal.dto.UserDto;
import main.java.com.workPal.model.*;
import main.java.com.workPal.repository.impl.UserRepository;
import main.java.com.workPal.utils.PasswordUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;
    private final ConnectionPool connectionPool;

    public UserService(UserRepository userRepository, ConnectionPool connectionPool) {
        this.userRepository = userRepository;
        this.connectionPool = connectionPool;
    }

    public Optional<UserDto> getUserById(int id) throws SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            return userRepository.findById(id, "id", UserDto.class, connection);
        }
    }

    public List<UserDto> getAllUsers() throws SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            return userRepository.findAll(UserDto.class, connection);
        }
//        return userRepository.findAll(UserDto.class);
    }

    public UserDto loginUser(String email, String password) throws SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            Optional<UserDto> userOptional = userRepository.findByEmail(email, connection);

            if (userOptional.isPresent()) {
                UserDto user = userOptional.get();

                byte[] salt = Base64.getDecoder().decode(user.getEncodedSalt());
                String hashedPassword = PasswordUtil.hashPassword(password, salt);

                if (hashedPassword.equals(user.getPassword())) {
                    return user;
                }
            }
            return null;
        }
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

    public void updateUser(UserDto userDto) throws SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            userRepository.update(userDto, connection);
        }
    }

    public UserDto saveMember(Member member, Connection connection) throws SQLException {
        byte[] salt = PasswordUtil.generateSalt();
        String hashedPassword = PasswordUtil.hashPassword(member.getPassword(), salt);
        member.setPassword(hashedPassword);
        member.setEncodedSalt(Base64.getEncoder().encodeToString(salt));

        return userRepository.save(member, UserDto.class, connection);
    }

    public UserDto saveManager(Manager manager, Connection connection) throws SQLException {
        byte[] salt = PasswordUtil.generateSalt();
        String hashedPassword = PasswordUtil.hashPassword(manager.getPassword(), salt);
        manager.setPassword(hashedPassword);
        manager.setEncodedSalt(Base64.getEncoder().encodeToString(salt));

        return userRepository.save(manager, UserDto.class, connection);
    }

    public UserDto saveAdmin(Admin admin, Connection connection) throws SQLException {
        byte[] salt = PasswordUtil.generateSalt();
        String hashedPassword = PasswordUtil.hashPassword(admin.getPassword(), salt);
        admin.setPassword(hashedPassword);
        admin.setEncodedSalt(Base64.getEncoder().encodeToString(salt));
//        admin.setRole(UserRole.ADMIN);

        return userRepository.save(admin, UserDto.class, connection);
    }
}
