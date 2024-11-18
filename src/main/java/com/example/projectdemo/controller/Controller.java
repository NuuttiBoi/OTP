package com.example.projectdemo.controller;

import com.example.projectdemo.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class Controller {
    public ListView<Car> carList;
    public TextField searchField;
    public Label carListHeader;
    public Text brandsText;
    public Button searchButton;
    public Button backButton;
    private CarDAO car = new CarDAO("Auto");
    List<Car> list = new ArrayList<>();
    private LocalDate returnDate = null;
    private LocalDate startDate = null;
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
    private Circle kuudesPallo;
    @FXML
    private Circle seittämäsPallo;
    @FXML
    private Circle kaheksasPallo;
    @FXML
    private Circle yheksäsPallo;
    @FXML
    private Hyperlink seeAllLink;
    @FXML
    private ScrollBar scrollbar;
    @FXML
    private HBox pallot;
    private UserDAO user;

    public void loginPage() throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(loginLoader.load()));
        // Set the title for the second scene
        stage.setTitle("Scene 2");

        stage.show();
    }


    private Image loadImage(String filename) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(filename)) {
            if (is == null) {
                throw new IOException("InputStream is invalid for filename " + filename);
            }
            return new Image(is);
        }
    }


    public void initialize(String locationID, LocalDate startDate, LocalDate returnDate){
        ResourceBundle bundle = LanguageManager.getResourceBundle();
        this.user = user;
        this.startDate = startDate;
        this.returnDate = returnDate;
        seeAllLink.setText(bundle.getString("See_All"));
        backButton.setText(bundle.getString("Back"));
        searchButton.setText(bundle.getString("Search"));
        brandsText.setText(bundle.getString("Brands"));



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
            Image toyotaLogo = loadImage("/com/example/projectdemo/carLogos/toyota_logo.png");
            Image nissanLogo = loadImage("/com/example/projectdemo/carLogos/nissan_logo.png");
            Image fordLogo = loadImage("/com/example/projectdemo/carLogos/ford_logo.png");
            Image hondaLogo = loadImage("/com/example/projectdemo/carLogos/honda_logo.png");
            Image vwLogo = loadImage("/com/example/projectdemo/carLogos/vw_logo.png");
            Image mercedesLogo = loadImage("/com/example/projectdemo/carLogos/mercedes_logo.JPG");
            Image bmwLogo = loadImage("/com/example/projectdemo/carLogos/bmw_logo.JPG");
            Image audiLogo = loadImage("/com/example/projectdemo/carLogos/audi_logo.png");
            Image volvoLogo = loadImage("/com/example/projectdemo/carLogos/volvo_logo.JPG");


            // Fill the circles with the respective car logos
            ekaPallo.setFill(new ImagePattern(toyotaLogo));
            tokaPallo.setFill(new ImagePattern(vwLogo));
            kolmasPallo.setFill(new ImagePattern(nissanLogo));
            neljasPallo.setFill(new ImagePattern(fordLogo));
            viidesPallo.setFill(new ImagePattern(hondaLogo));
            kuudesPallo.setFill(new ImagePattern(mercedesLogo));
            seittämäsPallo.setFill(new ImagePattern(bmwLogo));
            kaheksasPallo.setFill(new ImagePattern(audiLogo));
            yheksäsPallo.setFill(new ImagePattern(volvoLogo));



            // Set up a listener for the scrollbar's value property
            scrollbar.valueProperty().addListener((obs, oldVal, newVal) -> {
                // Calculate the new Y position based on the scrollbar's value
                System.out.println("Scrollbar Value Changed: " + newVal);
                pallot.setLayoutY(-newVal.doubleValue()); // Move the HBox in the opposite direction of the scrollbar
            });





        } catch (Exception e){
            e.printStackTrace();
        }

        // näyttää kaikki autot
        seeAllLink.setOnAction(event -> {
            carList.getItems().clear();
            carList.getItems().addAll(list);
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
                } catch (SQLException e) {
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

        kuudesPallo.setOnMouseClicked(event -> {
            filterCarsByModel("Mercedes-Benz");
        });

        seittämäsPallo.setOnMouseClicked(event -> {
            filterCarsByModel("BMW");
        });

        kaheksasPallo.setOnMouseClicked(event -> {
            filterCarsByModel("Audi");
        });

        yheksäsPallo.setOnMouseClicked(event -> {
            filterCarsByModel("Volvo");
        });
    }



    // Method to filter cars based on the model (brand)
    public void filterCarsByModel(String model) {
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


    // Avaa auton vuokrausikkunan
    private void openNewWindow(Car selectedCar) throws IOException, SQLException {
        Stage currentStage = (Stage) carList.getScene().getWindow();
        currentStage.close();

        Stage newWindow = new Stage();
        newWindow.setTitle("Car Details");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/CarPage.fxml"));

        Parent layout = fxmlLoader.load();

        CarPageController controller = fxmlLoader.getController();

        Image image = null;
        ConnectDb connectDb = new ConnectDb();
        Connection conn = connectDb.connect();

        String query = "SELECT image FROM vehicles WHERE CarID = ?";

        try{
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, selectedCar.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                image = new Image(resultSet.getString("image"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }


        controller.setCarDetails(image, selectedCar, startDate, returnDate);
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("/com/example/projectdemo/style.css").toExternalForm());
        newWindow.setScene(scene);
        //newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.show();  // Show the window and wait for it to be closed
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
    public void onBackButtonClick(){
        // Close the current window (stage)
        Stage stage = (Stage) carList.getScene().getWindow();
        stage.close();
    }


}