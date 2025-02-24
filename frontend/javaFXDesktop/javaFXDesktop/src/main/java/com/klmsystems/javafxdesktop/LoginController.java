package com.klmsystems.javafxdesktop;

import com.klmsystems.javafxdesktop.models.AuthResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import com.klmsystems.javafxdesktop.services.ApiService;
import com.klmsystems.javafxdesktop.services.AuthService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import retrofit2.Call;
import retrofit2.Response;
public class LoginController implements Initializable,ControlledScreen{

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label errorLabel;
    AuthService authService;
    private ScreenManager mainContainer;
@Override
    public void initialize(URL location, ResourceBundle resources) {
         usernameField.setText("testuser");
        passwordField.setText("testpassword");
    }
  @FXML
private void handleLogin(ActionEvent event) {
    String username = usernameField.getText();
    String password = passwordField.getText();

    authService = new AuthService();
    authService.authenticateUser(username, password).enqueue(new retrofit2.Callback<AuthResponse>() {
        @Override
        public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Login successful! Token: " + response.body().getToken());

                // Navigate to dashboard on successful login
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    errorLabel.setText("Error loading dashboard.");
                }
            } else {
                // Failed login due to incorrect credentials or server error
                errorLabel.setText("Invalid username or password.");
            }
        }

        @Override
        public void onFailure(Call<AuthResponse> call, Throwable t) {
            System.err.println("Login request failed: " + t.getMessage());
            errorLabel.setText("Server error. Please try again later.");
        }
    });
}


    @FXML
    private void handleCancel(ActionEvent event) {
        // Close the application
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void setScreenParent(ScreenManager screenPage) {
        this.mainContainer=screenPage;
    }

    @Override
    public void runOnScreenChange() {
        
    }

    @Override
    public void cleanup() {
       
    }

    
}