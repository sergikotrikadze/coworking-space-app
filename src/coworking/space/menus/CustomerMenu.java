package coworking.space.menus;

import coworking.space.entities.Reservation;
import coworking.space.entities.User;
import coworking.space.entities.Workspace;
import coworking.space.repository.ReservationRepository;
import coworking.space.repository.WorkspaceRepository;

import java.util.Scanner;
import java.util.Set;

public class CustomerMenu {
    private Scanner scanner;
    private WorkspaceRepository workspaceRepository;
    private ReservationRepository reservationRepository;
    private User user;

    public CustomerMenu(Scanner scanner, WorkspaceRepository workspaceRepository, ReservationRepository reservationRepository, User user) {
        this.scanner = scanner;
        this.workspaceRepository = workspaceRepository;
        this.reservationRepository = reservationRepository;
        this.user = user;
    }

    public void displayMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Browse available spaces");
            System.out.println("2. Make a reservation");
            System.out.println("3. View my reservations");
            System.out.println("4. Cancel a reservation");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    browseSpaces();
                    break;
                case "2":
                    makeReservation();
                    break;
                case "3":
                    viewMyReservations();
                    break;
                case "4":
                    cancelReservation();
                    break;
                case "5":
                    running = false;
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void browseSpaces() {
        Set<Workspace> spaces = workspaceRepository.getOnlyAvailableSpaces();
        if (spaces.isEmpty()) {
            System.out.println("No available spaces.");
        } else {
            for (Workspace w : spaces) {
                System.out.println(w);
            }
        }
    }

    private void makeReservation() {
        browseSpaces();
        System.out.print("Enter workspace ID to reserve: ");
        long workspaceId;
        try {
            workspaceId = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid workspace ID.");
            return;
        }
        Workspace workspace;
        try {
            workspace = workspaceRepository.getWorkspaceById(workspaceId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (!workspace.isAvailable()) {
            System.out.println("Workspace is not available.");
            return;
        }
        System.out.print("Enter reservation date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter start time (HH:MM): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter end time (HH:MM): ");
        String endTime = scanner.nextLine();

        Reservation reservation = new Reservation(workspaceId, user.getLogin(), date, startTime, endTime);
        reservationRepository.addReservation(reservation);
        // Mark workspace as unavailable if reserved.
        workspace.setAvailable(false);
        System.out.println("Reservation made: " + reservation);
    }

    private void viewMyReservations() {
        Set<Reservation> myReservations = reservationRepository.getReservationsByCustomer(user.getLogin());
        if (myReservations.isEmpty()) {
            System.out.println("You have no reservations.");
        } else {
            for (Reservation r : myReservations) {
                System.out.println(r);
            }
        }
    }

    private void cancelReservation() {
        System.out.print("Enter reservation ID to cancel: ");
        long resId;
        try {
            resId = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid reservation ID.");
            return;
        }
        try {
            reservationRepository.deleteReservationById(resId, user.getLogin());
            System.out.println("Reservation cancelled.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
