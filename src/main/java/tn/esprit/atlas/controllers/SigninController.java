package tn.esprit.atlas.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SigninController {

    public Button signInButton;
    @FXML
    private Button goBackButton;

    @FXML
    private void handleGoBack() throws IOException {
        loadScene("/tn/esprit/atlas/views/main-view.fxml");
    }

    @FXML
    private void handleGoToSignUp(MouseEvent event) throws IOException {
        loadScene("/tn/esprit/atlas/views/signup-view.fxml");
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

    public void handleSignIn(ActionEvent actionEvent) {
        // Add your sign-in logic here
    }
}
