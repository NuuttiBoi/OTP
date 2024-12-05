package com.example.projectdemo.controller;

import com.example.projectdemo.model.ConnectDb;
import com.example.projectdemo.model.RegistrationDao;
import com.example.projectdemo.model.User;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;

class RegistrationCarListPageControllerTest {


    @Test
    void login() throws SQLException {
        ConnectDb connectDb = new ConnectDb();
        connectDb.connect();
        RegistrationDao registrationDAO = new RegistrationDao();
        User user = new User(100,"testi","testi");
        registrationDAO.insertRecord(String.valueOf(user.getUserID()), user.getEmail(), user.getPassword());

    }

}