package com.example.projectdemo.model;

import java.lang.reflect.Array;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nuutti Turunen
 */

public class CarDAO  {

    private static List<Car> carList = new ArrayList<>();
    private String name, model;
    private int year;

    public CarDAO(String name){
        this.name = name;
    }
    public static void main(String[] args) {
    }
    public List<Car> getList(){
        String url = "jdbc:mysql://localhost:3307/cardb";
        String user = "root";
        String password = "12345";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM vehicles";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Car car = new Car(rs.getString("CarID"),rs.getString("Make"),rs.getString("Model"),
                        rs.getInt("Year"), rs.getString("LocationID"));
                System.out.println("Car year: " + rs.getInt("Year"));
                System.out.println("Car maker: " + rs.getString("Make"));
                System.out.println("Car model: " + rs.getString("Model"));
                carList.add(car);
                // Continue retrieving other fields...
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return carList;
    }




    // Autot pitäisi hakea siis sijainnin mukaan, tässä aloitettu siihen uutta metodai
    public List<Car> getCarsByLocation(String locationID) {
        String url = "jdbc:mysql://localhost:3306/cardb";
        String user = "root";
        String password = "cee5tuyo";
        List<Car> carsByLocation = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE LocationID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the LocationID in the query
            stmt.setString(1, locationID);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Car car = new Car(rs.getString("CarID"), rs.getString("Make"), rs.getString("Model"),
                        rs.getInt("Year"), rs.getString("LocationID"));
                carsByLocation.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carsByLocation;
    }


}
