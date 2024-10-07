package com.example.projectdemo.controller;
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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;


public class PaymentController implements javafx.fxml.Initializable {

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
    public void initialize(URL location, ResourceBundle resources) {
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
        int selectedYear = yearSpinner.getValue();

        // Validate the selected year
        if (selectedYear < 24) {
            // Show an error alert if the card is expired
            showAlert("Invalid Card", "The card has expired. Please use a valid card.");
            return;
        }

        // Proceed with further payment logic if the card is valid
        // Add your payment processing code here
    }

    @FXML
    public void handleBackToCarPage(ActionEvent event) {
        try {
            // Close the current stage
            Stage currentStage = (Stage) mainPane.getScene().getWindow();
            currentStage.close();

            // Load Scene1.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/CarPage.fxml"));
            Parent layout = fxmlLoader.load();  // Load the FXML layout
            Scene scene = new Scene(layout);
            Stage carPageStage = new Stage();
            carPageStage.setScene(scene);
            carPageStage.setTitle("Car Page");
            carPageStage.show();  // Show the car page
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
