package coworking.space;

import coworking.space.entities.User;
import coworking.space.menus.AccountCreationMenu;
import coworking.space.menus.WelcomeMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        WelcomeMenu welcomeMenu = new WelcomeMenu();

        //from down here needs to be in nested while loops
        welcomeMenu.displayWelcomeTxt();
        int userChoice = welcomeMenu.getUserChoice(scanner);

        if (userChoice == 1) {
            AccountCreationMenu accCreationMenu = new AccountCreationMenu();
            accCreationMenu.displayExplanationTxt();
            User user = new User();
            user.setLogin(accCreationMenu.getUserLogin(scanner));
            user.setPassword(accCreationMenu.getUserPassword(scanner));
            user.setUserRole(accCreationMenu.getUserRole(scanner));
                    
        } else if (userChoice == 2) {
            
        }
    }

}
    