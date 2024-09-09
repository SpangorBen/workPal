package main.java.com.workPal.repository.impl;

import main.java.com.workPal.config.DatabaseConnection;
import main.java.com.workPal.model.User;
import main.java.com.workPal.repository.interfaces.UserRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository implements UserRepositoryInterface {

    @Override
    public Optional<User> findUserById(Long id) {
        System.out.println("User found");
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        System.out.println("User found");
        return Optional.empty();
    }

    @Override
    public User saveUser(User user) {
        String sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUserName());
                pstmt.setString(2, user.getEmail());
                pstmt.setString(3, user.getPassword());
                pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        System.out.println("User updated");
    }

    @Override
    public void deleteUserById(Long id) {
        System.out.println("User deleted");
    }
}
