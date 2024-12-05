package com.example.projectdemo.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * Main class that runs the application.
 * @author Nuutti Turunen
 */
public class Gui extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/com/example/projectdemo/fxmlFiles/WelcomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/projectdemo/style.css").toExternalForm());
        stage.setTitle("Car Rental");
        stage.getIcons().add(new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm()));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the application.
     */
    public static void main(String[] args) {
        launch();
    }
}
