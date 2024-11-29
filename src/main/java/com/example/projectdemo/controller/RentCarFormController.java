package com.example.projectdemo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Controller for renting a car.
 */
public class RentCarFormController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;

    public void handleSubmit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Rental confirmed");
        alert.showAndWait();

        String name = nameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();

        // You can add validation logic here
        if (name.isEmpty() || email.isEmpty() || address.isEmpty()) {
            System.out.println("All fields must be filled out.");
        } else {
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Address: " + address);
            // Process the submitted information here (e.g., save to database)
        }

    }
}
