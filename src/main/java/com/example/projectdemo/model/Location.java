package com.example.projectdemo.model;

public class Location {
    private String name, address;

    public Location(String name, String address){
        this.name = name;
        this.address = address;
    }

    public String getName(){
        return this.name;
    }
    public String getAddress(){
        return this.address;
    }
    @Override public String toString(){
        return name + " " + address;
    }
}
