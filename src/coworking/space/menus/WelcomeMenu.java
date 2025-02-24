package coworking.space.menus;

import java.util.Scanner;

public class WelcomeMenu {

    public void displayWelcomeTxt() {
        System.out.println("------------------");
        System.out.println("---------Hello to Sergi's coworking space---------");
        System.out.println("Account creation menu - press 1");
        System.out.println("Login menu - press 2");
        System.out.println("Exit - press 3");
        System.out.println("------------------");
    }

    public int getUserChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        return Integer.parseInt(scanner.nextLine());
    }
}