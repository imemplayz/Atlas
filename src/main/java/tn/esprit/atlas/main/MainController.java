package tn.esprit.atlas.main;

import tn.esprit.atlas.services.HotelService;
import tn.esprit.atlas.entities.Hotel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;



import java.io.IOException;

public class MainController {

    @FXML
    private Button switchToSignInButton;
    @FXML
    private Button switchToReservationButton;
    @FXML
    private Button switchToVolsButton;
    @FXML
    private Button switchToAirlinesButton;

    // Hotel Management Buttons
    @FXML
    private Button addHotelButton;
    @FXML
    private Button updateHotelButton;
    @FXML
    private Button deleteHotelButton;
    @FXML
    private Button viewHotelsButton;

    // Review Management Buttons
    @FXML
    private Button addReviewButton;
    @FXML
    private Button updateReviewButton;
    @FXML
    private Button deleteReviewButton;
    @FXML
    private Button viewReviewsButton;



    // FXML fields for hotel form
    @FXML
    private TextField hotelNameField;
    @FXML
    private TextField hotelAddressField;
    @FXML
    private TextField hotelRatingField;
    @FXML
    private Button submitHotelButton;

    private HotelService hotelService = new HotelService();

    // Hotel form submission handler
    @FXML
    private void handleSubmitHotelForm() {
        try {
            String name = hotelNameField.getText();
            String address = hotelAddressField.getText();
            float rating = Float.parseFloat(hotelRatingField.getText());

            // Create hotel object and add it to the database
            Hotel hotel = new Hotel();
            hotel.setName(name);
            hotel.setAddress(address);
            hotel.setRating(rating);

            // Add hotel using HotelService
            hotelService.add(hotel);

            // Show success message
            showAlert("Hotel Added", "Hotel added successfully!", AlertType.INFORMATION);
        } catch (Exception e) {
            // Show error message if something goes wrong
            showAlert("Error", "Failed to add hotel. Please try again.", AlertType.ERROR);
        }
    }

    // Method to show alert messages
    private void showAlert(String title, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }






    // Navigation Methods (already existing)
    @FXML
    private void switchToSignIn() throws IOException {
        Parent signInRoot = FXMLLoader.load(getClass().getResource("/tn/esprit/atlas/views/signin-view.fxml"));
        Scene currentScene = switchToSignInButton.getScene();
        currentScene.setRoot(signInRoot);
    }

    @FXML
    private void switchToReservation() throws IOException {
        Parent reservationRoot = FXMLLoader.load(getClass().getResource("/tn/esprit/atlas/views/reservation-view.fxml"));
        Scene currentScene = switchToReservationButton.getScene();
        currentScene.setRoot(reservationRoot);
    }

    @FXML
    private void switchToVols() throws IOException {
        Parent volsRoot = FXMLLoader.load(getClass().getResource("/tn/esprit/atlas/views/vols-view.fxml"));
        Scene currentScene = switchToVolsButton.getScene();
        currentScene.setRoot(volsRoot);
    }

    @FXML
    private void switchToAirlines() throws IOException {
        Parent airlinesRoot = FXMLLoader.load(getClass().getResource("/tn/esprit/atlas/views/airlines-view.fxml"));
        Scene currentScene = switchToAirlinesButton.getScene();
        currentScene.setRoot(airlinesRoot);
    }

    // Hotel Management Methods
    @FXML
    private void handleAddHotel() throws IOException {
        // Load the add hotel form view
        Parent addHotelRoot = FXMLLoader.load(getClass().getResource("/tn/esprit/atlas/views/add-hotel-view.fxml"));

        // Create a new scene with the add hotel form
        Scene addHotelScene = new Scene(addHotelRoot);

        // Get the current stage and set the new scene
        Stage currentStage = (Stage) addHotelButton.getScene().getWindow();
        currentStage.setScene(addHotelScene);
        currentStage.show();
    }

    @FXML
    private void handleUpdateHotel() throws IOException {
        // Logic to update a hotel
        System.out.println("Updating hotel...");
    }

    @FXML
    private void handleDeleteHotel() throws IOException {
        // Logic to delete a hotel
        System.out.println("Deleting hotel...");
    }

    @FXML
    private void handleViewHotels() throws IOException {
        // Logic to view hotels (e.g., load a list of hotels)
        System.out.println("Viewing hotels...");
    }

    // Review Management Methods
    @FXML
    private void handleAddReview() throws IOException {
        // Logic to add a review
        System.out.println("Adding review...");
    }

    @FXML
    private void handleUpdateReview() throws IOException {
        // Logic to update a review
        System.out.println("Updating review...");
    }

    @FXML
    private void handleDeleteReview() throws IOException {
        // Logic to delete a review
        System.out.println("Deleting review...");
    }

    @FXML
    private void handleViewReviews() throws IOException {
        // Logic to view reviews (e.g., load a list of reviews)
        System.out.println("Viewing reviews...");
    }
}
