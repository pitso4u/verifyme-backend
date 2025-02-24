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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.klmsystems.javafxdesktop.models.Employee;
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
public class ViewEmployeesController implements Initializable {

    @FXML
    private TableView<Employee> employeesTableView;

    @FXML
    private TableColumn<Employee, Integer> idColumn;

    @FXML
    private TableColumn<Employee, String> firstnameColumn;

    @FXML
    private TableColumn<Employee, String> surnameColumn;

    @FXML
    private TableColumn<Employee, String> employeeIdColumn;

    @FXML
    private TableColumn<Employee, String> qrCodeColumn;

    @FXML
    private TableColumn<Employee, Timestamp> createdAtColumn;

    @FXML
    private TableColumn<Employee, String> genderColumn;

    @FXML
    private TableColumn<Employee, String> idNumberColumn;

    @FXML
    private TableColumn<Employee, String> languageColumn;

    @FXML
    private TableColumn<Employee, String> addressColumn;

    @FXML
    private TableColumn<Employee, String> emailAddressColumn;

    @FXML
    private TableColumn<Employee, String> phoneNumberColumn;

    @FXML
    private TableColumn<Employee, String> departmentColumn;

    @FXML
    private TableColumn<Employee, String> positionColumn;

    @FXML
    private TableColumn<Employee, Timestamp> hiredAtColumn;

    @FXML
    private TableColumn<Employee, String> raceColumn;

    @FXML
    private TableColumn<Employee, String> postalCodeColumn;

    @FXML
    private TableColumn<Employee, Integer> userIdColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        qrCodeColumn.setCellValueFactory(new PropertyValueFactory<>("qrCode"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        idNumberColumn.setCellValueFactory(new PropertyValueFactory<>("IDNumber"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        hiredAtColumn.setCellValueFactory(new PropertyValueFactory<>("hiredAt"));
        raceColumn.setCellValueFactory(new PropertyValueFactory<>("race"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        refreshEmployees();
    }

    @FXML
    public void refreshEmployees() {
        List<Employee> employees = fetchAllEmployees();
        employeesTableView.getItems().clear();
        employeesTableView.getItems().addAll(employees);
    }

    private List<Employee> fetchAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(AuthService.BASE_URL + "/employees"))
                    .GET()
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                JSONArray jsonArray = new JSONArray(response.body());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Employee employee = new Employee(
                            jsonObject.getInt("id"),
                            jsonObject.getString("firstname"),
                            jsonObject.getString("surname"),
                            jsonObject.getString("employee_id"),
                            jsonObject.getString("qr_code"),
                            parseTimestamp(jsonObject.getString("created_at")),
                            jsonObject.getString("gender"),
                            jsonObject.getString("id_number"),
                            jsonObject.getString("language"),
                            jsonObject.getString("address"),
                            jsonObject.getString("email_address"),
                            jsonObject.getString("phone_number"),
                            jsonObject.getString("department"),
                            jsonObject.getString("position"),
                            parseTimestamp(jsonObject.getString("hired_at")),
                            jsonObject.getString("race"),
                            jsonObject.getString("postal_code"),
                            jsonObject.optInt("userId", -1)
                    );
                    employees.add(employee);
                }
            } else {
                System.err.println("Failed to fetch employees with status code: " + statusCode);
                System.err.println("Response body: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private Timestamp parseTimestamp(String timestampStr) {
        try {
            return Timestamp.valueOf(timestampStr.replace("T", " ").replace("Z", ""));
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid timestamp format: " + timestampStr);
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
