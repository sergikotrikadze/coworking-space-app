package coworking.space;

import coworking.space.entities.User;
import coworking.space.entities.Workspace;
import coworking.space.entities.WorkspaceType;
import coworking.space.menus.AccountCreationMenu;
import coworking.space.menus.AdminMenu;
import coworking.space.menus.CustomerMenu;
import coworking.space.menus.WelcomeMenu;
import coworking.space.repository.ReservationRepository;
import coworking.space.repository.UserRepository;
import coworking.space.repository.WorkspaceRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        WelcomeMenu welcomeMenu = new WelcomeMenu();
        UserRepository userRepository = new UserRepository();
        WorkspaceRepository workspaceRepository = new WorkspaceRepository();
        ReservationRepository reservationRepository = new ReservationRepository();

        // Prepopulate some workspaces
        Workspace ws1 = new Workspace();
        ws1.setWorkspaceType(WorkspaceType.OPEN_SPACE);
        ws1.setPrice(25.0);
        workspaceRepository.addWorkspace(ws1);

        Workspace ws2 = new Workspace();
        ws2.setWorkspaceType(WorkspaceType.PRIVATE_ROOM);
        ws2.setPrice(50.0);
        workspaceRepository.addWorkspace(ws2);

        boolean running = true;
        while (running) {
            welcomeMenu.displayWelcomeTxt();
            int userChoice = welcomeMenu.getUserChoice(scanner);

            switch (userChoice) {
                case 1: // Account creation
                    AccountCreationMenu accCreationMenu = new AccountCreationMenu();
                    accCreationMenu.displayExplanationTxt();
                    User newUser = new User();
                    newUser.setLogin(accCreationMenu.getUserLogin(scanner));
                    newUser.setPassword(accCreationMenu.getUserPassword(scanner));
                    try {
                        newUser.setUserRole(accCreationMenu.getUserRole(scanner));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    userRepository.addUser(newUser);
                    System.out.println("Account created successfully: " + newUser);
                    break;
                case 2: // Login
                    System.out.print("Enter your login: ");
                    String login = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();

                    User loggedInUser = userRepository.getUserByLogin(login);
                    if (loggedInUser == null || !loggedInUser.getPassword().equals(password)) {
                        System.out.println("Invalid login or password");
                        break;
                    }
                    System.out.println("Login successful. Welcome, " + loggedInUser.getLogin());

                    if (loggedInUser.getUserRole().equals(coworking.space.entities.UserRole.ADMIN)) {
                        AdminMenu adminMenu = new AdminMenu(scanner, workspaceRepository, reservationRepository);
                        adminMenu.displayMenu();
                    } else {
                        CustomerMenu customerMenu = new CustomerMenu(scanner, workspaceRepository, reservationRepository, loggedInUser);
                        customerMenu.displayMenu();
                    }
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
