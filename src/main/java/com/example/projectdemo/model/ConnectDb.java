package com.example.projectdemo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDb {
    String url = "jdbc:mysql://mysql.metropolia.fi:3306/nuuttitu?useSSL=false";
    String user = "nuuttitu";
    String password = "l3gion2016xx99";

    Connection conn;

    public Connection connect() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
        } catch (
                SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }
    public void closeConnection() throws SQLException {
        conn.close();
    }
}
