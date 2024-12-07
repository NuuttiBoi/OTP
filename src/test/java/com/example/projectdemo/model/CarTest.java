package com.example.projectdemo.model;

import com.example.projectdemo.controller.WelcomePageController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private Car car;

    @BeforeEach
    public void setUp() {
        Locale locale = new Locale("en","US");
        LanguageManager.setLocale(locale);
        car = new Car("1", "Toyota", "Corolla", 2020, "ABC123", true, 25000.0, "Location", 15000);
    }

    @Test
    public void testCarInitialization() {
        assertEquals("Toyota", car.getMake());
        assertEquals("Corolla", car.getModel());
        assertEquals(2020, car.getYear());
        assertEquals("ABC123", car.getLicensePlate());
        assertTrue(car.isAvailable());
        assertEquals(25000.0, car.getPrice());
        assertEquals("1", car.getId());
        assertEquals(15000, car.getKmDriven());
    }

    @Test
    public void testSetAvailable() {
        car.setAvailable(false);
        assertFalse(car.isAvailable());
    }


    @Test
    public void testToString() {
        String expectedString = "Toyota Corolla (2020)";
        assertEquals(expectedString, car.toString());
    }



    @Test
    public void testHashCode() {
        assertEquals(car.getId().hashCode(), car.hashCode());
    }
}