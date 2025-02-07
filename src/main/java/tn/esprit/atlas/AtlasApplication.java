package tn.esprit.atlas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;

    public class AtlasApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AtlasApplication.class.getResource("/tn/esprit/atlas/views/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("ATLAS");

        InputStream inputStream = getClass().getResourceAsStream("/tn/esprit/atlas/assets/ATLAS_LOGO.png");
        if (inputStream == null) {
            System.err.println("Icon file not found! Check the path.");
        } else {
            Image icon = new Image(inputStream);
            stage.getIcons().add(icon);
        }

        stage.setScene(scene);

        stage.setMaximized(true);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}