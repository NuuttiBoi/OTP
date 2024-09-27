package com.example.projectdemo.model;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private String name, address, id;
    private List<Car> carList = new ArrayList<>();

    public Location(String id, String name, String address, List<Car> carList){
        this.id = id;
        this.name = name;
        this.address = address;
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
