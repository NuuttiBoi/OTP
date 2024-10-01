package com.example.projectdemo.controller;

// Kontrolleri aloituissivulle
// täällä pitäisi valita mistä sijainnista käyttäjä haluaa vuokrata auton
// + sisäänkirjautuminen / rekisteröinti

import com.example.projectdemo.model.Car;
import com.example.projectdemo.model.Location;
import com.example.projectdemo.model.LocationDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDate startDate;
    private LocalDate returnDate;
    private List<Location> locations = new ArrayList<>();
    private LocationDAO locationDAO = new LocationDAO("locationDao");
    private List<Car> carList = new ArrayList<Car>();
    private Controller controller = new Controller();

    public void initialize(){
        locations = locationDAO.getLocationList();
        locationList.getItems().addAll(locations);
        // Set event listener for mouse clicks on list items
        locationList.setOnMouseClicked(event -> {
            Location selectedLocation = locationList.getSelectionModel().getSelectedItem();
            if (selectedLocation != null) {
                // Call a method to open a new window when an item is clicked
                try {
                    openScene1(selectedLocation, returnDate);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        logo.setImage(carLogo);
    }

    private void openScene1(Location selectedLocation, LocalDate returnDate) throws IOException{
        Stage scene1 = new Stage();
        scene1.setTitle("scene1");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/Scene1.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout
        Controller controller = fxmlLoader.getController();  // Make sure to import your controller class
        controller.initialize(selectedLocation.getId(), returnDate);

        // Create the scene and set it to the stage
        Scene scene = new Scene(layout, 600, 500);
        scene.getStylesheets().add(getClass().getResource("/com/example/projectdemo/scene2_style.css").toExternalForm());
        scene1.setScene(scene);

        // Set the new window as a modal window
        scene1.initModality(Modality.APPLICATION_MODAL);
        scene1.showAndWait();  // Show the window and wait for it to be closed
    }

    public void onSignInButtonClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/login.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout
        Scene scene = new Scene(layout,600,600);
        Stage login = new Stage();
        login.setScene(scene);
        signInButton.setOnMouseClicked(event -> login.showAndWait());
    }

    public void handleStartDate(){
        startDate = startDatePicker.getValue();
        System.out.println(startDatePicker.getValue());
    }
    public void handleReturnDate(){
        returnDate = returnDatePicker.getValue();
        System.out.println(returnDatePicker.getValue());
    }
    public LocalDate getReturnDate(){
        return this.returnDate;
    }

    // Käyttäjä valitsee täällä sijainnin, jonka perusteella tulisi avata ikkuna,
    // jossa näkyy kyseisessä sijainnissa saatavilla olevat autot.
    // pitäisi ehkä tehdä oma table joka yhdistää rentallocationit vehiclesiin


}
