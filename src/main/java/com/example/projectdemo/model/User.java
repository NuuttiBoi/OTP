package com.example.projectdemo.model;

public class User {

    private int userID;
    private String email;
    private String role;
    private String type;
    private String rentalID, password;


    public User(int userID, String email, String type) {
        this.userID = userID;
        this.email = email;
        this.type = type;
    }

    public User() {
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRentalID() {
        return rentalID;
    }

    public void setRentalID(String rentalID) {
        this.rentalID = rentalID;
    }

    // ToString method for easy display
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", type='" + type + '\'' +
                ", rentalID='" + rentalID + '\'' +
                '}';
    }
}
