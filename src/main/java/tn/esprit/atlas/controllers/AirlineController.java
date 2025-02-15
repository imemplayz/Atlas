package tn.esprit.atlas.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

public class AirlineController {

    @FXML
    private ImageView tunisairImage; // Assurez-vous que l'ID correspond à l'image dans le fichier FXML

    @FXML
    public void initialize()
    {
        tunisairImage.setOnMouseClicked(event -> openTunisairDetails());
    }

    private void openTunisairDetails() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tunisair_details.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) tunisairImage.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


