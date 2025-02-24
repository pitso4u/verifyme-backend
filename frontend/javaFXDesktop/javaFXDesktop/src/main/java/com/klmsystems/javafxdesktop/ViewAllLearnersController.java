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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.klmsystems.javafxdesktop.models.Learner;
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
public class ViewAllLearnersController implements Initializable {

    @FXML
    private TableView<Learner> learnersTableView;

    @FXML
    private TableColumn<Learner, Integer> idColumn;
    @FXML
    private TableColumn<Learner, String> firstnameColumn;
    @FXML
    private TableColumn<Learner, String> surnameColumn;
    @FXML
    private TableColumn<Learner, String> studentIdColumn;
    @FXML
    private TableColumn<Learner, String> qrCodeColumn;
    @FXML
    private TableColumn<Learner, Timestamp> createdAtColumn;
    @FXML
    private TableColumn<Learner, String> genderColumn;
    @FXML
    private TableColumn<Learner, String> idNumberColumn;
    @FXML
    private TableColumn<Learner, String> languageColumn;
    @FXML
    private TableColumn<Learner, String> addressColumn;
    @FXML
    private TableColumn<Learner, String> emailAddressColumn;
    @FXML
    private TableColumn<Learner, String> phoneNumberColumn;

    private DatabaseConfig databaseConfig = new DatabaseConfig();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        qrCodeColumn.setCellValueFactory(new PropertyValueFactory<>("qrCode"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        idNumberColumn.setCellValueFactory(new PropertyValueFactory<>("IDNumber"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        refreshLearners();
    }

    @FXML
    public void refreshLearners() {
        List<Learner> learners = fetchAllLearners();
        learnersTableView.getItems().clear();
        learnersTableView.getItems().addAll(learners);
    }

    private List<Learner> fetchAllLearners() {
        List<Learner> learners = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(AuthService.BASE_URL + "/learners"))
                    .GET()
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                JSONArray jsonArray = new JSONArray(response.body());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Learner learner = new Learner(
                            jsonObject.getInt("id"),
                            jsonObject.getString("firstname"),
                            jsonObject.getString("surname"),
                            jsonObject.getString("studentId"),
                            jsonObject.getString("qrCode"),
                            parseTimestamp(jsonObject.getString("createdAt")),
                            jsonObject.getString("gender"),
                            jsonObject.getString("IDNumber"),
                            jsonObject.getString("language"),
                            jsonObject.getString("address"),
                            jsonObject.getString("emailAddress"),
                            jsonObject.getString("phoneNumber")
                    );
                    learners.add(learner);
                }
            } else {
                System.err.println("Failed to fetch learners with status code: " + statusCode);
                System.err.println("Response body: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return learners;
    }

    private Timestamp parseTimestamp(String timestampStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            java.util.Date parsedDate = dateFormat.parse(timestampStr);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
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
