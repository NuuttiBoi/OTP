package com.example.projectdemo.controller;

import com.example.projectdemo.model.LanguageManager;
import javafx.fxml.FXML;
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

/**
 * Controller for the confirmation of a rental.
 */
public class ConfirmationPageController {

    @FXML
    public Text confirmText;
    @FXML
    public Button homeButton;
    @FXML
    private ImageView logo;

    @FXML
    private Pane mainPane;


    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        ResourceBundle bundle = LanguageManager.getResourceBundle();
        confirmText.setText(bundle.getString("confirmText"));
        homeButton.setText(bundle.getString("homeButton"));
        // Load the car logo image
        //Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        //logo.setImage(carLogo);

        // Set the background color of the main pane to blue
        //mainPane.setBackground(new Background(new BackgroundFill(Color.web("#21283d"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Takes the user back to the home page.
     */
    @FXML
    public void handleBackToStart() {
        try {
            // Load the StartPage.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/StartPage.fxml"));
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
