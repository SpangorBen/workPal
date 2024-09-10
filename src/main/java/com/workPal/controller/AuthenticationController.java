package main.java.com.workPal.controller;

import main.java.com.workPal.dto.UserDto;
import main.java.com.workPal.services.impl.UserService;

import java.util.List;

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
            UserDto createdUser = userService.registerUser("testuser", "password123", "testuser@example.com");
            System.out.println("User registered successfully! ID: " + createdUser.getId());
        } catch (Exception e) {
            System.err.println("Error registering user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void getUserById(int id) {
        try {
            UserDto user = userService.getUserById(id);
            if (user == null) {
                System.out.println("User not found.");
            } else {
                System.out.println("ID: " + user.getId() +
                        ", Name: " + user.getName() +
                        ", Email: " + user.getEmail());
            }
        } catch (Exception e) {
            System.err.println("Error fetching user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteUserById(int id) {
        try {
            userService.deleteUserById(id);
            System.out.println("User deleted successfully!");
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
