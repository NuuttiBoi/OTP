package com.example.projectdemo.controller;

// Kontrolleri aloituissivulle
// täällä pitäisi valita mistä sijainnista käyttäjä haluaa vuokrata auton
// + sisäänkirjautuminen / rekisteröinti
import com.example.projectdemo.model.UserDAO;
import com.example.projectdemo.model.Car;
import com.example.projectdemo.model.Location;
import com.example.projectdemo.model.LocationDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    public ListView<Location> locationList;
    public Button signInButton;
    @FXML
    public DatePicker startDatePicker;
    @FXML
    public DatePicker returnDatePicker;
    @FXML
    public ImageView logo;
    @FXML
    private Pane mainPane;
    @FXML
    public ImageView tokyo;
    @FXML
    public Text signedInText;
    private LocalDate startDate;
    private LocalDate returnDate;
    private boolean isSignedIn = false;
    private List<Location> locations = new ArrayList<>();
    private LocationDAO locationDAO = new LocationDAO("locationDao");
    private List<Car> carList = new ArrayList<Car>();

    public void initialize() {
        if (isSignedIn) {
            signedInText.setText("You are signed in.");
        }
        locations = locationDAO.getLocationList();
        locationList.setCellFactory(lv -> new javafx.scene.control.ListCell<Location>() {
            @Override
            protected void updateItem(Location location, boolean empty) {
                super.updateItem(location, empty);
                if (empty || location == null) {
                    setText(null);
                } else {
                    setText(location.getName());
                }
            }
        });

        mainPane.setBackground(new Background(new BackgroundFill(Color.web("#21283d"), CornerRadii.EMPTY, Insets.EMPTY)));
        locationList.getItems().addAll(locations);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        locationList.setOnMouseClicked(event -> {
            Location selectedLocation = locationList.getSelectionModel().getSelectedItem();
            if (selectedLocation != null) {
//                if (!UserDAO.isLoggedIn()) { // Check if the user is signed in
//                    alert.setContentText("Please log in to continue.");
//                    alert.showAndWait();
//                    return; // Exit the method if not signed in
//                }

                // The rest of your existing code...
                if (startDate == null || returnDate == null) {
                    alert.setContentText("Please select the dates.");
                    alert.showAndWait();
                } else if (returnDate.isBefore(startDate)) {
                    alert.setContentText("Return date can't be before start date.");
                    alert.showAndWait();
                } else {
                    try {
                        openScene1(selectedLocation, returnDate);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        logo.setImage(carLogo);
    }

    private void openScene1(Location selectedLocation, LocalDate returnDate) throws IOException {
        Stage scene1 = new Stage();
        scene1.setTitle("scene1");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/Scene1.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout
        Controller controller = fxmlLoader.getController();  // Make sure to import your controller class
        controller.initialize(selectedLocation.getId(), startDate, returnDate);

        Scene scene = new Scene(layout, 300, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/projectdemo/style.css").toExternalForm());
        scene1.setScene(scene);

        scene1.initModality(Modality.APPLICATION_MODAL);
        scene1.showAndWait();
    }

    public void onSignInButtonClicked() throws IOException {
        // Get the current stage (the one with the home page)
        Stage currentStage = (Stage) signInButton.getScene().getWindow();

        // Close the current stage
        currentStage.close();

        // Load the login page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/LoginController.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout

        // Create a new stage for the login page
        Stage loginStage = new Stage();
        Scene scene = new Scene(layout, 300, 600);
        loginStage.setScene(scene);
        loginStage.setTitle("Login Page");

        // Show the login page
        loginStage.show();
    }

    public void handleStartDate() {
        startDate = startDatePicker.getValue();
        System.out.println(startDatePicker.getValue());
    }

    public void handleReturnDate() {
        returnDate = returnDatePicker.getValue();
        System.out.println(returnDatePicker.getValue());
    }

    public void setSignedIn() {
        isSignedIn = true;
        signInButton.setVisible(false);
        signedInText.setText("You are signed in."); // Update the text to reflect the sign-in status
    }

    public boolean isSignedIn() {
        return isSignedIn;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    // Käyttäjä valitsee täällä sijainnin, jonka perusteella tulisi avata ikkuna,
    // jossa näkyy kyseisessä sijainnissa saatavilla olevat autot.
    // pitäisi ehkä tehdä oma table joka yhdistää rentallocationit vehiclesiin
}
