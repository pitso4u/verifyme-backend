package com.klmsystems.javafxdesktop;

import com.klmsystems.javafxdesktop.ScreenManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application implements ControlledScreen {
    private ScreenManager screenManager;

    private Map<String, Parent> loadedScreens = new HashMap<>();

    private static Scene scene;

    private void loadScreens() {
        // Load all screens upfront
        loadScreen("login", "src/main/resources/com/klmsystems/javafxdesktop/login.fxml");
        loadScreen("dashboard", "src/main/resources/com/klmsystems/javafxdesktop/dashboard.fxml");
        loadScreen("viewalllearners", "src/main/resources/com/klmsystems/javafxdesktop/viewalllearners.fxml");
        loadScreen("viewallemployee", "src/main/resources/com/klmsystems/javafxdesktop/viewallemployee.fxml");
        loadScreen("viewincidents", "src/main/resources/com/klmsystems/javafxdesktop/viewincidents.fxml");
        loadScreen("viewallvisitors", "src/main/resources/com/klmsystems/javafxdesktop/viewallvisitors.fxml");
        loadScreen("attendance", "src/main/resources/com/klmsystems/javafxdesktop/attendance.fxml");
    }

    private void loadScreen(String screenId, String fxmlFile) {
        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile)); still failed
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));still failed

FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxmlFile)); //still failed
            Parent screen = fxmlLoader.load();
            loadedScreens.put(screenId, screen);
        } catch (IOException e) {
            System.err.println("Error loading screen: " + screenId);
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        loadScreens();
        screenManager = new ScreenManager(stage);
        screenManager.setLoadedScreens(loadedScreens);
        scene = new Scene(screenManager, 600, 400); // Changed to login
        
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.show();
    }

    @Override
    public void setScreenParent(ScreenManager screenPage) {
        // Implementation required by ControlledScreen
    }

    @Override
    public void runOnScreenChange() {
        // Implementation required by ControlledScreen
    }

    @Override
    public void cleanup() {
        // Cleanup logic
        screenManager.cleanup();
    }

    public static void main(String[] args) {
        launch();
    }
    
}



