package com.example.projectdemo.controller;

import com.example.projectdemo.model.Car;
import com.example.projectdemo.model.CarDAO;
import com.example.projectdemo.model.ConnectDb;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.paint.ImagePattern;
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
    @FXML
    private Circle ekaPallo;
    @FXML
    private Circle tokaPallo;
    @FXML
    private Circle kolmasPallo;
    @FXML
    private Circle neljasPallo;
    @FXML
    private Circle viidesPallo;
    @FXML
    private Hyperlink seeAllLink;
    @FXML
    private ScrollBar scrollbar;
    @FXML
    private HBox pallot;


    public void loginPage() throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(loginLoader.load()));
        // Set the title for the second scene
        stage.setTitle("Scene 2");

        stage.show();
    }



    public void initialize(String locationID){
        try{
            //list = car.getList();
            // hakee autot tietystä sijainnista
            list = car.getCarsByLocation(locationID);
            carList.getItems().clear();
            carList.getItems().addAll(list);
            System.out.println(carList);
            System.out.println(car.getList());

            // Set up the scrollbar
           // scrollbar.setMin(0);
           // scrollbar.setMax(300); // Adjust this value based on the height of the HBox
            //scrollbar.setVisibleAmount(100);
            //scrollbar.setBlockIncrement(10);

            //Circles
            // Set images inside circles
            Image toyotaLogo = new Image(getClass().getResource("/com/example/projectdemo/carLogos/toyota_logo.png").toExternalForm());
            Image vwLogo = new Image(getClass().getResource("/com/example/projectdemo/carLogos/vw_logo.png").toExternalForm());
            Image nissanLogo = new Image(getClass().getResource("/com/example/projectdemo/carLogos/nissan_logo.png").toExternalForm());
            Image fordLogo = new Image(getClass().getResource("/com/example/projectdemo/carLogos/ford_logo.png").toExternalForm());
            Image hondaLogo = new Image(getClass().getResource("/com/example/projectdemo/honda_logo.png").toExternalForm());


            // Fill the circles with the respective car logos
            ekaPallo.setFill(new ImagePattern(toyotaLogo));
            tokaPallo.setFill(new ImagePattern(vwLogo));
            kolmasPallo.setFill(new ImagePattern(nissanLogo));
            neljasPallo.setFill(new ImagePattern(fordLogo));
            viidesPallo.setFill(new ImagePattern(hondaLogo));

            // Set up a listener for the scrollbar's value property
            scrollbar.valueProperty().addListener((obs, oldVal, newVal) -> {
                // Calculate the new Y position based on the scrollbar's value
                System.out.println("Scrollbar Value Changed: " + newVal);
                pallot.setLayoutY(-newVal.doubleValue()); // Move the HBox in the opposite direction of the scrollbar
            });



        } catch (Exception e){
            e.printStackTrace();
        }

        seeAllLink.setOnAction(event -> {
            carList.getItems().clear();
            carList.getItems().addAll(list); // Show all cars
        });

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
        // Event handling for circle clicks
        ekaPallo.setOnMouseClicked(event -> {
            filterCarsByModel("Toyota");
        });

        tokaPallo.setOnMouseClicked(event -> {
            filterCarsByModel("Volkswagen");
        });

        kolmasPallo.setOnMouseClicked(event -> {
            filterCarsByModel("Nissan");
        });

        neljasPallo.setOnMouseClicked(event -> {
            filterCarsByModel("Ford");
        });

        viidesPallo.setOnMouseClicked(event -> {
            filterCarsByModel("Honda");
        });
    }

    // Method to filter cars based on the model (brand)
    private void filterCarsByModel(String model) {
        // Clear the current items in the ListView
        carList.getItems().clear();
        // Filter the cars by the given model (case-insensitive)
        List<Car> filteredCars = new ArrayList<>();
        for (Car car : list) {
            if (car.getMake().equalsIgnoreCase(model)) {
                filteredCars.add(car);
            }
        }

        // Add the filtered cars to the ListView
        carList.getItems().addAll(filteredCars);
    }


    // Method to open a new window with item details
    private void openNewWindow(Car selectedCar) throws IOException {
        // Create a new stage (window)
        // Create a new stage (window)
        Stage newWindow = new Stage();
        newWindow.setTitle("Car Details");

        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/CarPage.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout

        CarPageController controller = fxmlLoader.getController();  // Make sure to import your controller class


        // Load the image and pass it to the controller
        Image image;
        if(Objects.equals(selectedCar.getMake(),"Toyota")){
            image = new Image(getClass().getResource("/com/example/projectdemo/carPhotos/golf.jpg").toString());
        } else {
            image = new Image(getClass().getResource("/com/example/projectdemo/carPhotos/golf.jpg").toString());
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