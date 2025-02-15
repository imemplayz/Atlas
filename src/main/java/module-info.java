module tn.esprit.atlas.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    exports tn.esprit.atlas.main;
    opens tn.esprit.atlas.main to javafx.fxml;
    exports tn.esprit.atlas.controllers to javafx.fxml;
    opens tn.esprit.atlas.controllers to javafx.fxml;
}