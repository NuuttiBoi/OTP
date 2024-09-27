package com.example.projectdemo.controller;

// Kontrolleri aloituissivulle
// täällä pitäisi valita mistä sijainnista käyttäjä haluaa vuokrata auton
// + sisäänkirjautuminen / rekisteröinti

import com.example.projectdemo.model.Car;
import com.example.projectdemo.model.Location;
import com.example.projectdemo.model.LocationDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    public ListView<Location> locationList;
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
                    openScene1(selectedLocation);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void openScene1(Location selectedLocation) throws IOException{
        Stage scene1 = new Stage();
        scene1.setTitle("scene1");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/Scene1.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout
        Controller controller = fxmlLoader.getController();  // Make sure to import your controller class
        controller.initialize(selectedLocation.getId());

        // Create the scene and set it to the stage
        Scene scene = new Scene(layout, 600, 500);
        scene.getStylesheets().add(getClass().getResource("/com/example/projectdemo/scene2_style.css").toExternalForm());
        scene1.setScene(scene);

        // Set the new window as a modal window
        scene1.initModality(Modality.APPLICATION_MODAL);
        scene1.showAndWait();  // Show the window and wait for it to be closed
    }

    // Käyttäjä valitsee täällä sijainnin, jonka perusteella tulisi avata ikkuna,
    // jossa näkyy kyseisessä sijainnissa saatavilla olevat autot.
    // pitäisi ehkä tehdä oma table joka yhdistää rentallocationit vehiclesiin


}
