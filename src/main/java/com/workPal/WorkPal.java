package main.java.com.workPal;

import main.java.com.workPal.config.ConnectionPool;
import main.java.com.workPal.config.DatabaseConnection;
import main.java.com.workPal.controller.AuthenticationController;
import main.java.com.workPal.dto.UserDto;
import main.java.com.workPal.model.*;
import main.java.com.workPal.repository.impl.UserRepository;
import main.java.com.workPal.services.impl.UserService;
import main.java.com.workPal.ui.AuthMenu;
import main.java.com.workPal.utils.PasswordUtil;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;
import java.util.Scanner;

public class WorkPal {
    public static void main(String[] args) {

        Connection conn = DatabaseConnection.getConnection();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Scanner scanner = new Scanner(System.in);



        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository, connectionPool);

        AuthenticationController authController = new AuthenticationController(userService);

        AuthMenu authMenu = new AuthMenu(authController);
        authMenu.start();

//        try (Connection connection = connectionPool.getConnection()) {
//
//            // Create a new Member object
//            Manager newMember = new Manager();
//            newMember.setName("testmember");
//            newMember.setPassword("testtest");
//            newMember.setEmail("test@test.com");
//            newMember.setRole(UserRole.MANAGER);
//            newMember.setPhoneNumber("123-456-7890");

//            // Hash the password using PasswordUtil
//            byte[] salt = PasswordUtil.generateSalt();
//            String hashedPassword = PasswordUtil.hashPassword(newMember.getPassword(), salt);
//            newMember.setPassword(hashedPassword);
//            newMember.setEncodedSalt(Base64.getEncoder().encodeToString(salt));

            // Save the member using the UserService
//            UserDto savedMemberDto = userService.saveManager(newMember, connection);
//
//            // Print the saved member details (or check in the database)
//            System.out.println("Saved Member: " + savedMemberDto);
//
//        } catch (SQLException e) {
//            System.err.println("Error during database operations: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            connectionPool.closeAllConnections();
//        }

//        DatabaseConnection.getConnection().close();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ConnectionPool.getInstance().closeAllConnections();
        }));
    }
}
