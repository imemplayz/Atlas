package tn.esprit.atlas.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import tn.esprit.atlas.entities.User;
import tn.esprit.atlas.utils.UserSession;

import java.io.IOException;
import java.util.Optional;

public class ProfileController {

    @FXML
    private Label title_username;
    @FXML
    private Label fullname;
    @FXML
    private Label creation_date;
    @FXML
    private Label email;
    @FXML
    private Label phone_number;
    @FXML
    private Label address;

    @FXML
    private TextField firstname_input;
    @FXML
    private TextField lastname_input;
    @FXML
    private TextField email_input;
    @FXML
    private TextField age_input;
    @FXML
    private TextField address_input;
    @FXML
    private TextField phone_input;

    @FXML
    private Button logout_button;

    private User user = UserSession.getUser();

    @FXML
    private void initialize() {
        // Set values for the Labels
        title_username.setText("/ " + user.getName() + " " + user.getSurname());
        fullname.setText(user.getName() + " " + user.getSurname());
        email.setText(user.getEmail());
        phone_number.setText(user.getNumTel());
        address.setText(user.getAdresse());

        // Set placeholder text in the TextFields
        firstname_input.setPromptText(user.getName());
        lastname_input.setPromptText(user.getSurname());
        email_input.setPromptText(user.getEmail());
        age_input.setPromptText(String.valueOf(user.getAge()));
        address_input.setPromptText(user.getAdresse());
        phone_input.setPromptText(user.getNumTel());
    }

    // Handle delete profile button click
    @FXML
    private void onDeleteProfileClicked() {
        // Create the confirmation alert
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Profile");
        alert.setHeaderText("Are you sure you want to delete your profile?");
        alert.setContentText("This action cannot be undone.");

        // Show the alert and capture the user's response
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If "OK" is clicked, proceed with the deletion
            deleteUser();
        }
    }

    // Delete the user
    private void deleteUser() {
        UserController.removeUser(user.getId());
        System.out.println("User deleted: " + user.getName() + " " + user.getSurname());
        loadScene("/tn/esprit/atlas/views/signup-view.fxml");
    }

    @FXML
    private void logoutUser(){
        UserController.logout();
        loadScene("/tn/esprit/atlas/views/signup-view.fxml");
    }

    public void loadScene(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) title_username.getScene().getWindow(); // Or any other component's scene reference
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle any potential IOExceptions
        }
    }
}
