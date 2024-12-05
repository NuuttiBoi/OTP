package com.example.projectdemo.controller;

import com.example.projectdemo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for the "rentals page". Here the user can view their current rentals.
 * @author Nuutti Turunen
 */
public class RentalsController {
    public Text myRentalsHeader;
    @FXML
    private ListView rentalList;
    private List<Rental> rentals = new ArrayList<>();
    @FXML
    public ImageView logo;

    /**
     * Initializes the controller.
     */
    public void initialize() throws SQLException {
        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        logo.setImage(carLogo);

        ResourceBundle bundle = LanguageManager.getResourceBundle();
        myRentalsHeader.setText(bundle.getString("rentalsHeader"));
        User user1 = SessionManager.getCurrentUser();
        System.out.println(user1.toString());
        System.out.println(user1.getUserID());
        RentalDao rentalDAO = new RentalDao();
        rentals = rentalDAO.getRentals(user1.getUserID());

        rentalList.getItems().addAll(rentals);

    }

}
