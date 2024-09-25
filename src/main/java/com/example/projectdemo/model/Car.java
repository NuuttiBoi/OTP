package com.example.projectdemo.model;

public class Car {
    private String id, make, model, licensePlate;
    private int year;
    private boolean isAvailable;
    private double price;

    public Car(String id, String make, String model, int year, String licensePlate, boolean isAvailable, double price) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    // Getters for all fields
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getId() { return id; }
    public String getLicensePlate() { return licensePlate; }
    public boolean isAvailable() { return isAvailable; }
    public double getPrice() { return price; }

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
