package com.example.projectdemo.controller;

import com.example.projectdemo.model.LanguageManager;
import com.example.projectdemo.model.LanguageOption;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.RED;

/**
 * Controller for the first scene.
 */

public class WelcomePageController {

    @FXML
    public ComboBox<LanguageOption> languageSelection;

    @FXML
    public Text welcomeText;
    @FXML
    public Text instructionText;
    @FXML
    private Text buttonText;

    @FXML
    private Button signInButton;

    @FXML
    public ImageView logo;

    private ResourceBundle bundle;

    private Image loadImage(String filename) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(filename)) {
            if (is == null) {
                throw new IOException("InputStream is invalid for filename " + filename);
            }
            return new Image(is);
        }
    }

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() throws IOException {
        Image carLogo = new Image(getClass().getResource("/com/example/projectdemo/logo.png").toExternalForm());
        logo.setImage(carLogo);


        Image english = loadImage("/com/example/projectdemo/flags/usa.png");
        Image japanese = loadImage("/com/example/projectdemo/flags/japan.png");
        Image finnish = loadImage("/com/example/projectdemo/flags/finland.png");
        Image russian = loadImage("/com/example/projectdemo/flags/russia.png");


        ImageView englishImageView = new ImageView(english);
        englishImageView.setFitHeight(20); // adjust size as needed
        englishImageView.setFitWidth(20);

        ImageView japaneseImageView = new ImageView(japanese);
        japaneseImageView.setFitHeight(20);
        japaneseImageView.setFitWidth(20);

        ImageView finnishImageView = new ImageView(finnish);
        finnishImageView.setFitHeight(20);
        finnishImageView.setFitWidth(20);

        ImageView russianImageView = new ImageView(russian);
        russianImageView.setFitHeight(20);
        russianImageView.setFitWidth(20);

        LanguageOption englishOption = new LanguageOption("English", englishImageView, english);
        LanguageOption finnishOption = new LanguageOption("Finnish", finnishImageView, finnish);
        LanguageOption japaneseOption = new LanguageOption("Japanese", japaneseImageView, japanese);
        LanguageOption russianOption = new LanguageOption("Russian", russianImageView, russian);


        languageSelection.getItems().addAll(englishOption,finnishOption,japaneseOption,russianOption);

        /*
        // Set the cell factory for displaying items
        languageSelection.setCellFactory(comboBox -> new ListCell<LanguageOption>() {
            @Override
            protected void updateItem(LanguageOption option, boolean empty) {
                super.updateItem(option, empty);
                if (empty || option == null) {
                    //setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(option.getImageView()); // Set graphic for list items
                    setText(null); // No text shown
                }
            }
        });

         */


        languageSelection.setCellFactory(new Callback<ListView<LanguageOption>, ListCell<LanguageOption>>() {
            @Override
            public ListCell<LanguageOption> call(ListView<LanguageOption> languageOptionListView) {
                return new ListCell<LanguageOption>(){
                    Label flag = new Label();
                    Label name = new Label();
                    private final ImageView view;
                    private final HBox cell;
                    {
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        cell = new HBox();
                        view = new ImageView();
                        cell.getChildren().add(view);
                        cell.getChildren().add(name);
                    }
                    @Override
                    protected void updateItem(LanguageOption languageOption, boolean empty) {
                        super.updateItem(languageOption, empty);
                        if(languageOption == null || empty){
                            setGraphic(null);
                        } else {
                            name.setText(languageOption.getLanguage());
                            view.setImage(languageOption.getImage());
                            view.setFitWidth(40);
                            view.setFitHeight(40);
                            setGraphic(cell);
                        }
                    }
                };
            }
        });

        // Set the button cell for displaying the selected item
        languageSelection.setButtonCell(new ListCell<LanguageOption>() {
            @Override
            protected void updateItem(LanguageOption option, boolean empty) {
                super.updateItem(option, empty);
                if (option == null) {
                    //setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(option.getImageView()); // Set graphic for the selected item
                    setText(option.toString()); // No text shown
                }
            }
        });

        // Load saved language preference or set default
        String savedLanguage = loadLanguagePreference();
        if (savedLanguage != null) {
            languageSelection.setValue(languageSelection.getItems().stream()
                    .filter(option -> option.getLanguage().equals(savedLanguage))
                    .findFirst()
                    .orElse(englishOption)); // Fallback to English if saved language not found
            switchLanguage(savedLanguage);
        } else {
            languageSelection.setValue(englishOption); // Default to English
            switchLanguage("English");
        }

        // Handle language selection change
        languageSelection.setOnAction(event -> {
            LanguageOption selectedOption = languageSelection.getValue();
            if (selectedOption != null) {
                String selectedLanguage = selectedOption.getLanguage();
                switchLanguage(selectedLanguage);
                saveLanguagePreference(selectedLanguage);
                System.out.println("Language selected: " + selectedLanguage);
            }
        });


        // Load default language (or previously saved language)
        /*
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

         */

    }
    private void switchLanguage(String language) {
        Locale locale;
        if (language.equals("Finnish")) {
            locale = new Locale("fi","FI");
            LanguageManager.setLocale(locale);
        } else if (language.equals("Japanese")) {
            locale = new Locale("ja","JP");
            LanguageManager.setLocale(locale);
        } else if (language.equals("Russian")) {
            locale = new Locale("ru", "RU");
            LanguageManager.setLocale(locale);
        } else {
            locale = new Locale("en","US");
            LanguageManager.setLocale(locale);
        }

        // Load the resource bundle for the selected language
        bundle = ResourceBundle.getBundle("messages", locale);

        // Update UI text
        welcomeText.setText(bundle.getString("welcomeMessage"));
        instructionText.setText(bundle.getString("instruction"));
        signInButton.setText(bundle.getString("buttonText"));
    }


    private void saveLanguagePreference(String language) {
        // Save language preference to a file or configuration with UTF-8 encoding
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("languagePreference.txt"), "UTF-8"))) {
            writer.write(language);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String loadLanguagePreference() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("languagePreference.txt"), StandardCharsets.UTF_8))) {
            return reader.readLine();
        } catch (IOException e) {
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
