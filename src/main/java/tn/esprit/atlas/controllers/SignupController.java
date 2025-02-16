package tn.esprit.atlas.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.atlas.entities.User;

import java.io.File;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField addressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button goBackButton;

    @FXML
    private Button uploadButton;

    @FXML
    private File uploadedFile;

    @FXML
    private Label fileLabel;

    private UserController userController = new UserController(); // Instance of UserController

    @FXML
    private void initialize() {
        // Restrict phoneField to numeric input only and limit to 10 digits
        phoneField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,10}")) { // Allow up to 10 digits
                return change;
            }
            return null; // Reject the change if it contains non-digits or exceeds 10 digits
        }));

        // Restrict ageField to numeric input only and limit to 3 digits (e.g., age 0-120)
        ageField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,3}")) { // Allow up to 3 digits
                return change;
            }
            return null; // Reject the change if it contains non-digits or exceeds 3 digits
        }));
    }

    @FXML
    private void handleSignUp() {
        if (validateForm()) {
            if (isValidEmail(emailField.getText())) {
                if (mathPasswords()) {
                    // Create a User object
                    User user = new User();
                    user.setName(firstNameField.getText());
                    user.setSurname(lastNameField.getText());
                    user.setAge(Integer.parseInt(ageField.getText()));
                    user.setAdresse(addressField.getText());
                    user.setEmail(emailField.getText());
                    user.setPassword(passwordField.getText());
                    user.setNumTel(phoneField.getText());

                    // Set default values for fields not included in the form
                    user.setRole("Voyageur"); // Default role
                    user.setProfileImage(uploadedFile != null ? uploadedFile.getAbsolutePath() : null); // Set profile image path if uploaded
                    user.setVoyageurPreferences(""); // Default preferences
                    user.setDestinationsPreferrees(""); // Default preferred destinations
                    user.setBudget(0.0); // Default budget

                    try {
                        // Add the user to the database
                        userController.createUser(user);

                        // Show success message
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("User Registered");
                        alert.setContentText("Your profile has been successfully registered!");
                        alert.showAndWait();

                        // Clear the form
                        clearForm();

                        // Redirect user to profile view
                        loadScene("/tn/esprit/atlas/views/profile-view.fxml");
                    } catch (SQLIntegrityConstraintViolationException e) {
                        // Handle duplicate email error
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Email Already Exists");
                        alert.setContentText("The email address is already registered. Please use a different email.");
                        alert.showAndWait();
                    } catch (Exception e) {
                        // Handle other exceptions
                        e.printStackTrace();
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Registration Failed");
                        alert.setContentText("An error occurred while registering the user. Please try again.");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Passwords mismatch");
                    alert.setContentText("Passwords do not match!");
                    alert.showAndWait();
                }
            } else {
                // Show error message for invalid email
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Form Error");
                alert.setHeaderText("Invalid Email");
                alert.setContentText("Please enter a valid email address.");
                alert.showAndWait();
            }
        } else {
            // Show error message for incomplete form
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Form Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please fill in all fields and upload an image.");
            alert.showAndWait();
        }
    }

    // Regex pattern for email validation
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // Compile the regex pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Method to validate an email address
    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    private boolean validateForm() {
        // Check if all fields are filled
        boolean fieldsFilled = !firstNameField.getText().isEmpty() &&
                !lastNameField.getText().isEmpty() &&
                !ageField.getText().isEmpty() &&
                !addressField.getText().isEmpty() &&
                !emailField.getText().isEmpty() &&
                !phoneField.getText().isEmpty() &&
                !passwordField.getText().isEmpty() &&
                !confirmPasswordField.getText().isEmpty() &&
                uploadedFile != null; // Check if a file has been uploaded

        // Validate email format
        boolean emailValid = isValidEmail(emailField.getText());

        return fieldsFilled && emailValid;
    }

    public boolean mathPasswords() {
        return passwordField.getText().equals(confirmPasswordField.getText());
    }

    @FXML
    private void handleUpload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        uploadedFile = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
        if (uploadedFile != null) {
            System.out.println("Selected file: " + uploadedFile.getAbsolutePath());
            fileLabel.setText(uploadedFile.getName());
        } else {
            System.out.println("No file selected.");
        }
    }

    @FXML
    private void handleGoToSignIn(MouseEvent event) throws IOException {
        loadScene("/tn/esprit/atlas/views/signin-view.fxml");
    }

    @FXML
    private void handleGoBack() throws IOException {
        loadScene("/tn/esprit/atlas/views/signin-view.fxml");
    }

    private void loadScene(String fxmlPath) throws IOException {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));

        // Get the current stage
        Stage stage = (Stage) goBackButton.getScene().getWindow();

        // Preserve window size
        double width = stage.getWidth();
        double height = stage.getHeight();

        // Set new root while keeping size
        stage.getScene().setRoot(root);
        stage.setWidth(width);
        stage.setHeight(height);
    }

    private void clearForm() {
        firstNameField.clear();
        lastNameField.clear();
        ageField.clear();
        addressField.clear();
        emailField.clear();
        phoneField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        uploadedFile = null;
    }
}