package com.klmsystems.javafxdesktop;

import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javafx.animation.Interpolator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenManager extends BorderPane implements ControlledScreen {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenManager.class);
    private HashMap<String, Node> screens = new HashMap<>();
    private HashMap<String, ControlledScreen> controllers = new HashMap<>();
    private Deque<String> screenHistory = new LinkedList<>();
    private String currentScreen;
    private Stage primaryStage;

    public ScreenManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public ControlledScreen loadScreen(String screenID, String screenFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screenFile));
            Parent root = loader.load();

            ControlledScreen controller = loader.getController();
            if (controller == null) {
                LOGGER.error("Controller for screen '{}' is null. Check FXML file: {}", screenID, screenFile);
                return null;
            }

            controller.setScreenParent(this);
            screens.put(screenID, root);
            controllers.put(screenID, controller);

            return controller;
        } catch (Exception e) {
            LOGGER.error("Failed to load screen '{}' from file '{}'. Cause: {}", screenID, screenFile, e.getMessage());
            return null;
        }
    }

    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    public Node getScreen(String name) {
        return screens.get(name);
    }

    public ControlledScreen getController(String name) {
        return controllers.get(name);
    }

    public void addController(String name, ControlledScreen controlledScreen) {
        controllers.put(name, controlledScreen);
    }

    public void printScreenMaps() {
        LOGGER.info("Screens map: " + screens.keySet());
        LOGGER.info("Controllers map: " + controllers.keySet());
    }

    public boolean setScreen(final String name) {
        if (currentScreen != null) {
            screenHistory.add(currentScreen);
        }

        Node screenNode = screens.get(name);
        if (screenNode != null) {
            getChildren().clear();
            getChildren().add(screenNode);
            currentScreen = name;
            LOGGER.info("Screen set successfully: " + name);
            return true;
        } else {
            LOGGER.error("Screen not found: " + name);
            return false;
        }
    }

    @Override
    public void runOnScreenChange() {
        // Any logic you want to run when the screen changes
    }

    @Override
    public void setScreenParent(ScreenManager screenPage) {
        // This method is required by the interface but not used for ScreenManager
    }
public void setLoadedScreens(Map<String, Parent> loadedScreens) {
    for (Map.Entry<String, Parent> entry : loadedScreens.entrySet()) {
        addScreen(entry.getKey(), entry.getValue());
    }
}


    @Override
    public void cleanup() {
        screens.clear();
        controllers.clear();
        screenHistory.clear();
    }

    public boolean unloadCurrentScreen() {
        if (currentScreen != null) {
            ControlledScreen controller = controllers.get(currentScreen);
            if (controller != null) {
                controller.cleanup();
            }

            screens.remove(currentScreen);
            getChildren().clear();
            currentScreen = null;
            return true;
        } else {
            LOGGER.error("No current screen to unload!");
            return false;
        }
    }

    public boolean unloadScreen(String name) {
        ControlledScreen controller = controllers.remove(name);
        if (controller != null) {
            controller.cleanup();
        }

        return screens.remove(name) != null;
    }

    public void goBack() {
        if (!screenHistory.isEmpty()) {
            String previousScreen = screenHistory.pollLast();
            setScreen(previousScreen);
        }
    }

    private static class AnimateFXInterpolator {
        public static final Interpolator EASE = Interpolator.SPLINE(0.25, 0.1, 0.25, 1);
    }
}