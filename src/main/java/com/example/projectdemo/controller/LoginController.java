
package com.example.projectdemo.controller;

import com.example.projectdemo.model.SessionManager;
import com.example.projectdemo.model.User;
import com.example.projectdemo.model.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField emailIdField;

    @FXML
    public ImageView logo;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Button registerButton;

    @FXML
    private Pane mainPane;

    private HomeController homeController = new HomeController();

    @FXML
    public void initialize() {
        // Load the car logo image
        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        logo.setImage(carLogo);

        mainPane.setBackground(new Background(new BackgroundFill(Color.web("#21283d"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {
        Window owner = submitButton.getScene().getWindow();

        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());

        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String emailId = emailIdField.getText();
        String password = passwordField.getText();

        UserDAO userDao = new UserDAO();
        User user = userDao.validate(emailId, password);
        // Example login process



        if (user == null) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            // Show the success message and wait for user acknowledgment
            infoBox("Login Successful!", null, "Success");
            User loggedInUser = userDao.validate(emailId, password); // Assuming userService handles authentication
            if (loggedInUser != null) {
                SessionManager.login(loggedInUser);
                // Navigate to the main application scene or dashboard
            }
            // Close the current login stage
            Stage currentStage = (Stage) submitButton.getScene().getWindow();
            currentStage.close();

            // Load the Start.fxml page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/Start.fxml"));
            Parent layout = fxmlLoader.load();  // Load the FXML layout
            Scene scene = new Scene(layout, 300, 600);
            Stage startStage = new Stage();
            startStage.setScene(scene);

            startStage.setTitle("Start Page");
            startStage.show();  // Show the start page
        }
    }


    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void handleRegisterButtonClick(ActionEvent actionEvent) throws IOException {
        // Get the current stage (the one with the login page)
        Stage currentStage = (Stage) registerButton.getScene().getWindow();

        // Close the current stage
        currentStage.close();

        // Load the registration page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/registrationController.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout
        Scene scene = new Scene(layout, 300, 600);
        Stage register = new Stage();
        register.setScene(scene);
        register.setTitle("Registration Page");
        register.show();  // Show the registration page
    }
}
