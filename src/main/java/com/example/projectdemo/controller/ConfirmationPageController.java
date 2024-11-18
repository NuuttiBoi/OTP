package com.example.projectdemo.controller;

import com.example.projectdemo.model.LanguageManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;

public class ConfirmationPageController {

    public Text confirmText;
    public Button homeButton;
    @FXML
    private ImageView logo;

    @FXML
    private Pane mainPane;


    @FXML
    public void initialize() {
        ResourceBundle bundle = LanguageManager.getResourceBundle();
        confirmText.setText(bundle.getString("confirmText"));
        homeButton.setText(bundle.getString("homeButton"));
        // Load the car logo image
        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        //logo.setImage(carLogo);

        // Set the background color of the main pane to blue
        //mainPane.setBackground(new Background(new BackgroundFill(Color.web("#21283d"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    public void handleBackToStart() {
        try {
            // Load the Start.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/Start.fxml"));
            Parent root = fxmlLoader.load();

            // Get the current stage
            Stage currentStage = (Stage) homeButton.getScene().getWindow();

            // Set the new scene
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Start Page");
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
