package com.example.projectdemo.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating a location.
 */
public class Location {
    private String name, address, id, image;
    List<Car> carList = new ArrayList<>();

    public Location(String id, String name, String address, List<Car> carList, String image){
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
        this.carList = carList;
    }

    public String getName(){
        return this.name;
    }
    public String getAddress(){
        return this.address;
    }
    public String getId(){return this.id;}
    @Override public String toString(){
        return name + " " + address;
    }

}
