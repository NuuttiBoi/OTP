package com.example.projectdemo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {
    private String name;
    private List<Location> locationList = new ArrayList<>();
    private List<Car> carList = new ArrayList<>();

    public LocationDAO(String name) {
        this.name = name;
    }

    ConnectDb connection = new ConnectDb();

    // Hakee sijainnit tietokannasta
    /*
    public List<Location> getLocationList() {
        String url = "jdbc:mysql://mysql.metropolia.fi:3306/nuuttitu?useSSL=false";  // Make sure to replace `nuuttitu` with your actual database name if it's different
        String user = " nuuttitu";
        String password = "cee5tuyo";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM locations";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Location location = new Location(rs.getString("LocationID"),
                        rs.getString("Location"),
                        rs.getString("Address"), carList);
                locationList.add(location);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locationList;
    }
     */
    public List<Location> getLocationList() {
        ConnectDb connectDb = new ConnectDb();
        try (Connection conn = connectDb.connect();) {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM locations";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Location location = new Location(rs.getString("LocationID"),
                        rs.getString("Location"),
                        rs.getString("Address"), carList,
                        rs.getString("image"));
                locationList.add(location);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locationList;
    }
}
