package com.example.projectdemo.controller;


import com.example.projectdemo.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Kontrolleri aloituissivulle.
 * täällä pitäisi valita mistä sijainnista käyttäjä haluaa vuokrata auton
 * + sisäänkirjautuminen / rekisteröinti.
 */

public class HomeController {
    public ListView<Location> locationList;
    public Button signInButton;
    @FXML
    public DatePicker startDatePicker;
    @FXML
    public DatePicker returnDatePicker;
    @FXML
    public ImageView logo;
    public MenuButton menuButton;
    public MenuItem menuOption1;
    @FXML
    public Text startDateText;
    public Text endDateText;
    public Text locationInstructionText;
    public Text dateInstructionText;
    public Text headerText;
    @FXML
    private Pane mainPane;
    @FXML
    private LocalDate startDate;
    private LocalDate returnDate;
    private List<Location> locations = new ArrayList<>();
    private LocationDao locationDao = new LocationDao("locationDao");
    private ResourceBundle bundle;

    /**
     * Initializes the controller.
     */
    public void initialize() {
        bundle = LanguageManager.getResourceBundle();
        startDateText.setText(bundle.getString("startDate"));
        endDateText.setText(bundle.getString("endDate"));
        locationInstructionText.setText(bundle.getString("locationInstruction"));
        dateInstructionText.setText(bundle.getString("dateInstruction"));
        menuButton.setText(bundle.getString("menuButton"));
        menuOption1.setText(bundle.getString("menuOption"));

        // This sets the datepicker to adapt to the language that the user has selected
        Locale.setDefault(bundle.getLocale());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d.MM.uuuu",bundle.getLocale());
        startDatePicker.setValue(LocalDate.now());
        handleStartDate();
        startDatePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                return object.format(formatter);
            }

            @Override
            public LocalDate fromString(String string) {
                return LocalDate.parse(string, formatter);
            }
        });
        returnDatePicker.setValue(LocalDate.now());
        handleReturnDate();
        returnDatePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                return object.format(formatter);
            }

            @Override
            public LocalDate fromString(String string) {
                return LocalDate.parse(string, formatter);
            }
        });

        startDatePicker.setShowWeekNumbers(false);
        returnDatePicker.setShowWeekNumbers(false);
        locations = locationDao.getLocationList(bundle);
        locationList.setCellFactory(lv -> new javafx.scene.control.ListCell<Location>() {
            @Override
            protected void updateItem(Location location, boolean empty) {
                super.updateItem(location, empty);
                if (empty || location == null) {
                    setText(null);
                    setGraphic(null);
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
                if (startDate == null || returnDate == null) {
                    alert.setContentText(bundle.getString("dateInstruction"));
                    alert.showAndWait();
                    // Check if the selected dates are valid
                } else if (returnDate.isBefore(startDate) || startDate.isBefore(ChronoLocalDate.from(java.time.ZonedDateTime.now()))) {
                    alert.setContentText(bundle.getString("startBeforeEndError"));
                    alert.showAndWait();
                    System.out.println(ChronoLocalDate.from(java.time.ZonedDateTime.now()));
                } else {
                    try {
                        openScene1(selectedLocation, returnDate);
                        SessionManager.setLocation(selectedLocation);
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
        Stage currentStage = (Stage) mainPane.getScene().getWindow();
        currentStage.close();
        Stage scene1 = new Stage();
        scene1.setTitle("scene1");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/CarListPage.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout
        CarListPageController carListPageController = fxmlLoader.getController();  // Make sure to import your controller class
        carListPageController.initialize(selectedLocation.getId(), startDate, returnDate);
        Scene scene = new Scene(layout, 300, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/projectdemo/style.css").toExternalForm());
        scene1.setScene(scene);

        scene1.initModality(Modality.APPLICATION_MODAL);
        scene1.showAndWait();
    }

    /**
     * Fetches the selected startdate.
     */
    public void handleStartDate() {
        startDate = startDatePicker.getValue();
        System.out.println(startDatePicker.getValue());
        SessionManager.setStartDate(startDate);
    }

    /**
     * Fetches the selected return date.
     */
    public void handleReturnDate() {
        returnDate = returnDatePicker.getValue();
        System.out.println(returnDatePicker.getValue());
        SessionManager.setEndDate(returnDate);
    }

    /**
     * Opens the scene where user can view their current rentals.
     */
    public void handleMyRentals() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/UserRentalsPage.fxml"));
        Parent layout = fxmlLoader.load();
        Stage rentalsStage = new Stage();
        Scene scene = new Scene(layout, 600, 400);
        rentalsStage.setScene(scene);
        rentalsStage.show();
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }
}
