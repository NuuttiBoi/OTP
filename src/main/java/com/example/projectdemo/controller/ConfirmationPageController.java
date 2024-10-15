package com.example.projectdemo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmationPageController {
    @FXML
    private Button homeButton;

public void handleBackToHome(ActionEvent event) {
    try {
        // Load the home page FXML (replace 'HomePage.fxml' with the actual file path)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/Start.fxml"));
        Parent homePageRoot = fxmlLoader.load();

        // Get the current stage
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        stage.setScene(new Scene(homePageRoot));
        stage.setTitle("Home Page");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
