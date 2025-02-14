package tn.esprit.atlas.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button switchToSignInButton;

    @FXML
    private void switchToSignIn() throws IOException {
        // Load the sign-in view
        Parent signInRoot = FXMLLoader.load(getClass().getResource("/tn/esprit/atlas/views/signin-view.fxml"));

        // Get the current scene
        Scene currentScene = switchToSignInButton.getScene();

        // Set the new root to the existing scene
        currentScene.setRoot(signInRoot);
    }
}
