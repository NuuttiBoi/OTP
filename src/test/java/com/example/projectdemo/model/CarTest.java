package com.example.projectdemo.model;

import org.junit.jupiter.api.Test;
import com.example.projectdemo.model.Car;
import com.example.projectdemo.model.CarDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private Car car;

    @BeforeEach
    public void setUp() {
        // Initialize the Car object before each test
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
        assertEquals(15000, car.getKm_driven());
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