package com.example.projectdemo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {
    private String name;
    private List<Location> locationList = new ArrayList<>();

    public LocationDAO(String name) {
        this.name = name;
    }

    public List<Location> getLocationList() {
        String url = "jdbc:mysql://localhost:3306/cardb";
        String user = "root";
        String password = "cee5tuyo";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM rental_locations";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                Location location = new Location(rs.getString("LocationName"),
                        rs.getString("Address"));
                locationList.add(location);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locationList;
    }
}
