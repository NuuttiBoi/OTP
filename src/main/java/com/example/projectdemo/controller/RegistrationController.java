package com.example.projectdemo.controller;

import com.example.projectdemo.model.LanguageManager;
import com.example.projectdemo.model.RegistrationDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Window;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Controller for the registration page.
 */
public class RegistrationController {

    @FXML
    public ImageView logo;
    @FXML
    public Label registrationHeader;
    @FXML
    public Label emailText;
    @FXML
    public Label passwordText;
    @FXML
    public Label nameText;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Pane mainPane;
    ResourceBundle bundle = LanguageManager.getResourceBundle();

    @FXML
    public void initialize() {
        registrationHeader.setText(bundle.getString("register"));
        nameText.setText(bundle.getString("nameText"));
        emailText.setText(bundle.getString("emailText"));
        submitButton.setText(bundle.getString("submit"));
        passwordText.setText(bundle.getString("passwordText"));

        // Load the car logo image
        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        logo.setImage(carLogo);

        // Set the background color of the main pane to blue
        mainPane.setBackground(new Background(new BackgroundFill(Color.web("#21283d"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    public void register(ActionEvent event) throws SQLException, IOException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(fullNameField.getText());
        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());

        if (fullNameField.getText().isEmpty() || (fullNameField.getText().matches(".*\\d.*"))) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }

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

        String fullName = fullNameField.getText();
        String emailId = emailIdField.getText();
        String password = passwordField.getText();

        RegistrationDAO jdbcDao = new RegistrationDAO();
        jdbcDao.insertRecord(fullName, emailId, password);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + fullNameField.getText());

        // Close the current login stage
        //currentStage.close();

        // Load the Start.fxml page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/Start.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout
        Scene scene = new Scene(layout, 300, 600);
        Stage startStage = new Stage();
        startStage.setScene(scene);
        startStage.setTitle("Start Page");
        startStage.show();  // Show the start page

    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
