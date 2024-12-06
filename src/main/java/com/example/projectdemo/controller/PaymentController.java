package com.example.projectdemo.controller;
import com.example.projectdemo.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Controller for the payment page.
 */
public class PaymentController  {

    @FXML
    public Text paymentHeader;
    @FXML
    public Text cardNameText;
    @FXML
    public Text cardNumberText;
    @FXML
    public Text expDateText;
    @FXML
    public Text cardCvv;
    @FXML
    public AnchorPane rootPane;
    @FXML
    private Pane mainPane;

    @FXML
    private Spinner<Integer> monthSpinner;

    @FXML
    private Spinner<Integer> yearSpinner;

    @FXML
    private Hyperlink backToCarPageLink;

    @FXML
    private ImageView logo;

    @FXML
    private TextField cvvField;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField cardName;

    @FXML
    private Button submitButton;

    @FXML
    private Button paymentButton;

    private Car selectedCar;


    @FXML
    public void initialize() {
        ResourceBundle bundle = LanguageManager.getResourceBundle();
        //rootPane.requestLayout(); // Force layout pass

        cardNameText.setText(bundle.getString("cardName"));
        cardNumberText.setText(bundle.getString("cardNumber"));
        cardCvv.setText(bundle.getString("cardCvv"));
        paymentHeader.setText(bundle.getString("paymentHeader"));
        paymentButton.setText(bundle.getString("paymentButton"));
        backToCarPageLink.setText(bundle.getString("backToCarPageLink"));

        // Set up the month spinner to display values from 1 to 12
        SpinnerValueFactory<Integer> monthFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12);
        monthSpinner.setValueFactory(monthFactory);

        // Set up the year spinner to display values starting from 24 onwards
        ObservableList<Integer> years = FXCollections.observableArrayList();
        for (int i = 24; i <= 99; i++) {
            years.add(i);
        }
        SpinnerValueFactory<Integer> yearFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(years);
        yearSpinner.setValueFactory(yearFactory);

        // Limit card name input to letters only
        cardName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z ]*")) {
                cardName.setText(oldValue);
            }

        });
        // Limit card number input to a maximum of 16 digits and format with spaces
        cardNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            String formatted = newValue.replaceAll("[^\\d]", "");
            if (formatted.length() > 16) {
                formatted = formatted.substring(0, 16);
            }
            formatted = formatted.replaceAll("(.{4})", "$1 ").trim();
            cardNumber.setText(formatted);
        });

/// Set up the card number input with formatting
        cardNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            // Remove all non-digit characters
            String digitsOnly = newValue.replaceAll("\\D", "");

            // Limit to a maximum of 16 digits
            if (digitsOnly.length() > 16) {
                digitsOnly = digitsOnly.substring(0, 16);
            }

            // Format with spaces
            StringBuilder formatted = new StringBuilder();
            for (int i = 0; i < digitsOnly.length(); i++) {
                if (i > 0 && i % 4 == 0) {
                    formatted.append(" "); // Add a space every 4 digits
                }
                formatted.append(digitsOnly.charAt(i));
            }

            // Set the new formatted value
            cardNumber.setText(formatted.toString());

            // Move the cursor to the end of the text
            cardNumber.positionCaret(cardNumber.getText().length());
        });

        // Load the car logo image
        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        logo.setImage(carLogo);

        // Set the background color for the pane
        mainPane.setBackground(new Background(new BackgroundFill(Color.web("#21283d"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Limit CVV field input to a maximum of 3 digits
        cvvField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}")) {
                cvvField.setText(oldValue);
            }
        });
    }

    @FXML
    public void handlePayment(ActionEvent event) {
        String cardNameValue = cardName.getText();
        String cardNumberValue = cardNumber.getText().replace(" ", ""); // Remove spaces for validation
        String cvvValue = cvvField.getText();
        int selectedYear = yearSpinner.getValue();

        // Check if any field is empty
        if (cardNameValue.isEmpty() || cardNumberValue.isEmpty() || cvvValue.isEmpty()) {
            showAlert("Incomplete Information", "Please fill in all the required fields.");
            return;
        }

        // Validate card name (must contain at least one space)
        if (!cardNameValue.contains(" ")) {
            showAlert("Invalid Name", "Please enter your full name (first and last name).");
            return;
        }

        // Validate card number (must be 16 digits)
        if (cardNumberValue.length() != 16) {
            showAlert("Invalid Card Information", "Please enter a valid 16-digit card number.");
            return;
        }

        // Validate CVV (must be 3 digits)
        if (cvvValue.length() != 3) {
            showAlert("Invalid Card Information", "Please enter a 3-digit CVV.");
            return;
        }

        // Validate the selected year
        if (selectedYear < 24) {
            showAlert("Invalid Card", "The card has expired. Please use a valid card.");
            return;
        }

        // If all validations pass, proceed to payment confirmation
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/OrderConfirmationPage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) paymentButton.getScene().getWindow(); // Get the current stage
            stage.getIcons().add(new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm()));
            stage.setScene(new Scene(root)); // Set the scene to OrderConfirmationPage.fxml
            stage.show(); // Display the confirmation page

            RentalDao rentalDAO = new RentalDao();
            rentalDAO.addRental("2",String.valueOf(SessionManager.getStartDate()),String.valueOf(SessionManager.getEndDate()),
                    selectedCar.getId(), SessionManager.getLocation().getId(),SessionManager.getCurrentUser().getUserID());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBackToCarPage(ActionEvent event) throws IOException{
        Stage currentStage = (Stage) paymentButton.getScene().getWindow();
        currentStage.close();
    }



    public void setSelectedCar(Car selectedCar){
        this.selectedCar = selectedCar;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(LoginController.class.getResource("/com/example/projectdemo/logo.png").toExternalForm()));
        alert.setTitle(title);
        alert.setHeaderText(null);
        //alert.setContentText(user.toString());
        alert.showAndWait();
    }
}
