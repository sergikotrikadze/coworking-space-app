package coworking.space.menus;

import coworking.space.entities.UserRole;

import java.util.Scanner;

public class AccountCreationMenu {

    private static final String ADMIN_KEY = "admin123"; //if user enters this key they will be able to create an admin acc

    public void displayExplanationTxt() {
        System.out.println("------------------");
        System.out.println("Please fill out all forms except last one if you are just a customer");
        System.out.println("------------------");
    }

    public String getUserLogin(Scanner scanner) {
        System.out.print("Enter your Login: ");
        return scanner.nextLine();
    }

    public String getUserPassword(Scanner scanner) {
        System.out.print("Enter your Password: ");
        return scanner.nextLine();
    }

    public UserRole getUserRole(Scanner scanner) {
        System.out.println("Enter admin KEY: ");
        String input = scanner.nextLine();
        if (input.isEmpty()) return UserRole.CUSTOMER;
        if (input.equals(ADMIN_KEY)) return UserRole.ADMIN;
        throw new IllegalArgumentException("Invalid admin key!");
    }
}
