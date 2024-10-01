package com.example.projectdemo.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.projectdemo.model.Car;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarPageControllerTest {
    private CarPageController controller;
    private Text modelText;
    private Text carDetailsText;
    private ImageView carPic;
    private Car car;

    @BeforeEach
    public void setUp() {
        controller = new CarPageController();
        modelText = mock(Text.class);
        carDetailsText = mock(Text.class);
        carPic = mock(ImageView.class);
        controller.modelText = modelText;
        controller.carDetailsText = carDetailsText;
        controller.carPic = carPic;

        // Initialize a Car object for testing
        car = new Car("1", "Toyota", "Camry", 2020, "ABC123", true, 50.0, "New York", 15000);
    }

    @Test
    public void testSetCarDetails() {
        LocalDate returnDate = LocalDate.now().plusDays(7);
        LocalDate startDate = LocalDate.now().plusDays(0);

        Image carImage = new Image("path/to/image.png"); // Use a valid image path

        controller.setCarDetails(carImage, car, startDate, returnDate);

        verify(carPic).setImage(carImage);
        verify(modelText).setText("Toyota Camry");
        verify(carDetailsText).setText("Year: 2020 \n Kilometers driven: 15000");
    }


    @Test
    public void testHandleRentCarClick_SignedIn() throws IOException, SQLException {
        controller.isSignedIn = true; // User is signed in
        controller.car = car; // Set the car to be rented

        controller.handleRentCarClick();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        assertEquals("You are signed in", alert.getContentText());
    }

    @Test
    public void testHandleRentCarClick_NotSignedIn() throws IOException, SQLException {
        controller.isSignedIn = false; // User is not signed in
        controller.car = car; // Set the car to be rented

        controller.handleRentCarClick();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        assertEquals("You have to sign in/register in order to rent a car", alert.getContentText());
    }

}