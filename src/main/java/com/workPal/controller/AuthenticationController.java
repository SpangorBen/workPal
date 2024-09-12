package main.java.com.workPal.controller;

import main.java.com.workPal.dto.UserDto;
import main.java.com.workPal.services.impl.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    public void getAllUsers() {
        System.out.println("\n--- All Users ---");

        try {
            List<UserDto> allUsers = userService.getAllUsers();

            if (allUsers.isEmpty()) {
                System.out.println("No users found.");
            } else {
                for (UserDto userDto : allUsers) {
                    System.out.println("ID: " + userDto.getId() +
                            ", Name: " + userDto.getName() +
                            ", Email: " + userDto.getEmail());
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching users: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void RegisterUser() {
        try {
            UserDto createdUser = userService.registerUser("testuser", "testuser@example.com", "testpassword");
            System.out.println("User registered successfully! ID: " + createdUser.getId());
        } catch (Exception e) {
            System.err.println("Error registering user: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    public void getUserById(int id) {
//        try {
//            Optional<UserDto> user = userService.getUserById(id);
//            if (user.isPresent()) {
//                System.out.println("ID: " + user.getId() +
//                        ", Name: " + user.getName() +
//                        ", Email: " + user.getEmail());
//            } else {
//                System.out.println("User not found.");
//            }
//        } catch (Exception e) {
//            System.err.println("Error fetching user: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

    public void deleteUserById(int id) {
        try {
            userService.deleteUserById(id);
            System.out.println("User deleted successfully!");
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void testUpdateUser() {
        try {
            Optional<UserDto> user = userService.getUserById(15);

            if (user.isPresent()) {
                UserDto userDto = user.get();
                System.out.println("User found: " + userDto.getPassword());
                userDto.setName("Updated test");
                userService.updateUser(userDto);

                Optional<UserDto> updatedUser = userService.getUserById(15);
                if (updatedUser.isPresent() && updatedUser.get().getName().equals("Updated test")) {
                    System.out.println("User updated successfully!");
                } else {
                    System.err.println("User update failed.");
                }
            } else {
                System.out.println("User with id 1 not found.");
            }

        } catch (Exception e) {
            System.err.println("Error fetching user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean loginUser(String email, String password) {
        System.out.println("\n--- Login User ---");

        try {
            UserDto user = userService.loginUser(email, password);

            if (user != null) {
                System.out.println("User logged in successfully! ID: " + user.getId());
                return true;
            } else {
                System.out.println("Login failed.");
            }
        } catch (SQLException e) {
            System.err.println("Error logging in: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }
}
