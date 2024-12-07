package com.example.projectdemo.model;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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
    ResourceBundle bundle = LanguageManager.getResourceBundle();
    private String language = bundle.getLocale().toLanguageTag();
    private String currency;
    private String currencySymbol;
    private String price;
    private ApiFacade apiFacade;

    /**
     * Default constructor.
     */
    public CarDao() {
    }

    /**
     * Sets the desired currency based on the language that the user has selected.
     */
    public void setCurrency(){
        System.out.println(language);
        switch (language){
            case "en-USD":
                currency = "USD";
                currencySymbol = "$";
                break;
            case "fi-FI":
                currency = "EUR";
                currencySymbol = "€";
                break;
            case "ja-JP":
                currency = "JPY";
                currencySymbol = "¥";
                break;
            case "ru-RU":
                currency = "RUB";
                currencySymbol = "₽";
                break;
            default:
                currency = "EUR";
                currencySymbol = "€";
                break;
        }
        SessionManager.setCurrency(currencySymbol);
    }

    /**
     * Fetches a list of cars from the database.
     */
    public List<Car> getList() {
        ConnectDb connectDb = new ConnectDb();
        Connection conn = connectDb.connect();
        try  {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM vehicle_translations";
            ResultSet rs = stmt.executeQuery(query);
            carList = fetchCars(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
          throw new RuntimeException(e);
        } catch (ParseException e) {
          throw new RuntimeException(e);
        }
      return carList;
    }


    /**
     * Autot haetaan käyttäjän valitseman sijainnin perusteella.
     */
    public List<Car> getCarsByLocation(String locationID, ResourceBundle bundle) {
        String languageCode = String.valueOf(bundle.getLocale());
        List<Car> carsByLocation = new ArrayList<>();
        String query = "SELECT * FROM vehicle_translations WHERE LocationID = ? AND language_code = ?";

        ConnectDb connectDb = new ConnectDb();
        Connection conn = connectDb.connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            // asettaa locationID:n queryy toiseksi parametriksi
            stmt.setString(1, locationID);
            stmt.setString(2, languageCode);
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
        } catch (IOException e) {
          throw new RuntimeException(e);
        } catch (ParseException e) {
          throw new RuntimeException(e);
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
    public Car getCarById(String carId) throws SQLException, IOException, ParseException {
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

    public List<Car> fetchCars(ResultSet resultSet) throws SQLException, IOException, ParseException {
        setCurrency();

        List<Car> cars = new ArrayList<>();
        while (resultSet.next()) {
            price = String.valueOf(resultSet.getDouble("Price"));
            Car car = new Car(
                    resultSet.getString("car_id"),
                    resultSet.getString("Make"),
                    resultSet.getString("Model"),
                    resultSet.getInt("Year"),
                    "ABC-123",
                    true,
                    Double.parseDouble(convertPrice(currency,price)),
                    resultSet.getString("LocationID"),
                    resultSet.getInt("distance_driven")
            );
            cars.add(car);
        }
        return cars;
    }
    /**
     * Converts the rental price to the target currency from EUR using the FXRates API
     */
    public String convertPrice(String targetCurrency, String amount) throws IOException, ParseException {
        apiFacade = new ApiFacade();
        String query = "https://api.fxratesapi.com/convert?from=EUR&to=" + targetCurrency + "&date=2024-12-01&amount=" + amount + "&format=json";
        String conversionResult = apiFacade.getAttributeValueFromJson(query,"result");
        return conversionResult;
    }


    public ApiFacade getApiFacade() {
        return apiFacade;
    }
}