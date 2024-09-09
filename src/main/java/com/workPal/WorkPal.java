package main.java.com.workPal;

import main.java.com.workPal.config.DatabaseConnection;
import main.java.com.workPal.model.User;

import java.security.SecureRandom;
import java.sql.*;

public class WorkPal {
    public static void main(String[] args) {
//        try (Connection connection = DatabaseConnection.getConnection()) {
//            if (connection != null && !connection.isClosed()) {
//                System.out.println("Database connection successful!");
//            } else {
//                System.err.println("Failed to establish database connection.");
//            }
//
//        } catch (SQLException e) {
//            System.err.println("Error connecting to the database: " + e.getMessage());
//            e.printStackTrace(); // Print stack trace for debugging
//        }
//        Connection connection1 = DatabaseConnection.getConnection();
//        Connection connection2 = DatabaseConnection.getConnection();
//
//        System.out.println("Connection 1: " + connection1);
//        System.out.println("Connection 2: " + connection2);
//
//        System.out.println("Are connections the same object? " + (connection1 == connection2));

        User user = new User("John Doe", "test@test.com", "password");

        String sql = "INSERT INTO Users (name, email, password) VALUES (?, ?, ?)";

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
