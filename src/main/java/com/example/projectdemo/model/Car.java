package com.example.projectdemo.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Car {
    private String id, make, model, licensePlate;
    private int year, km_driven;
    private boolean isAvailable;
    private double price;
    String location;
    private LocalDateTime currentDate = LocalDateTime.now();
    private CarDAO car = new CarDAO("yhteys");

    public Car(String id, String make, String model, int year, String licensePlate, boolean isAvailable, double price,
               String location, int km_driven) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.isAvailable = isAvailable;
        this.price = price;
        this.location = location;
        this.km_driven = km_driven;
    }

    public void setAvailable(boolean selection){
        isAvailable = selection;
    }
    public void setRented(LocalDateTime date) throws SQLException {
        while (currentDate.isBefore(date)){
            isAvailable = false;
            car.setAvailability(id);
        }
        isAvailable = true;
    }

    // Getters for all fields
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getId() { return id; }
    public int getKm_driven(){return km_driven;}
    public String getLicensePlate() { return licensePlate; }
    public boolean isAvailable() { return isAvailable; }
    public double getPrice() { return price; }
    public boolean getIsAvailable(){return isAvailable; }

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
