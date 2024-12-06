package com.example.projectdemo.controller;

import com.example.projectdemo.model.LanguageManager;
import com.example.projectdemo.model.RegistrationDao;
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

    static ResourceBundle bundle = LanguageManager.getResourceBundle();

    /**
     * Initializes the controller.
     */
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

    /**
     * Handles user registration.
     */
    @FXML
    public void register(ActionEvent event) throws SQLException, IOException {

        Window owner = submitButton.getScene().getWindow();
        if (fullNameField.getText().isEmpty() || (fullNameField.getText().matches(".*\\d.*"))) {
            showAlert(Alert.AlertType.ERROR, owner, bundle.getString("alertErrorTitle"),
                    bundle.getString("alertErrorName"));
            return;
        }

        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, bundle.getString("alertErrorTitle"),
                    bundle.getString("alertErrorEmail"));
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, bundle.getString("alertErrorTitle"),
                    bundle.getString("alertErrorPassword"));
            return;
        }

        String fullName = fullNameField.getText();
        String emailId = emailIdField.getText();
        String password = passwordField.getText();

        RegistrationDao jdbcDao = new RegistrationDao();
        jdbcDao.insertRecord(fullName, emailId, password);

        showAlert(Alert.AlertType.CONFIRMATION, owner, bundle.getString("registrationSuccessful"),
                bundle.getString("welcomeText") + fullNameField.getText());

        // Close the current login stage
        Stage currentStage = (Stage) submitButton.getScene().getWindow();
        currentStage.close();

        // Load the StartPage.fxml page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/StartPage.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout
        Scene scene = new Scene(layout, 300, 600);
        Stage startStage = new Stage();
        startStage.setScene(scene);
        startStage.setTitle("Start Page");
        startStage.show();  // Show the start page

    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(ButtonType.OK);
        Button button = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        button.setText(bundle.getString("okButton"));
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(LoginController.class.getResource("/com/example/projectdemo/logo.png").toExternalForm()));
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
