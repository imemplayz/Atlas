package tn.esprit.atlas.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SigninController {

    public Button signInButton;
    public TextField passwordField;
    public TextField emailField;
    @FXML
    private Button goBackButton;

    @FXML
    private void handleGoBack() throws IOException {
        // Load the main view
        Parent mainRoot = FXMLLoader.load(getClass().getResource("/tn/esprit/atlas/views/main-view.fxml"));

        // Get the current scene
        Scene currentScene = goBackButton.getScene();

        // Load the CSS file
        String css = this.getClass().getResource("/tn/esprit/atlas/css/signin.css").toExternalForm();

        // Create a new scene with the mainRoot and apply the CSS
        Scene newScene = new Scene(mainRoot);
        newScene.getStylesheets().add(css);

        // Set the new scene to the stage
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.setScene(newScene);
    }

    public void handleSignIn(ActionEvent actionEvent) {
        // Add your sign-in logic here
    }
}