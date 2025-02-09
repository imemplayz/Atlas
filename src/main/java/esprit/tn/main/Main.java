package esprit.tn.main;

import esprit.tn.entities.Hotel;
import esprit.tn.entities.Review;
import esprit.tn.services.HotelService;
import esprit.tn.services.ReviewService;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    // Set up a logger to replace printStackTrace
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            HotelService hotelService = new HotelService();
            ReviewService reviewService = new ReviewService();

            while (true) {
                System.out.println("\n1. Add Hotel | 2. List Hotels | 3. Add Review | 4. Edit Review | 5. Delete Review | 6. List Reviews | 7. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Add Hotel
                        Hotel hotel = new Hotel();
                        System.out.print("Hotel Name: ");
                        hotel.setName(scanner.nextLine());
                        System.out.print("Address: ");
                        hotel.setAddress(scanner.nextLine());
                        System.out.print("Rating: ");
                        hotel.setRating(scanner.nextFloat());
                        hotelService.add(hotel);
                        break;

                    case 2:
                        // List Hotels
                        hotelService.getAll().forEach(h ->
                                System.out.println(h.getId() + " | " + h.getName())
                        );
                        break;

                    case 3:
                        // Add Review
                        System.out.println("Select a Hotel to add a review:");
                        hotelService.getAll().forEach(h ->
                                System.out.println(h.getId() + " | " + h.getName())
                        );
                        System.out.print("Enter Hotel ID to add a review: ");
                        int hotelIdForReview = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Review Comment: ");
                        String comment = scanner.nextLine();
                        System.out.print("Enter Rating (1-5): ");
                        int rating = scanner.nextInt();
                        Review review = new Review(0, comment, rating, hotelIdForReview);
                        reviewService.add(review);
                        break;

                    case 4:
                        // Edit Review
                        System.out.println("Select a Review to edit:");
                        reviewService.getAll().forEach(r ->
                                System.out.println(r.getId() + " | " + r.getComment() + " | Rating: " + r.getRating())
                        );
                        System.out.print("Enter Review ID to edit: ");
                        int reviewIdToEdit = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Updated Comment: ");
                        String updatedComment = scanner.nextLine();
                        System.out.print("Updated Rating (1-5): ");
                        int updatedRating = scanner.nextInt();

                        // Get the current review to retain the hotelId
                        Review currentReview = reviewService.getById(reviewIdToEdit);
                        if (currentReview != null) {
                            // Use the existing hotelId for the update
                            Review reviewToEdit = new Review(reviewIdToEdit, updatedComment, updatedRating, currentReview.getHotelId());
                            reviewService.update(reviewToEdit);
                        } else {
                            System.out.println("Review not found!");
                        }
                        break;

                    case 5:
                        // Delete Review
                        System.out.println("Select a Review to delete:");
                        reviewService.getAll().forEach(r ->
                                System.out.println(r.getId() + " | " + r.getComment() + " | Rating: " + r.getRating())
                        );
                        System.out.print("Enter Review ID to delete: ");
                        int reviewIdToDelete = scanner.nextInt();
                        reviewService.delete(new Review(reviewIdToDelete)); // Use the constructor with ID
                        break;

                    case 6:
                        // List Reviews
                        reviewService.getAll().forEach(r ->
                                System.out.println("Review ID: " + r.getId() + " | Comment: " + r.getComment() + " | Rating: " + r.getRating() + " | Hotel ID: " + r.getHotelId())
                        );
                        break;

                    case 7:
                        // Exit
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice! Please choose again.");
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred", e); // Use logging instead of printStackTrace
        }
    }
}
