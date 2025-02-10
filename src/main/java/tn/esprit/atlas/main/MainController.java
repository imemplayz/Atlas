package tn.esprit.atlas.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;

public class MainController {

    @FXML
    private void handleGoToSignIn(ActionEvent event) throws IOException {
        Parent signInView = FXMLLoader.load(getClass().getResource("/tn/esprit/atlas/views/signin-view.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Get the current scene size
        double width = currentStage.getScene().getWidth();
        double height = currentStage.getScene().getHeight();

        // Set the new scene with the same size
        Scene signInScene = new Scene(signInView, width, height);
        currentStage.setScene(signInScene);
        currentStage.show();
    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        Parent mainView = FXMLLoader.load(getClass().getResource("/tn/esprit/atlas/views/main-view.fxml"));

        // Get the current stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Get current scene size
        double width = currentStage.getScene().getWidth();
        double height = currentStage.getScene().getHeight();

        // Create a new scene with the same size
        Scene mainScene = new Scene(mainView, width, height);

        currentStage.setScene(mainScene);
        currentStage.show();
    }

}