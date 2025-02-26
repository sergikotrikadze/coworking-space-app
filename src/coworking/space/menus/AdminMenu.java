package coworking.space.menus;

import coworking.space.entities.Workspace;
import coworking.space.repository.ReservationRepository;
import coworking.space.repository.WorkspaceRepository;
import coworking.space.exceptions.DataAccessException;

import java.util.Scanner;
import java.util.Set;

public class AdminMenu {
    private Scanner scanner;
    private WorkspaceRepository workspaceRepository;
    private ReservationRepository reservationRepository;

    public AdminMenu(Scanner scanner, WorkspaceRepository workspaceRepository, ReservationRepository reservationRepository) {
        this.scanner = scanner;
        this.workspaceRepository = workspaceRepository;
        this.reservationRepository = reservationRepository;
    }

    public void displayMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add a new coworking space");
            System.out.println("2. Remove a coworking space");
            System.out.println("3. View all reservations");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addWorkspace();
                    break;
                case "2":
                    removeWorkspace();
                    break;
                case "3":
                    viewAllReservations();
                    break;
                case "4":
                    running = false;
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addWorkspace() {
        System.out.print("Enter workspace type (OPEN_SPACE or PRIVATE_ROOM): ");
        String typeStr = scanner.nextLine();
        Workspace workspace = new Workspace();
        try {
            workspace.setWorkspaceType(Enum.valueOf(coworking.space.entities.WorkspaceType.class, typeStr.toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid workspace type.");
            return;
        }
        System.out.print("Enter price: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price.");
            return;
        }
        workspace.setPrice(price);
        workspaceRepository.addWorkspace(workspace);
        System.out.println("Workspace added: " + workspace);
        try {
            workspaceRepository.saveSpacesToFile("workspaces.txt");
        } catch (DataAccessException e) {
            System.out.println("Error saving workspace state: " + e.getMessage());
        }
    }

    private void removeWorkspace() {
        System.out.print("Enter workspace ID to remove: ");
        long id;
        try {
            id = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }
        try {
            workspaceRepository.deleteWorkspaceById(id);
            System.out.println("Workspace removed.");
            workspaceRepository.saveSpacesToFile("workspaces.txt");
        } catch (IllegalArgumentException | DataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewAllReservations() {
        Set<coworking.space.entities.Reservation> reservations = reservationRepository.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (coworking.space.entities.Reservation r : reservations) {
                System.out.println(r);
            }
        }
    }
}
