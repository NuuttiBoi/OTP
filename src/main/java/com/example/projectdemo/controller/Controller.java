package com.example.projectdemo.controller;

import com.example.projectdemo.model.Car;
import com.example.projectdemo.model.CarDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Controller {
    public ListView<Car> carList;
    public TextField searchField;
    private CarDAO car = new CarDAO("Auto");
    private List<Car> list = new ArrayList<>();
    @FXML
    private Label welcomeText;
    @FXML
    private ImageView carPic;

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
            carList.getItems().clear();
            carList.getItems().addAll(list);
            System.out.println(carList);
            System.out.println(car.getList());
        } catch (Exception e){
            e.printStackTrace();
        }

        // Set event listener for mouse clicks on list items
        carList.setOnMouseClicked(event -> {
            Car selectedCar = carList.getSelectionModel().getSelectedItem();
            if (selectedCar != null) {
                // Call a method to open a new window when an item is clicked
                try {
                    openNewWindow(selectedCar);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    // Method to open a new window with item details
    private void openNewWindow(Car selectedCar) throws IOException {
        // Create a new stage (window)
        // Create a new stage (window)
        Stage newWindow = new Stage();
        newWindow.setTitle("Car Details");

        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/Scene2.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout

        Scene2 controller = fxmlLoader.getController();  // Make sure to import your controller class


        // Load the image and pass it to the controller
        Image image;
        if(Objects.equals(selectedCar.getMake(),"Toyota")){
            image = new Image(getClass().getResource("/com/example/projectdemo/corolla.png").toString());
        } else {
            image = new Image(getClass().getResource("/com/example/projectdemo/golf.jpg").toString());
        }
        controller.setCarDetails(image, selectedCar);


        // Create the scene and set it to the stage
        Scene scene = new Scene(layout, 600, 500);
        scene.getStylesheets().add(getClass().getResource("/com/example/projectdemo/scene2_style.css").toExternalForm());
        newWindow.setScene(scene);

        // Set the new window as a modal window
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.showAndWait();  // Show the window and wait for it to be closed
    }


    public void onSearchButtonClick() {
        String searchText = searchField.getText().toLowerCase();
        // Filter the list based on the search text
        carList.getItems().clear();
        Set<Car> filteredSet = new HashSet<>(); // Using a Set to avoid duplicates
        for (Car car : list) {
            if (car.getMake().toLowerCase().contains(searchText) || car.getModel().toLowerCase().contains(searchText)) {
                filteredSet.add(car);
            }
        }
        // Update the ListView to display only the filtered cars
        carList.getItems().addAll(filteredSet);
    }


}