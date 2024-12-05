package com.example.projectdemo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for accessing rentals in the database.
 * @author Nuutti Turunen
 */
public class RentalDao {
    private List<Rental> rentals = new ArrayList<>();

    /**
     * Adds a new rental to the database.
     */
    public void addRental(String rentalID, String rentalDate, String returnDate, String carID, String locationID, int registration_id) {
        String sql = "INSERT INTO rentals (RentalID, RentalDate, ReturnDate, CarID, LocationID, registration_id) VALUES (?, ?, ?, ?, ?, ?)";
        ConnectDb connectDb = new ConnectDb();

        try (Connection conn = connectDb.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the values for the placeholders
            pstmt.setString(1, rentalID);
            pstmt.setString(2, rentalDate);
            pstmt.setString(3, returnDate);
            pstmt.setString(4, carID);
            pstmt.setString(5, locationID);
            pstmt.setInt(6, registration_id);

            // Execute the insert statement
            pstmt.executeUpdate();
            System.out.println("Rental added successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Fetches the users rentals from the database.
     */
    public List<Rental> getRentals(int registrationId) throws SQLException {
        String sql = "SELECT * FROM `rentals` WHERE registration_id=?";
        ConnectDb connectDb = new ConnectDb();
        try(Connection conn = connectDb.connect();
            PreparedStatement psstmt = conn.prepareStatement(sql)){
            psstmt.setInt(1, registrationId);
            ResultSet rs = psstmt.executeQuery();
            while (rs.next()){
                Rental rental = new Rental(
                        rs.getString("RentalID"),
                        LocalDate.parse(rs.getString("RentalDate")),
                        LocalDate.parse(rs.getString("ReturnDate")),
                        rs.getString("CarID"),
                        rs.getString("LocationID"),
                        rs.getInt("registration_id")
                );
                rentals.add(rental);
            }

        } catch (SQLException e) {
                System.out.println(e.getMessage());
        }
        return rentals;
    }

}
