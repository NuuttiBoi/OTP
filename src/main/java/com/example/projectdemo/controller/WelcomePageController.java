package com.example.projectdemo.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class WelcomePageController {

    @FXML
    public ComboBox languageSelection;

    public Text welcomeText;
    public Text instructionText;
    private Text buttonText;

    @FXML
    private Button signInButton;

    @FXML
    public ImageView logo;

    private ResourceBundle bundle;


    @FXML
    public void initialize() {
        // Load the car logo image
        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        logo.setImage(carLogo);

        // Weekdays
        String[] languages =
                { "English", "Finnish", "Japanese"};

        // Create a combo box
        languageSelection.setItems(FXCollections.observableArrayList(languages));

        // Load default language (or previously saved language)
        String savedLanguage = loadLanguagePreference(); // Load saved preference from a file or config
        if (savedLanguage != null) {
            languageSelection.setValue(savedLanguage);
            switchLanguage(savedLanguage);
        } else {
            languageSelection.setValue("English"); // Default language
            switchLanguage("English");
        }

        // Handle language selection change
        languageSelection.setOnAction(event -> {
            String selectedLanguage = (String) languageSelection.getValue();
            switchLanguage(selectedLanguage);
            saveLanguagePreference(selectedLanguage); // Save selected language
            System.out.println("Language selected: " + selectedLanguage);
        });

    }
    private void switchLanguage(String language) {
        Locale locale;
        if (language.equals("Finnish")) {
            locale = new Locale("fi","FI");
        } else if (language.equals("Japanese")) {
            locale = new Locale("ja","JP");
        }
        else {
            locale = new Locale("en","US");
        }

        // Load the resource bundle for the selected language
        bundle = ResourceBundle.getBundle("messages", locale);

        // Update UI text
        welcomeText.setText(bundle.getString("welcomeMessage"));
        instructionText.setText(bundle.getString("instruction"));
        signInButton.setText(bundle.getString("buttonText"));
    }

    private void saveLanguagePreference(String language) {
        // Save language preference to a file or configuration
        try (java.io.FileWriter writer = new java.io.FileWriter("languagePreference.txt")) {
            writer.write(language);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private String loadLanguagePreference() {
        // Load language preference from a file or configuration
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("languagePreference.txt"))) {
            return reader.readLine();
        } catch (java.io.IOException e) {
            return null; // Return null if preference file doesn't exist
        }
    }

    public void onSignInButtonClicked() throws IOException {
        // Get the current stage (the one with the home page)
        Stage currentStage = (Stage) signInButton.getScene().getWindow();

        // Close the current stage
        currentStage.close();

        // Load the login page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/LoginController.fxml"));
        Parent layout = fxmlLoader.load();  // Load the FXML layout

        // Create a new stage for the login page
        Stage loginStage = new Stage();
        Scene scene = new Scene(layout, 300, 600);
        loginStage.setScene(scene);
        loginStage.setTitle("Login Page");

        // Show the login page
        loginStage.show();
    }
}