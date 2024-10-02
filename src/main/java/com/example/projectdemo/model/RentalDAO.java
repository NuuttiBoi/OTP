package com.example.projectdemo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalDAO {
    public void addRental(String rentalID, String rentalDate, String returnDate, String status, String customerID, String carID, String locationID) {
        String sql = "INSERT INTO Rental (RentalID, RentalDate, ReturnDate, Status, CustomerID, CarID, LocationID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        ConnectDb connectDb = new ConnectDb();

        try (Connection conn = connectDb.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the values for the placeholders
            pstmt.setString(1, rentalID);
            pstmt.setString(2, rentalDate);
            pstmt.setString(3, returnDate);
            pstmt.setString(4, status);
            pstmt.setString(5, customerID);
            pstmt.setString(6, carID);
            pstmt.setString(7, locationID);

            // Execute the insert statement
            pstmt.executeUpdate();
            System.out.println("Rental added successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
