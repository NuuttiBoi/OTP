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
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    public Button rentCarButton;
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
    ResourceBundle bundle = LanguageManager.getResourceBundle();


    @FXML
    public void initialize() {

        backButton.setText(bundle.getString("backButton"));
        rentCarButton.setText(bundle.getString("rentCarButton"));
        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        logo.setImage(carLogo);

        //mainPane.setBackground(new Background(new BackgroundFill(Color.web("#21283d"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setCarDetails(Image carImage, Car selectedCar, LocalDate startDate, LocalDate returnDate) throws SQLException {
        this.returnDate = returnDate;
        this.startDate = startDate;
        this.car = selectedCar;
        carPic.setImage(carImage);
        CarDAO dao = new CarDAO("car");
        //dao.setAvailability(selectedCar.getId());
        modelText.setText(selectedCar.getMake() + " " + selectedCar.getModel());
        carDetailsText.setText(bundle.getString("Year") + selectedCar.getYear() + " \n " + bundle.getString("driven")  + selectedCar.getKm_driven() +
                "\nLocation: " + car.getLocation());

        long differenceInDays = ChronoUnit.DAYS.between(startDate, returnDate);
        int totalDifference = (int) differenceInDays;
        double totalPrice = selectedCar.getPrice() * totalDifference;
        System.out.println("Difference in days: " + totalDifference);

        priceText.setText(bundle.getString("rental_price") + totalDifference + bundle.getString("rentalDays") +"\n" + totalPrice + " €");
    }

    public void onClick(){
        Stage stage = (Stage) carPic.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleRentCarClick() throws IOException {
        //paymentController.initialize();
        Stage stage = (Stage) carPic.getScene().getWindow();
        //stage.close();
        //paymentController.setPreviousScene(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/Payment1.fxml"));
        Parent paymentLayout = fxmlLoader.load();
        PaymentController paymentController = fxmlLoader.getController();
        paymentController.setSelectedCar(car);
        Stage paymentStage = new Stage();
        Scene scene = new Scene(paymentLayout);
        paymentStage.setScene(scene);
        paymentStage.setTitle("Payment Page");
        //paymentStage.initModality(Modality.APPLICATION_MODAL);
        paymentStage.showAndWait();
        paymentStage.getScene().getRoot().requestLayout();

        /*
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.addRental("11", SessionManager.getStartDate().toString(), SessionManager.getEndDate().toString(),
                "C1","1",8);

         */
    }
}
