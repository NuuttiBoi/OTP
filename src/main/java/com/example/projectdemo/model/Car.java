package com.example.projectdemo.model;

public class Car {
    private String make, model;
    private int year;
    private String id, location;
    private boolean isAvailable;
    public Car(String id,String make, String model, int year, String location){
        this.make = make;
        this.model = model;
        this.year = year;
        this.id = id;
        this.location = location;
    }

    public String getMake(){
        return this.make;
    }
    public String getModel(){
        return this.model;
    }
    public int getYear(){return this.year;}
    public String getId(){return this.id;}

    @Override
    public String toString() {
        return make + " " + model + " (" + year + ")";
    }


    // varmistaa ettei HashSettiin tule duplikaatteja
    @Override
    public boolean equals(Object obj) {
        return !super.equals(obj);
    }
    @Override
    public int hashCode(){
        return getId().hashCode();
    }
}
