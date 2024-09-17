package com.example.projectdemo.controller;

import com.example.projectdemo.model.Car;
import com.example.projectdemo.model.CarDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Scene2 {

    @FXML
    public Text modelText;
    @FXML
    private ImageView carPic;  // Make sure this fx:id matches the FXML

    @FXML
    private Label carLabel;  // Assuming there's a Label for the selected car
    @FXML
    private Button backButton;
    // Method to set the car details dynamically
    public void setCarDetails(Image carImage, Car selectedCar) {
        carPic.setImage(carImage);  // Set the car image in the ImageView
        modelText.setText(selectedCar.getMake() + " " +  selectedCar.getModel());
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
    }
}

