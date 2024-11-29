package com.example.projectdemo.model;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Class for creating a rental.
 */
public class Rental {
    String rentalID, carId, locationId;
    LocalDate rentalDate;
    LocalDate returnDate;
    int registration_id;
    private Car car;
    CarDao carDAO = new CarDao();

    /**
     * Default constructor.
     */
    public Rental(){}

    /**
     * Parameterized constructor.
     */
    public Rental(String rentalID, LocalDate rentalDate, LocalDate returnDate, String carID, String locationID, int registration_id) throws SQLException {
        this.rentalID = rentalID;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.carId = carID;
        this.locationId = locationID;
        this.registration_id = registration_id;
        this.car = carDAO.getCarById(carID);
    }

    public void setRentalID(String rentalID) {
        this.rentalID = rentalID;
    }
    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return
            "RentalID: " + rentalID + "Car: " + car.getMake() + " , Start Date: " + rentalDate + ", Return Date: " + returnDate;
    }

}
