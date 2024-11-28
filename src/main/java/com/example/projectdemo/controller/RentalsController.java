package com.example.projectdemo.controller;

import com.example.projectdemo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RentalsController {
    public Text myRentalsHeader;
    @FXML
    private ListView rentalList;
    private List<Rental> rentals = new ArrayList<>();

    public void initialize() throws SQLException {
        ResourceBundle bundle = LanguageManager.getResourceBundle();
        myRentalsHeader.setText(bundle.getString("rentalsHeader"));
        User user1 = SessionManager.getCurrentUser();
        System.out.println(user1.toString());
        System.out.println(user1.getUserID());
        RentalDAO rentalDAO = new RentalDAO();
        rentals = rentalDAO.getRentals(user1.getUserID());

        rentalList.getItems().addAll(rentals);

    }

}
