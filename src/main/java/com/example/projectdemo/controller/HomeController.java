package com.example.projectdemo.controller;

// Kontrolleri aloituissivulle
// täällä pitäisi valita mistä sijainnista käyttäjä haluaa vuokrata auton
// + sisäänkirjautuminen / rekisteröinti

import com.example.projectdemo.model.Location;
import com.example.projectdemo.model.LocationDAO;
import com.example.projectdemo.view.Gui;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    public ListView<Location> locationList;
    private List<Location> locations = new ArrayList<>();
    private LocationDAO locationDAO = new LocationDAO("locationDao");

    public void initialize(){
        locations = locationDAO.getLocationList();
        locationList.getItems().addAll(locations);
    }

    // Käyttäjä valitsee täällä sijainnin, jonka perusteella tulisi avata ikkuna,
    // jossa näkyy kyseisessä sijainnissa saatavilla olevat autot.

    // pitäisi ehkä tehdä oma table joka yhdistää rentallocationit vehiclesiin


}
