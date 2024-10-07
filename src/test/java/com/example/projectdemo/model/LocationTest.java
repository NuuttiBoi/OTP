package com.example.projectdemo.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    private Location location;

    @BeforeEach
    public void setUp() {
        // Initialize the Location object before each test
        Car car1 = new Car("1", "Toyota", "Corolla", 2020, "ABC123", true, 25000.0, "Location1", 15000);
        Car car2 = new Car("2", "Honda", "Civic", 2021, "XYZ789", true, 30000.0, "Location2", 5000);
        location = new Location("1", "Main Office", "123 Main St", Arrays.asList(car1, car2));
    }

    @Test
    public void testLocationInitialization() {
        assertEquals("Main Office", location.getName());
        assertEquals("123 Main St", location.getAddress());
        assertEquals("1", location.getId());
    }

    @Test
    public void testToString() {
        String expectedString = "Main Office 123 Main St";
        assertEquals(expectedString, location.toString());
    }

    @Test
    public void testCarListInitialization() {
        // Testing if the car list is initialized correctly
        assertEquals(2, location.carList.size());
    }


}