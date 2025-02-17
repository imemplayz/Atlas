package tn.esprit.atlas.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tn.esprit.atlas.entities.User;
import tn.esprit.atlas.utils.UserSession;

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


    private User user = UserSession.getUser();

    @FXML
    private void initialize() {
        title_username.setText("/ " + user.getName() + " " + user.getSurname());
        fullname.setText(user.getName() + " " + user.getSurname());
        email.setText(user.getEmail());
        phone_number.setText(user.getNumTel());
        address.setText(user.getAdresse());
    }
}
