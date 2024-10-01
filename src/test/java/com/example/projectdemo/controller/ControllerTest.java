package com.example.projectdemo.controller;

import com.example.projectdemo.model.Car;
import javafx.application.Application;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ControllerTest {
    private Controller controller;
    private ListView<Car> carList;
    private List<Car> list;

    @BeforeEach
    public void setUp() {
        // Initialize the controller and the list of cars
        controller = new Controller();
        controller.carList = new ListView<>();
        controller.searchField = new TextField(); // Assuming this is a simple text field
        list = new ArrayList<>();

        // Adding test cars to the list
        list.add(new Car("1", "Toyota", "Camry", 2020, "ABC123", true, 50.0, "New York", 15000));
        list.add(new Car("2", "Honda", "Accord", 2019, "DEF456", true, 55.0, "Los Angeles", 20000));
        list.add(new Car("3", "Toyota", "Corolla", 2021, "GHI789", true, 40.0, "Chicago", 10000));
        list.add(new Car("4", "Ford", "Focus", 2018, "JKL012", true, 45.0, "Miami", 25000));

        // Assuming you have a way to set the list of cars in the controller
        controller.list = list;
    }

    @Test
    public void testFilterCarsByModel() {
        controller.filterCarsByModel("Toyota");

        // Get the filtered items in the ListView
        List<Car> items = controller.carList.getItems();

        // Verify that the ListView contains only Toyota cars
        assertEquals(2, items.size()); // Should contain 2 Toyotas
        assertEquals("1", items.get(0).getId()); // Check the first car's id
        assertEquals("3", items.get(1).getId()); // Check the second car's id
    }

    @Test
    public void testFilterCarsByModel_NoMatch() {
        controller.filterCarsByModel("BMW");

        // Get the filtered items in the ListView
        List<Car> items = controller.carList.getItems();

        // Verify that the ListView is empty
        assertEquals(0, items.size()); // Should contain 0 items
    }

    @Test
    public void testOnSearchButtonClick() {
        controller.searchField.setText("Toyota");
        controller.onSearchButtonClick();

        // Get the items in the ListView after searching
        List<Car> items = controller.carList.getItems();

        // Verify that the ListView contains only the Toyota cars
        assertEquals(2, items.size()); // Should contain 2 Toyotas
    }

}