module tn.esprit.atlas {
    requires javafx.controls;
    requires javafx.fxml;


    opens tn.esprit.atlas to javafx.fxml;
    exports tn.esprit.atlas;
}