package com.example.projectdemo.controller;

import com.example.projectdemo.model.Car;
import com.example.projectdemo.model.CarDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public ListView<Car> carList;
    public TextField searchField;
    private CarDAO car = new CarDAO("Auto");
    private List<Car> list = new ArrayList<>();
    @FXML
    private Label welcomeText;

    public void onHelloButtonClick() {
        try {
            // Load the FXML file for the second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
            // Create a new stage for the second scene
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            // Set the title for the second scene
            stage.setTitle("Scene 2");
            // Show the second scene
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void initialize(){
        try{
            list = car.getList();
            Car car1 = new Car("Toyota","Corolla",2001);
            list.add(car1);
            carList.getItems().addAll(list);
            System.out.println(carList);
            System.out.println(car.getList());
        } catch (Exception e){
            e.printStackTrace();
        }

        // Set event listener for mouse clicks on list items
        carList.setOnMouseClicked(event -> {
            String selectedItem = String.valueOf(carList.getSelectionModel().getSelectedItem());
            if (selectedItem != null) {
                // Call a method to open a new window when an item is clicked
                openNewWindow(selectedItem);
            }
        });
    }


    // Method to open a new window with item details
    private void openNewWindow(String selectedItem) {
        // Create a new stage (window)
        Stage newWindow = new Stage();
        newWindow.setTitle("Car Details");

        VBox layout = new VBox();
        layout.getChildren().add(new javafx.scene.control.Label("Selected Car: " + selectedItem));

        Scene scene = new Scene(layout, 200, 100);
        newWindow.setScene(scene);

        // Set the new window as a modal window
        newWindow.initModality(Modality.APPLICATION_MODAL); // Makes the new window block interaction with the previous window
        newWindow.showAndWait();
    }

    public void onSearchButtonClick() {
        String searchText = searchField.getText().toLowerCase();
        // Filter the list based on the search text
        List<Car> filteredList = new ArrayList<>();
        for (Car car : list) {
            if (car.getMake().toLowerCase().contains(searchText) || car.getModel().toLowerCase().contains(searchText)) {
                filteredList.add(car);
            }
        }
        // Update the ListView to display only the filtered cars
        carList.getItems().clear();
        carList.getItems().addAll(filteredList);
    }


}