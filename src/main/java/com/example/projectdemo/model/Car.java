package com.example.projectdemo.model;

public class Car {
    private String make, model;
    private int year;
    private boolean isAvailable;
    public Car(String make, String model, int year){
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake(){
        return this.make;
    }
    public String getModel(){
        return this.model;
    }
    public int getYear(){return this.year;}

    @Override
    public String toString() {
        return make + " " + model + " (" + year + ")";
    }
}
