package coworking.space.menus;

import java.util.Scanner;

public class WelcomeMenu {

    public void displayWelcomeTxt() {
        System.out.println("------------------");
        System.out.println("Hello and welcome to Sergi's coworking space");
        System.out.println("1 - Create an account");
        System.out.println("2 - Login");
        System.out.println("3 - Exit");
        System.out.println("------------------");
    }

    public int getUserChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
