package com.example.projectdemo.controller;

import com.example.projectdemo.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

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
    private Car car;
    private LocalDate returnDate;
    private LocalDate startDate;
    private UserDAO user;

    @FXML
    public void initialize() {
        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        logo.setImage(carLogo);

        mainPane.setBackground(new Background(new BackgroundFill(Color.web("#21283d"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setCarDetails(Image carImage, Car selectedCar, LocalDate startDate, LocalDate returnDate) throws SQLException {
        this.returnDate = returnDate;
        this.startDate = startDate;
        this.car = selectedCar;
        carPic.setImage(carImage);
        CarDAO dao = new CarDAO("car");
        dao.setAvailability(selectedCar.getId());
        modelText.setText(selectedCar.getMake() + " " + selectedCar.getModel());
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
    void handleRentCarClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/Payment1.fxml"));
        Parent paymentLayout = fxmlLoader.load();
        Stage paymentStage = new Stage();
        PaymentController paymentController = new PaymentController();
        Scene scene = new Scene(paymentLayout, 300, 600);
        paymentStage.setScene(scene);
        paymentStage.setTitle("Payment Page");
        paymentStage.show();

        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.addRental("11", SessionManager.getStartDate().toString(), SessionManager.getEndDate().toString(),
                "C1","1",8);
    }
}
