package main.java.com.workPal;

import main.java.com.workPal.config.ConnectionPool;
import main.java.com.workPal.config.DatabaseConnection;
import main.java.com.workPal.controller.AuthenticationController;
import main.java.com.workPal.model.User;
import main.java.com.workPal.repository.impl.UserRepository;
import main.java.com.workPal.services.impl.UserService;
import main.java.com.workPal.utils.PasswordUtil;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;

public class WorkPal {
    public static void main(String[] args) {

        Connection conn = DatabaseConnection.getConnection();

        ConnectionPool connectionPool = ConnectionPool.getInstance();



        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository, connectionPool);

        AuthenticationController authController = new AuthenticationController(userService);


//        authController.getAllUsers();
//        authController.RegisterUser();
//        authController.getAllUsers();
//        authController.getUserById(2);
        authController.deleteUserById(2);
//        authController.getUserById(2);

//        DatabaseConnection.getConnection().close();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ConnectionPool.getInstance().closeAllConnections();
        }));
    }
}
