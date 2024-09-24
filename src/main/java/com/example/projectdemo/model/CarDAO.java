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
                Car car = new Car(rs.getString("Make"),rs.getString("Model"),
                        rs.getInt("Year"));
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
}
