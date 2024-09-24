//
package com.example.projectdemo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.lang.reflect.Field;

public class Login {
    @FXML
    private TextField username;
    private TextField password;


public void setUsername(String userMame){
    username.setText(userMame);

}

}
