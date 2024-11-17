package com.example.projectdemo.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Rental {
    String rentalID, customerID, carID, locationID;
    LocalDate rentalDate;
    LocalDate returnDate;
    int registration_id;
    private Car car;
    CarDAO carDAO = new CarDAO("car");
    boolean status;
    public Rental(){}
    public Rental(String rentalID, LocalDate rentalDate, LocalDate returnDate, String carID, String locationID, int registration_id) throws SQLException {
        this.rentalID = rentalID;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.carID = carID;
        this.locationID = locationID;
        this.registration_id = registration_id;
        this.car = carDAO.getCarById(carID);
    }

    public void setRentalID(String rentalID){
        this.rentalID = rentalID;
    }
    public void setRentalDate(LocalDate rentalDate){
        this.rentalDate = rentalDate;
    }
    public void setReturnDate(LocalDate returnDate){
        this.returnDate = returnDate;
    }
    public void setCar(Car car){this.car = car;}

    @Override
    public String toString(){
        return
            "RentalID: " + rentalID + "Car: " + car.getMake() + " , Start Date: " + rentalDate + ", Return Date: " + returnDate;
    }

}
