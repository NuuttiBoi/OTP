package com.example.projectdemo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class for accessing the locations in the database.
 * @author Nuutti Turunen
 */
public class LocationDao {
    private String name;
    private List<Location> locationList = new ArrayList<>();
    private List<Car> carList = new ArrayList<>();

    /**
     * Constructor for the locationdao.
     */
    public LocationDao(String name) {
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
    public List<Location> getLocationList(ResourceBundle bundle) {
        String locale_name = String.valueOf(bundle.getLocale());
        System.out.println(locale_name);
        ConnectDb connectDb = new ConnectDb();
        try (Connection conn = connectDb.connect();) {
            String query = "SELECT * FROM location_translations WHERE language_code = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,locale_name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Location location = new Location(rs.getString("LocationID"),
                        rs.getString("name"),
                        rs.getString("name"), carList,
                        rs.getString("image"));
                locationList.add(location);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locationList;
    }
}
