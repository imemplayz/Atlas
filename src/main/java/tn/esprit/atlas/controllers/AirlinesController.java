package tn.esprit.atlas.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import tn.esprit.atlas.entities.AirLine;
import tn.esprit.atlas.services.AirLineService;

import java.net.URL;
import java.util.ResourceBundle;

public class AirlinesController implements Initializable {

    @FXML
    private TableView<AirLine> airlinesTable;

    @FXML
    private TableColumn<AirLine, String> nomColumn;

    @FXML
    private TableColumn<AirLine, String> paysColumn;

    @FXML
    private TableColumn<AirLine, String> logoColumn;

    @FXML
    private TableColumn<AirLine, Void> actionsColumn; // New column for actions

    private AirLineService airLineService = new AirLineService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Bind table columns to AirLine properties
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        paysColumn.setCellValueFactory(new PropertyValueFactory<>("pays"));
        logoColumn.setCellValueFactory(new PropertyValueFactory<>("logo"));

        // Configure the actions column
        configureActionsColumn();

        // Load data into the table
        loadAirlinesData();
    }

    private void configureActionsColumn() {
        Callback<TableColumn<AirLine, Void>, TableCell<AirLine, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<AirLine, Void> call(final TableColumn<AirLine, Void> param) {
                return new TableCell<>() {
                    private final Button modifyButton = new Button("Modifier");
                    private final Button deleteButton = new Button("Supprimer");

                    {
                        // Handle modify button action
                        modifyButton.setOnAction(event -> {
                            AirLine airLine = getTableView().getItems().get(getIndex());
                            handleModifyAirline(airLine);
                        });

                        // Handle delete button action
                        deleteButton.setOnAction(event -> {
                            AirLine airLine = getTableView().getItems().get(getIndex());
                            handleDeleteAirline(airLine);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(new HBox(5, modifyButton, deleteButton)); // Add buttons to the cell
                        }
                    }
                };
            }
        };

        actionsColumn.setCellFactory(cellFactory);
    }

    private void handleModifyAirline(AirLine airLine) {
        // Implement logic to modify the selected airline
        System.out.println("Modify: " + airLine.getNom());
    }

    private void handleDeleteAirline(AirLine airLine) {
        // Implement logic to delete the selected airline
        airLineService.supprimer(airLine);
        loadAirlinesData(); // Refresh the table
    }

    private void loadAirlinesData() {
        ObservableList<AirLine> airlinesList = FXCollections.observableArrayList(airLineService.getall());
        airlinesTable.setItems(airlinesList);
    }

    @FXML
    private void handleAddAirline() {
        // Implement logic to add a new airline
    }
}