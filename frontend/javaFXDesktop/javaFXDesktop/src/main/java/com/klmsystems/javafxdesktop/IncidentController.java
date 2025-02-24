package com.klmsystems.javafxdesktop;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.klmsystems.javafxdesktop.models.Incident;
import com.klmsystems.javafxdesktop.services.ApiService;
import com.klmsystems.javafxdesktop.services.AuthService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IncidentController implements Initializable {

    @FXML
    private ListView<String> incidentsListView;

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

    private ApiService apiService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AuthService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        refreshIncidents();
    }

    @FXML
    public void refreshIncidents() {
        List<Incident> incidents = fetchAllIncidents();
        incidentsListView.getItems().clear();
        for (Incident incident : incidents) {
            incidentsListView.getItems().add(incident.getIncidentType() + " (ID: " + incident.getId() + ")");
        }
    }

    private List<Incident> fetchAllIncidents() {
        List<Incident> incidents = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(AuthService.BASE_URL + "/incidents"))
                    .GET()
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                JSONArray jsonArray = new JSONArray(response.body());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Incident incident = new Incident(
                            jsonObject.getInt("id"),
                            jsonObject.getString("incidentType"),
                            jsonObject.getString("description"),
                            Timestamp.valueOf(jsonObject.getString("occurredAt")),
                            Timestamp.valueOf(jsonObject.getString("reportedAt")),
                            jsonObject.getString("location"),
                            jsonObject.getString("reportedBy"),
                            jsonObject.getString("involvedParties"),
                            jsonObject.getString("status"),
                            jsonObject.getString("resolution"),
                            jsonObject.getBoolean("followUpNeeded"),
                            jsonObject.getString("notes"),
                            jsonObject.getString("createdBy"),
                            Timestamp.valueOf(jsonObject.getString("createdDate"))
                    );
                    incidents.add(incident);
                }
            } else {
                System.err.println("Failed to fetch incidents with status code: " + statusCode);
                System.err.println("Response body: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return incidents;
    }

    @FXML
    public void createIncident() {
        Incident newIncident = new Incident();
        newIncident.setIncidentType(incidentTypeField.getText());
        newIncident.setDescription(descriptionField.getText());
        newIncident.setLocation(locationField.getText());
        newIncident.setReportedBy(reportedByField.getText());
        newIncident.setInvolvedParties(involvedPartiesField.getText());
        newIncident.setStatus(statusField.getText());
        newIncident.setResolution(resolutionField.getText());
        newIncident.setOccurredAt(new Timestamp(System.currentTimeMillis()));
        newIncident.setReportedAt(new Timestamp(System.currentTimeMillis()));

        // Call the API to create the incident
        apiService.createIncident(newIncident).enqueue(new Callback<Incident>() {
            @Override
            public void onResponse(Call<Incident> call, Response<Incident> response) {
                if (response.isSuccessful()) {
                    refreshIncidents();
                } else {
                    System.err.println("Error creating incident: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Incident> call, Throwable t) {
                System.err.println("Failed to create incident: " + t.getMessage());
            }
        });
    } // <-- Added missing closing brace here

    @FXML
    public void deleteIncident() {
        String selectedItem = incidentsListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            int incidentId = Integer.parseInt(selectedItem.split(" ")[2].replace("(", "").replace("ID:", "").replace(")", ""));

            apiService.deleteIncident(incidentId).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        refreshIncidents();
                    } else {
                        System.err.println("Error deleting incident: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    System.err.println("Failed to delete incident: " + t.getMessage());
                }
            });
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
