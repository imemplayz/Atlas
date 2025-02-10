package tn.esprit.atlas.main;

import tn.esprit.atlas.controllers.ForfaitController;
import tn.esprit.atlas.controllers.ReservationController;
import tn.esprit.atlas.entities.Forfait;
import tn.esprit.atlas.entities.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AtlasApplication {

    public static void main(String[] args) {
        // Initialize Database Connection
        try {
            DatabaseConnection.getInstance();
        } catch (RuntimeException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            return; // Exit if database connection fails
        }

        // Initialize Controllers
        ReservationController reservationController = new ReservationController();
        ForfaitController forfaitController = new ForfaitController();

        // CLI Menu
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Manage Reservations");
            System.out.println("2. Manage Forfaits");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageReservations(scanner, reservationController);
                    break;
                case 2:
                    manageForfaits(scanner, forfaitController);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void manageReservations(Scanner scanner, ReservationController reservationController) {
        while (true) {
            System.out.println("\n=== Reservation Management ===");
            System.out.println("1. Add Reservation");
            System.out.println("2. Update Reservation");
            System.out.println("3. Delete Reservation");
            System.out.println("4. List Reservations");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addReservation(scanner, reservationController);
                    break;
                case 2:
                    updateReservation(scanner, reservationController);
                    break;
                case 3:
                    deleteReservation(scanner, reservationController);
                    break;
                case 4:
                    listReservations(reservationController);
                    break;
                case 5:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void manageForfaits(Scanner scanner, ForfaitController forfaitController) {
        while (true) {
            System.out.println("\n=== Forfait Management ===");
            System.out.println("1. Add Forfait");
            System.out.println("2. Update Forfait");
            System.out.println("3. Delete Forfait");
            System.out.println("4. List Forfaits");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addForfait(scanner, forfaitController);
                    break;
                case 2:
                    updateForfait(scanner, forfaitController);
                    break;
                case 3:
                    deleteForfait(scanner, forfaitController);
                    break;
                case 4:
                    listForfaits(forfaitController);
                    break;
                case 5:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Reservation Management Methods
    private static void addReservation(Scanner scanner, ReservationController reservationController) {
        System.out.println("\n=== Add Reservation ===");
        System.out.print("Enter Reservation Type: ");
        String typeReservation = scanner.nextLine();
        System.out.print("Enter Reservation Description: ");
        String descriptionReservation = scanner.nextLine();
        System.out.print("Enter Reservation Price: ");
        double prix = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Reservation Status: ");
        String statut = scanner.nextLine();
        System.out.print("Enter Reservation Date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        Date dateReservation = java.sql.Date.valueOf(dateStr);

        Reservation reservation = new Reservation();
        reservation.setTypeReservation(typeReservation);
        reservation.setDescriptionReservation(descriptionReservation);
        reservation.setPrix(prix);
        reservation.setStatut(statut);
        reservation.setDateReservation(dateReservation);

        reservationController.createReservation(reservation);
        System.out.println("✅ Reservation added successfully!");
    }

    private static void updateReservation(Scanner scanner, ReservationController reservationController) {
        System.out.println("\n=== Update Reservation ===");
        System.out.print("Enter Reservation ID to update: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter New Reservation Type: ");
        String typeReservation = scanner.nextLine();
        System.out.print("Enter New Reservation Description: ");
        String descriptionReservation = scanner.nextLine();
        System.out.print("Enter New Reservation Price: ");
        double prix = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter New Reservation Status: ");
        String statut = scanner.nextLine();
        System.out.print("Enter New Reservation Date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        Date dateReservation = java.sql.Date.valueOf(dateStr);

        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationId);
        reservation.setTypeReservation(typeReservation);
        reservation.setDescriptionReservation(descriptionReservation);
        reservation.setPrix(prix);
        reservation.setStatut(statut);
        reservation.setDateReservation(dateReservation);

        reservationController.modifyReservation(reservation);
        System.out.println("✅ Reservation updated successfully!");
    }

    private static void deleteReservation(Scanner scanner, ReservationController reservationController) {
        System.out.println("\n=== Delete Reservation ===");
        System.out.print("Enter Reservation ID to delete: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        reservationController.removeReservation(reservationId);
        System.out.println("✅ Reservation deleted successfully!");
    }

    private static void listReservations(ReservationController reservationController) {
        System.out.println("\n=== List of Reservations ===");
        reservationController.listReservations();
    }

    // Forfait Management Methods
    private static void addForfait(Scanner scanner, ForfaitController forfaitController) {
        System.out.println("\n=== Add Forfait ===");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Duration (in days): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Destinations (comma-separated): ");
        String destinationsStr = scanner.nextLine();
        List<String> destinations = List.of(destinationsStr.split(","));
        System.out.print("Enter Available Seats: ");
        int availableSeats = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Package Image URL: ");
        String packageImage = scanner.nextLine();

        Forfait forfait = new Forfait();
        forfait.setName(name);
        forfait.setDescription(description);
        forfait.setPrice(price);
        forfait.setDuration(duration);
        forfait.setDestinations(destinations);
        forfait.setAvailableSeats(availableSeats);
        forfait.setPackageImage(packageImage);

        forfaitController.createForfait(forfait);
        System.out.println("✅ Forfait added successfully!");
    }

    private static void updateForfait(Scanner scanner, ForfaitController forfaitController) {
        System.out.println("\n=== Update Forfait ===");
        System.out.print("Enter Forfait ID to update: ");
        int packaged = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter New Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter New Duration (in days): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter New Destinations (comma-separated): ");
        String destinationsStr = scanner.nextLine();
        List<String> destinations = List.of(destinationsStr.split(","));
        System.out.print("Enter New Available Seats: ");
        int availableSeats = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter New Package Image URL: ");
        String packageImage = scanner.nextLine();

        Forfait forfait = new Forfait();
        forfait.setPackaged(packaged);
        forfait.setName(name);
        forfait.setDescription(description);
        forfait.setPrice(price);
        forfait.setDuration(duration);
        forfait.setDestinations(destinations);
        forfait.setAvailableSeats(availableSeats);
        forfait.setPackageImage(packageImage);

        forfaitController.modifyForfait(forfait);
        System.out.println("✅ Forfait updated successfully!");
    }

    private static void deleteForfait(Scanner scanner, ForfaitController forfaitController) {
        System.out.println("\n=== Delete Forfait ===");
        System.out.print("Enter Forfait ID to delete: ");
        int packaged = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        forfaitController.removeForfait(packaged);
        System.out.println("✅ Forfait deleted successfully!");
    }

    private static void listForfaits(ForfaitController forfaitController) {
        System.out.println("\n=== List of Forfaits ===");
        forfaitController.listForfaits();
    }
}