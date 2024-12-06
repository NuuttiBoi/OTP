package com.example.projectdemo.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RentalTest {

    @BeforeEach
    void setUp(){
        Locale locale = new Locale("en","US");
        LanguageManager.setLocale(locale);
    }


    @Test
    void testToString() throws SQLException {
        LocalDate startDate = LocalDate.of(2024,12,2);
        LocalDate endDate = LocalDate.of(2024,12,20);
        String carID = "C1";
        Rental rental = new Rental();
        rental.setRentalID(carID);
        rental.setRentalDate(startDate);
        rental.setReturnDate(endDate);

        Car car = new Car();
        car.setMake("Toyota");
        rental.setCar(car);

        String result = rental.toString();
        String expected = "RentalID: C1Car: Toyota , Start Date: 2024-12-02, Return Date: 2024-12-20";
        assertEquals(expected, result);
    }
}