module tn.esprit.atlas.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens tn.esprit.atlas.main to javafx.fxml;
    exports tn.esprit.atlas.main;
}