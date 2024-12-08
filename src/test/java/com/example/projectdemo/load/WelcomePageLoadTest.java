package com.example.projectdemo.load;

import com.example.projectdemo.model.LanguageOption;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.testfx.api.FxAssert;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;

class WelcomePageLoadTest extends ApplicationTest {

    private ComboBox<LanguageOption> languageSelection;
    private Button signInButton;
    private Text welcomeText;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectdemo/fxmlFiles/WelcomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 600);
        stage.setTitle("Car Rental");
        stage.setScene(scene);
        stage.show();

        languageSelection = lookup("#languageSelection").query();
        signInButton = lookup("#signInButton").query();
        welcomeText = lookup("#welcomeText").query();

        FxAssert.verifyThat(languageSelection, isNotNull());
        FxAssert.verifyThat(signInButton, isNotNull());
        FxAssert.verifyThat(welcomeText, isNotNull());
    }

    @Test
    void testUserInteraction() {
        clickOn(languageSelection);
        clickOn("English");
        clickOn(signInButton);

        FxAssert.verifyThat(welcomeText, isNotNull());
    }
}
