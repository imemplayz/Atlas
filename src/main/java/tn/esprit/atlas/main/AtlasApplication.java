package tn.esprit.atlas.main;

import tn.esprit.atlas.controllers.UserController;
import tn.esprit.atlas.entities.User;

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

        // Initialize UserController
        UserController userController = new UserController();

        // CLI Menu
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== User Management ===");
            System.out.println("1. Add User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. List Users");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addUser(scanner, userController);
                    break;
                case 2:
                    updateUser(scanner, userController);
                    break;
                case 3:
                    deleteUser(scanner, userController);
                    break;
                case 4:
                    listUsers(userController);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void addUser(Scanner scanner, UserController userController) {
        System.out.println("\n=== Add User ===");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Address: ");
        String adresse = scanner.nextLine();
        System.out.print("Enter Role (Voyageur, Admin, SupportClient): ");
        String role = scanner.nextLine();
        System.out.print("Enter Profile Image URL: ");
        String profileImage = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String numTel = scanner.nextLine();
        System.out.print("Enter Travel Preferences: ");
        String voyageurPreferences = scanner.nextLine();
        System.out.print("Enter Preferred Destinations: ");
        String destinationsPreferrees = scanner.nextLine();
        System.out.print("Enter Budget: ");
        double budget = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdresse(adresse);
        user.setRole(role);
        user.setProfileImage(profileImage);
        user.setNumTel(numTel);
        user.setVoyageurPreferences(voyageurPreferences);
        user.setDestinationsPreferrees(destinationsPreferrees);
        user.setBudget(budget);

        userController.createUser(user);
        System.out.println("✅ User added successfully!");
    }

    private static void updateUser(Scanner scanner, UserController userController) {
        System.out.println("\n=== Update User ===");
        System.out.print("Enter User ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter New Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter New Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter New Address: ");
        String adresse = scanner.nextLine();
        System.out.print("Enter New Role (Voyageur, Admin, SupportClient): ");
        String role = scanner.nextLine();
        System.out.print("Enter New Profile Image URL: ");
        String profileImage = scanner.nextLine();
        System.out.print("Enter New Phone Number: ");
        String numTel = scanner.nextLine();
        System.out.print("Enter New Travel Preferences: ");
        String voyageurPreferences = scanner.nextLine();
        System.out.print("Enter New Preferred Destinations: ");
        String destinationsPreferrees = scanner.nextLine();
        System.out.print("Enter New Budget: ");
        double budget = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdresse(adresse);
        user.setRole(role);
        user.setProfileImage(profileImage);
        user.setNumTel(numTel);
        user.setVoyageurPreferences(voyageurPreferences);
        user.setDestinationsPreferrees(destinationsPreferrees);
        user.setBudget(budget);

        userController.modifyUser(user);
        System.out.println("✅ User updated successfully!");
    }

    private static void deleteUser(Scanner scanner, UserController userController) {
        System.out.println("\n=== Delete User ===");
        System.out.print("Enter User ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        userController.removeUser(id);
        System.out.println("✅ User deleted successfully!");
    }

    private static void listUsers(UserController userController) {
        System.out.println("\n=== List of Users ===");
        userController.listUsers();
    }
}