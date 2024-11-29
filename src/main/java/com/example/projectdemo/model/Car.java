package com.example.projectdemo.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class for creating a car.
 */
public class Car {
    private String id;
    private String make;
    private String model;
    private String licensePlate;
    private int year;
    private int kmDriven;
    private boolean isAvailable;
    private double price;
    private String location;
    private LocalDateTime currentDate = LocalDateTime.now();
    private LocalDate rentalEndDate;
    private CarDao carDao = new CarDao();

    /**
     * Default constructor.
     */
    public Car(){}

    /**
     * Parameterized constructor.
     */
    public Car(String id, String make, String model, int year, String licensePlate, boolean isAvailable, double price,
               String location, int kmDriven) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.isAvailable = isAvailable;
        this.price = price;
        this.location = location;
        this.kmDriven = kmDriven;
    }

    /**
     * Set the car to be available to rent.
     */
    public void setAvailable(boolean selection) {
        isAvailable = selection;
    }

    /**
     * Set the car to be unavailable.
     */
    public void setRented(LocalDate date) throws SQLException {
        this.rentalEndDate = date;
        if (currentDate.isBefore(date.atStartOfDay())){
            isAvailable = false;
            carDao.setAvailability(id);
        }
    }

    public void checkAvailability() throws SQLException {
        // Automatically make the car available if the rental period has ended
        if (rentalEndDate != null && LocalDateTime.now().isAfter(rentalEndDate.atStartOfDay())) {
            isAvailable = true; // Mark as available
            rentalEndDate = null; // Clear the rental end date
            carDao.setAvailabilityYes(id); // Update the availability in the database
        }
    }


    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getId() {
        return id;
    }

    public int getKmDriven() {
        return kmDriven;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String toString() {
        return make + " " + model + " (" + year + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return !super.equals(obj);
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
