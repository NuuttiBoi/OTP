
package com.example.projectdemo.controller;

import com.example.projectdemo.model.LanguageManager;
import com.example.projectdemo.model.SessionManager;
import com.example.projectdemo.model.User;
import com.example.projectdemo.model.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller for the login page.
 */
public class LoginController {

    @FXML
    public Label passwordText;
    @FXML
    public Text noAccountText;
    @FXML
    public Label emailText;
    @FXML
    public Label headerText;
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
    static ResourceBundle bundle = LanguageManager.getResourceBundle();


    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {

        // Load the car logo image
        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        logo.setImage(carLogo);

        mainPane.setBackground(new Background(new BackgroundFill(Color.web("#21283d"), CornerRadii.EMPTY, Insets.EMPTY)));

        passwordText.setText(bundle.getString("passwordText"));
        noAccountText.setText(bundle.getString("no_account"));
        emailText.setText(bundle.getString("emailText"));
        registerButton.setText(bundle.getString("register"));
        submitButton.setText(bundle.getString("submit"));
        headerText.setText(bundle.getString("loginHeader"));
    }

    /**
     * Handles user login.
     */
    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {
        Window owner = submitButton.getScene().getWindow();
        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, bundle.getString("alertErrorTitle"), bundle.getString("alertErrorEmail"));
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, bundle.getString("alertErrorTitle"),
                    bundle.getString("alertErrorPassword"));
            return;
        }

        String emailId = emailIdField.getText();
        String password = passwordField.getText();

        UserDao userDao = new UserDao();
        User user = userDao.validate(emailId, password);
        // Example login process

        if (user == null) {
            infoBox(bundle.getString("emptyError"), null, bundle.getString("alertErrorTitle"));
        } else {
            // Show the success message and wait for user acknowledgment
            infoBox(bundle.getString("success"), null, "");
            User loggedInUser = userDao.validate(emailId, password); // Assuming userService handles authentication
            if (loggedInUser != null) {
                SessionManager.login(loggedInUser);
                // Navigate to the main application scene or dashboard
            }
            // Close the current login stage
            Stage currentStage = (Stage) submitButton.getScene().getWindow();
            currentStage.close();

            // Load the StartPage.fxml page
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/StartPage.fxml"));
            Parent layout = fxmlLoader.load();  // Load the FXML layout
            Scene scene = new Scene(layout);
            Stage startStage = new Stage();
            startStage.setScene(scene);

            startStage.setTitle("Start Page");
            startStage.show();  // Show the start page
        }
    }

    /**
     * Displays the confirmation of login.
     */
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(ButtonType.OK);
        Button button = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        button.setText(bundle.getString("okButton"));
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(LoginController.class.getResource("/com/example/projectdemo/logo.png").toExternalForm()));
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(LoginController.class.getResource("/com/example/projectdemo/logo.png").toExternalForm()));
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/RegistrationPage.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout
        Scene scene = new Scene(layout, 300, 600);
        Stage register = new Stage();
        register.setScene(scene);
        register.setTitle("Registration Page");
        register.show();  // Show the registration page
    }
}
