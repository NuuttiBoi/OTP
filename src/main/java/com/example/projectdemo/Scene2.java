package com.example.projectdemo;

import com.example.projectdemo.model.Car;
import com.example.projectdemo.model.CarDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class Scene2 {
    @FXML
    private Button button1;
    @FXML
    private ListView carList;

    private CarDAO car = new CarDAO("car");
    private List<Car> list = new ArrayList<>();

    public void onClick(){
        try{
            list = car.getList();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
