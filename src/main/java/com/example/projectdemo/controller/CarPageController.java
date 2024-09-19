package com.example.projectdemo.controller;

import com.example.projectdemo.model.Car;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.io.IOException;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CarPageController {

    @FXML
    public Text modelText;
    @FXML
    public Text carDetailsText;
    public ListView carLocationList;
    @FXML
    private ImageView carPic;  // Make sure this fx:id matches the FXML
    @FXML
    private Label carLabel;  // Assuming there's a Label for the selected car
    @FXML
    private Button backButton;
    private boolean isSignedIn = true;
    // Method to set the car details dynamically
    public void setCarDetails(Image carImage, Car selectedCar) {
        carPic.setImage(carImage);  // Set the car image in the ImageView
        modelText.setText(selectedCar.getMake() + " " +  selectedCar.getModel());
        carDetailsText.setText(selectedCar.getModel() + " " + selectedCar.getYear());
    }
    public void onClick(){
        // Get the current stage (window) and close it
        Stage stage = (Stage) carPic.getScene().getWindow();
        stage.close();
    }

    // This method will be called when "Rent Car" button is clicked
    @FXML
    private void handleRentCarClick() throws IOException {
        // Load the FXML file for the form
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/RentCar.fxml"));
        Parent formLayout = fxmlLoader.load();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // (placeholder) tähän logiikka, jos käyttäjä ei ole kirjautunut sisääm,
        // niin hänet viedään kirjautumisruutuun.
        // Jos on kirjautunut sisään niin auton pystyy vuokraamaan.
        if(isSignedIn){
            alert.setContentText("You are signed in");
            alert.showAndWait();
        } else {
            alert.setContentText("You have to sign in/register in order to rent a car");
            alert.showAndWait();
        }

        /*
        // Create a new stage (window) for the form
        Stage formWindow = new Stage();
        formWindow.setTitle("Rent Car");

        // Set the form scene
        Scene formScene = new Scene(formLayout, 400, 300);
        formWindow.setScene(formScene);

        // Set the new window as a modal window
        formWindow.initModality(Modality.APPLICATION_MODAL);

        // Show the window and wait until it's closed before returning to the previous window
        formWindow.showAndWait();
         */
    }
}

