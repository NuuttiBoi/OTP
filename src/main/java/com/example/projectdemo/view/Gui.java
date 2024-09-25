package com.example.projectdemo.view;

import com.example.projectdemo.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Gui extends Application implements IGui{
    private Controller controller;

    @Override
    public void init(){
        controller = new Controller();
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/com/example/projectdemo/Scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/projectdemo/style.css").toExternalForm());
        stage.setTitle("Car Rental");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}