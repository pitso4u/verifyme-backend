/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.klmsystems.javafxdesktop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import com.klmsystems.javafxdesktop.models.Incident;
import com.klmsystems.javafxdesktop.services.ApiService;
import com.klmsystems.javafxdesktop.services.ApiServiceImplementation;
/**
 * FXML Controller class
 *
 * @author sam
 */
public class ManageIncidentsController implements Initializable {

    @FXML
    private TableView<Incident> incidentTable;
    
    private ApiService apiService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apiService = new ApiServiceImplementation();
    }
    
    @FXML
    private void viewIncidents(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewIncidents.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void createIncident(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("createIncident.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateIncident(ActionEvent event) {
        try {
            // Get selected incident
            Incident selectedIncident = incidentTable.getSelectionModel().getSelectedItem();
            if (selectedIncident != null) {
                // Load edit form
                FXMLLoader loader = new FXMLLoader(getClass().getResource("editIncident.fxml"));
                Parent root = loader.load();
                EditIncidentController controller = loader.getController();
                controller.setIncident(selectedIncident);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteIncident(ActionEvent event) {
        try {
            Incident selectedIncident = incidentTable.getSelectionModel().getSelectedItem();
            if (selectedIncident != null) {
                // Call service to delete incident
                try {
                    apiService.deleteIncident(selectedIncident.getId());
                    System.out.println("Incident deleted successfully");
                } catch (Exception e) {
                    System.err.println("Error deleting incident: " + e.getMessage());
                    System.out.println("Please check if the incident exists and try again");
                }
                // Refresh incident list
                viewIncidents(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void backToDashboard(ActionEvent event) {
        navigateToDashboard(event);
    }

    private void navigateToDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
