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
import java.sql.SQLException;
import java.time.LocalDate;

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
    ImageView carPic;
    @FXML
    private Label carLabel;
    @FXML
    private Button backButton;
    boolean isSignedIn = true;
    Car car;
    private LocalDate returnDate;

    public void setCarDetails(Image carImage, Car selectedCar, LocalDate returnDate) {
        this.returnDate = returnDate;
        this.car = selectedCar;
        carPic.setImage(carImage);
        modelText.setText(selectedCar.getMake() + " " +  selectedCar.getModel());
        carDetailsText.setText("Year: " + selectedCar.getYear() + " \n Kilometers driven: " + selectedCar.getKm_driven());
    }
    public void onClick(){
        Stage stage = (Stage) carPic.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleRentCarClick() throws IOException, SQLException {
        // Load the FXML file for the form
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/RentCar.fxml"));
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
        car.setRented(returnDate.atStartOfDay());

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

