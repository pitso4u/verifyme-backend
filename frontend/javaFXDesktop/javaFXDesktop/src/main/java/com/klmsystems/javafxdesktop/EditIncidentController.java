package com.klmsystems.javafxdesktop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.klmsystems.javafxdesktop.models.Incident;
import com.klmsystems.javafxdesktop.services.ApiService;
import com.klmsystems.javafxdesktop.services.ApiServiceImplementation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditIncidentController implements Initializable {

    @FXML
    private TextField incidentTypeField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField reportedByField;
    @FXML
    private TextField involvedPartiesField;
    @FXML
    private TextField statusField;
    @FXML
    private TextField resolutionField;

    private Incident incident;
    private ApiService apiService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apiService = new ApiServiceImplementation(); // Use ApiServiceImplementation
        // Initialize form with incident data if available
        if (incident != null) {
            incidentTypeField.setText(incident.getIncidentType());
            descriptionField.setText(incident.getDescription());
            locationField.setText(incident.getLocation());
            reportedByField.setText(incident.getReportedBy());
            involvedPartiesField.setText(incident.getInvolvedParties());
            statusField.setText(incident.getStatus());
            resolutionField.setText(incident.getResolution());
        }
}
    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    @FXML
    private void saveIncident() {
        if (incident == null) {
            return;
        }

        // Update incident with form values
        incident.setIncidentType(incidentTypeField.getText());
        incident.setDescription(descriptionField.getText());
        incident.setLocation(locationField.getText());
        incident.setReportedBy(reportedByField.getText());
        incident.setInvolvedParties(involvedPartiesField.getText());
        incident.setStatus(statusField.getText());
        incident.setResolution(resolutionField.getText());

        // Call API to update incident
        Call<Incident> call = apiService.updateIncident(incident.getId(), incident); // Use updateIncident with ID
        ApiServiceImplementation.handleApiCall(call, new Callback<Incident>() { // Use ApiServiceImplementation.handleApiCall
            @Override
            public void onResponse(Call<Incident> call, Response<Incident> response) {
                if (response.isSuccessful()) {
                    navigateToManageIncidents();
                } else {
                    System.err.println("Error updating incident: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Incident> call, Throwable t) {
                System.err.println("Failed to update incident: " + t.getMessage());
            }
        });
    }

    private void navigateToManageIncidents() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("manageIncidents.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) incidentTypeField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelEdit() {
        navigateToManageIncidents();
    }
}

