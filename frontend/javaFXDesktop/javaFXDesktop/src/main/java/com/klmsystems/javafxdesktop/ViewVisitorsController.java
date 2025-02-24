/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.klmsystems.javafxdesktop;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.klmsystems.javafxdesktop.models.Visitor; // Ensure you have a Visitor model
import com.klmsystems.javafxdesktop.services.ApiService;
import com.klmsystems.javafxdesktop.services.AuthService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sam
 */
public class ViewVisitorsController implements Initializable {

    @FXML
    private TableView<Visitor> visitorsTableView;

    @FXML
    private TableColumn<Visitor, String> nameColumn;
    @FXML
    private TableColumn<Visitor, Integer> idColumn;
    @FXML
    private TableColumn<Visitor, String> purposeColumn;
    @FXML
    private TableColumn<Visitor, String> entryTimeColumn;
    @FXML
    private TableColumn<Visitor, String> departmentColumn;
    @FXML
    private TableColumn<Visitor, String> remarksColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        purposeColumn.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        entryTimeColumn.setCellValueFactory(new PropertyValueFactory<>("entryTime"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        remarksColumn.setCellValueFactory(new PropertyValueFactory<>("remarks"));

        refreshVisitors();
    }

    @FXML
    public void refreshVisitors() {
        List<Visitor> visitors = fetchAllVisitors();
        visitorsTableView.getItems().clear();
        visitorsTableView.getItems().addAll(visitors);
    }

    private List<Visitor> fetchAllVisitors() {
        List<Visitor> visitors = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(AuthService.BASE_URL + "/visitors"))
                    .GET()
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                JSONArray jsonArray = new JSONArray(response.body());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Visitor visitor = new Visitor(
                            jsonObject.getString("name"),
                            jsonObject.getInt("id"),
                            jsonObject.getString("purpose"),
                            jsonObject.getString("entrytime"),
                            jsonObject.optString("department", "N/A"),
                            jsonObject.optString("remarks", "")
                    );
                    visitors.add(visitor);
                }
            } else {
                System.err.println("Failed to fetch visitors with status code: " + statusCode);
                System.err.println("Response body: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return visitors;
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
