package com.klmsystems.javafxdesktop;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private Label attendanceOverviewLabel;

    public void initialize() {
        fetchAttendanceData();
    }

    private void fetchAttendanceData() {
        // Simulate fetching data
        attendanceOverviewLabel.setText("Attendance Overview: 20 students present today.");
    }

    @FXML
    private void captureAttendance(ActionEvent event) {
        loadScene(event, "captureAttendance");
    }

    @FXML
    private void scanQRCode(ActionEvent event) {
        loadScene(event, "scanQRCode");
    }

    @FXML
    private void viewLearners(ActionEvent event) {
        loadScene(event, "viewAllLearners");
    }

    

    @FXML
    private void viewVisitors(ActionEvent event) {
        loadScene(event, "viewVisitors");
    }

    @FXML
    private void manageIncidents(ActionEvent event) {
        loadScene(event, "manageIncidents");
    }

    @FXML
    private void viewReports(ActionEvent event) {
        loadScene(event, "viewReports");
    }

    @FXML
    private void openSettings(ActionEvent event) {
        loadScene(event, "settings");
    }

    @FXML
    private void viewIncidents(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewIncidents.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void viewEmployees(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewEmployees.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadScene(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile + ".fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Optionally show an error message to the user
        }
    }
}