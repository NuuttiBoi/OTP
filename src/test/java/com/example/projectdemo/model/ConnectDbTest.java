package com.example.projectdemo.model;

import org.junit.internal.runners.statements.Fail;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectDbTest {
    String url = "jdbc:mysql://mysql.metropolia.fi:3306/nuuttitu?useSSL=false";
    String user = "nuuttitu";
    String password = "l3gion2016xx99";

    Connection conn;

    @Test
    void connect() {
        try{
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}