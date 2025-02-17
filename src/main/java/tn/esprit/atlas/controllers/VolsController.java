package tn.esprit.atlas.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import tn.esprit.atlas.entities.Vol;
import tn.esprit.atlas.services.VolService;

import java.util.List;

public class VolsController {

    @FXML
    private ListView<Vol> volListView;

    private VolService volService = new VolService(); // Service pour récupérer les vols

    public void initialize() {
        // Charger les vols depuis la base de données
        List<Vol> vols = volService.getall();
        ObservableList<Vol> observableVols = FXCollections.observableArrayList(vols);
        volListView.setItems(observableVols);

        // Personnaliser l'affichage de chaque vol dans la ListView
        volListView.setCellFactory(param -> new ListCell<Vol>() {
            @Override
            protected void updateItem(Vol vol, boolean empty) {
                super.updateItem(vol, empty);
                if (empty || vol == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Création d'une ligne pour chaque vol
                    HBox hBox = new HBox(20); // Espacement entre les éléments
                    Text departure = new Text(vol.getDeparture() + " - " + vol.getDestination());
                    Text seats = new Text(vol.getAvailableSeats() + " sièges disponibles");

                    // Appliquer du style au texte
                    departure.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
                    seats.setStyle("-fx-font-size: 14px; -fx-fill: green;"); // Texte vert

                    hBox.getChildren().addAll(departure, seats);
                    setGraphic(hBox);
                }
            }
        });
    }
}
