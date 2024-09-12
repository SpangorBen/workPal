package main.java.com.workPal.ui;

import main.java.com.workPal.controller.AuthenticationController;

import java.util.Scanner;

public class AuthMenu {

    private final AuthenticationController authenticationController;
    private final Scanner scanner;

    public AuthMenu(AuthenticationController authenticationController) {
        this.authenticationController = authenticationController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n--- WorkPal Login ---");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void handleLogin() {
        System.out.println("\n--- Login ---");
        scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean loginSuccess = authenticationController.loginUser(email, password);
        if (loginSuccess) {
            System.out.println("Login successful!");

        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }
}
