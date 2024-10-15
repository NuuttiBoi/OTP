package com.example.projectdemo.controller;

import com.example.projectdemo.model.Rental;
import com.example.projectdemo.model.RentalDAO;
import com.example.projectdemo.model.SessionManager;
import com.example.projectdemo.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalsController {
    @FXML
    private ListView rentalList;
    private List<Rental> rentals = new ArrayList<>();

    public void initialize() throws SQLException {
        User user1 = SessionManager.getCurrentUser();
        System.out.println(user1.toString());
        System.out.println(user1.getUserID());
        RentalDAO rentalDAO = new RentalDAO();
        rentals = rentalDAO.getRentals(user1.getUserID());

        rentalList.getItems().addAll(rentals);

    }

}
