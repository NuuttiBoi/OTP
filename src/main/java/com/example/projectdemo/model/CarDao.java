package com.example.projectdemo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class for accessing cars in the database.
 *
 * @author Nuutti Turunen
 */

public class CarDao {

    private static List<Car> carList = new ArrayList<>();
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String carId = "CarID";

    /**
     * Default constructor.
     */
    public CarDao() {
    }

    /**
     * Fetches a list of cars from the database.
     */
    public List<Car> getList() {
        ConnectDb connectDb = new ConnectDb();
        Connection conn = connectDb.connect();
        try  {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM vehicles";
            ResultSet rs = stmt.executeQuery(query);
            carList = fetchCars(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }


    /**
     * Autot haetaan käyttäjän valitseman sijainnin perusteella.
     */
    public List<Car> getCarsByLocation(String locationID) {
        List<Car> carsByLocation = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE LocationID = ?";

        ConnectDb connectDb = new ConnectDb();
        Connection conn = connectDb.connect();

        try {
             PreparedStatement stmt = conn.prepareStatement(query);
            // asettaa locationID:n queryy toiseksi parametriksi
            stmt.setString(1, locationID);
            ResultSet rs = stmt.executeQuery();
            List<Car> foundCars = fetchCars(rs);
            for(Car car : foundCars){
                if(car.isAvailable()){
                    carsByLocation.add(car);
                }
            }
            System.out.println("cars in the location :" + carsByLocation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carsByLocation;
    }
    public void setAvailability(String carId) throws SQLException {
        logger.info(carId);
        String query = "UPDATE vehicles SET Availability = ? WHERE CarID = ?";

        ConnectDb connectDb = new ConnectDb();
        Connection conn = connectDb.connect();

        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,0);
        statement.setString(2,carId);
        int rowsUpdated = statement.executeUpdate();

        if (rowsUpdated > 0) {
            logger.info("An existing vehicle's availability was updated successfully.");
        }
        statement.close();
        conn.close();
    }
    public void setAvailabilityYes(String carId) throws SQLException {
        String query = "UPDATE vehicles SET Availability = ? WHERE CarID = ?";

        ConnectDb connectDb = new ConnectDb();
        Connection conn = connectDb.connect();

        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,1);
        statement.setString(2,carId);
        int rowsUpdated = statement.executeUpdate();

        if (rowsUpdated > 0) {
            logger.info("An existing vehicle's availability was updated successfully.");
        }
        statement.close();
        conn.close();
    }
    public Car getCarById(String carId) throws SQLException {
        Car car = null;
        String query = "SELECT * FROM vehicles WHERE CarID=?";
        ConnectDb connectDb = new ConnectDb();
        Connection conn = connectDb.connect();

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, carId);
        ResultSet rs = preparedStatement.executeQuery();
        car = fetchCars(rs).get(0);
        return car;
    }

    public List<Car> fetchCars(ResultSet resultSet) throws SQLException {
        List<Car> cars = new ArrayList<>();
        while (resultSet.next()) {
            Car car = new Car(
                    resultSet.getString(carId),
                    resultSet.getString("Make"),
                    resultSet.getString("Model"),
                    resultSet.getInt("Year"),
                    resultSet.getString("LicensePlate"),
                    resultSet.getBoolean("Availability"),
                    resultSet.getDouble("Price"),
                    resultSet.getString("LocationID"),
                    resultSet.getInt("km_driven")
            );
            cars.add(car);
        }
        return cars;
    }


}