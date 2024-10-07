package com.example.projectdemo.controller;

import com.example.projectdemo.model.Car;
import com.example.projectdemo.model.RentalDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class CarPageController {

    @FXML
    public Text modelText;
    @FXML
    public Text carDetailsText;
    public ListView carLocationList;
    @FXML
    public Text priceText;
    @FXML
    ImageView carPic;
    @FXML
    private Label carLabel;
    @FXML
    private Button backButton;
    @FXML
    private Pane mainPane;
    @FXML
    public ImageView logo;
    boolean isSignedIn = false;
    Car car;
    private LocalDate returnDate;
    private LocalDate startDate;
    private HomeController homeController = new HomeController();

    @FXML
    public void initialize() {
        // Load the car logo image
        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        logo.setImage(carLogo);

        mainPane.setBackground(new Background(new BackgroundFill(Color.web("#21283d"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setCarDetails(Image carImage, Car selectedCar, LocalDate startDate, LocalDate returnDate) {
        this.returnDate = returnDate;
        this.startDate = startDate;
        this.car = selectedCar;
        carPic.setImage(carImage);
        modelText.setText(selectedCar.getMake() + " " +  selectedCar.getModel());
        carDetailsText.setText("Year: " + selectedCar.getYear() + " \nKilometers driven: " + selectedCar.getKm_driven() +
        "\nLocation: " + car.getLocation());

        long differenceInDays = ChronoUnit.DAYS.between(startDate, returnDate);
        int totalDifference = (int) differenceInDays;
        double totalPrice = selectedCar.getPrice() * totalDifference;
        System.out.println("Difference in days: " + totalDifference);

        priceText.setText("Price for " + totalDifference + " days:\n" + totalPrice + " €");
    }
    public void onClick(){
        Stage stage = (Stage) carPic.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleRentCarClick() throws IOException, SQLException {
        isSignedIn = homeController.isSignedIn();
        // Load the FXML file for the form
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/RentCar.fxml"));
        Parent formLayout = fxmlLoader.load();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // (placeholder) tähän logiikka, jos käyttäjä ei ole kirjautunut sisääm,
        // niin hänet viedään kirjautumisruutuun.
        // Jos on kirjautunut sisään niin auton pystyy vuokraamaan.
        if(isSignedIn){
            RentalDAO rentalDAO = new RentalDAO();
            // tähän logiikka, joka lisää uuden vuokrauksen ja linkittää sen aktiiviseen käyttäjään
            alert.setContentText("You are signed in");
            alert.showAndWait();
        } else {
            alert.setContentText("You have to sign in/register in order to rent a car");
            alert.showAndWait();
        }
        car.setRented(LocalDate.from(returnDate.atStartOfDay()));

        /*
        // Create a new stage (window) for the form
        Stage formWindow = new Stage();
        formWindow.setTitle("Rent Car");

        // Set the form scene
        Scene formScene = new Scene(formLayout, 400, 300);
        formWindow.setScene(formScene);

        // Set the new window as a modal window
        formWindow.initModality(Modality.APPLICATION_MODAL);

        // Show the window and wait until it's closed before returning to the previous window
        formWindow.showAndWait();
         */
    }
}

