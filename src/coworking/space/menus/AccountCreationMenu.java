package coworking.space.menus;

import coworking.space.entities.UserRole;

import java.util.Scanner;

public class AccountCreationMenu {

    private static final String ADMIN_KEY = "admin123"; // If user enters this key, they can create an admin account

    public void displayExplanationTxt() {
        System.out.println("------------------");
        System.out.println("Please fill out the forms. Leave the admin key empty if you are a customer.");
        System.out.println("------------------");
    }

    public String getUserLogin(Scanner scanner) {
        System.out.print("Enter your login: ");
        return scanner.nextLine();
    }

    public String getUserPassword(Scanner scanner) {
        System.out.print("Enter your password: ");
        return scanner.nextLine();
    }

    public UserRole getUserRole(Scanner scanner) {
        System.out.print("Enter admin KEY (or press Enter to continue as Customer): ");
        String input = scanner.nextLine();
        if (input.isEmpty()) return UserRole.CUSTOMER;
        if (input.equals(ADMIN_KEY)) return UserRole.ADMIN;
        throw new IllegalArgumentException("Invalid admin key!");
    }
}
//