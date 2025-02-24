package com.klmsystems.javafxdesktop.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Path;
import java.util.List;
import retrofit2.Call;

import com.klmsystems.javafxdesktop.models.AttendanceRequest;
import com.klmsystems.javafxdesktop.models.AuthRequest;
import com.klmsystems.javafxdesktop.models.AuthResponse;
import com.klmsystems.javafxdesktop.models.Employee;
import com.klmsystems.javafxdesktop.models.Incident;
import com.klmsystems.javafxdesktop.models.Learner;
import com.klmsystems.javafxdesktop.models.Visitor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiServiceImplementation implements ApiService {

    private final ApiService apiService;
 private static final Logger LOGGER = Logger.getLogger(ApiServiceImplementation.class.getName());
    public ApiServiceImplementation() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:5001/") // Update with your backend URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static <T> void handleApiCall(Call<T> call, Callback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    LOGGER.log(Level.INFO, "API call successful, URL: {0}", call.request().url());
                    callback.onResponse(call, response);
                } else {
                    LOGGER.log(Level.SEVERE, "API response error, URL: {0}, Code: {1}, Message: {2}",
                            new Object[]{call.request().url(), response.code(), response.message()});
                    if (response.errorBody() != null) {
                        try {
                            LOGGER.log(Level.SEVERE, "Error Body: {0}", response.errorBody().string());
                        } catch (IOException e) {
                            LOGGER.log(Level.WARNING, "Error reading error body", e);
                        }
                    }
                    callback.onFailure(call, new Throwable("API response error: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                LOGGER.log(Level.SEVERE, "API call failure, URL: {0}, Error: {1}",
                        new Object[]{call.request().url(), t.getMessage()});
                callback.onFailure(call, t);
            }
        });
    }

    @Override
    public Call<AuthResponse> login(AuthRequest request) {
        return apiService.login(request);
    }

    @Override
    public Call<Void> logAttendance(AttendanceRequest request) {
        return apiService.logAttendance(request);
    }

    @Override
    public Call<List<Visitor>> getAllVisitors() {
        return apiService.getAllVisitors();
    }

    @Override
    public Call<Visitor> createVisitor(Visitor visitor) {
        return apiService.createVisitor(visitor);
    }

    @Override
    public Call<Visitor> updateVisitor(int id, Visitor visitor) {
        return apiService.updateVisitor(id, visitor);
    }

    @Override
    public Call<Void> deleteVisitor(int id) {
        return apiService.deleteVisitor(id);
    }

    @Override
    public Call<Employee> createEmployee(Employee employee) {
        return apiService.createEmployee(employee);
    }

    @Override
    public Call<List<Employee>> getAllEmployees() {
        return apiService.getAllEmployees();
    }

    @Override
    public Call<Employee> updateEmployee(int id, Employee employee) {
        return apiService.updateEmployee(id, employee);
    }

    @Override
    public Call<Void> deleteEmployee(int id) {
        return apiService.deleteEmployee(id);
    }

    @Override
    public Call<Incident> createIncident(Incident incident) {
        return apiService.createIncident(incident);
    }

    @Override
    public Call<List<Incident>> getAllIncidents() {
        return apiService.getAllIncidents();
    }

    @Override
    public Call<Incident> getIncidentById(int id) {
        return apiService.getIncidentById(id);
    }

    @Override
    public Call<Incident> updateIncident(int id, Incident incident) {
        return apiService.updateIncident(id, incident);
    }

    @Override
    public Call<Void> deleteIncident(int id) {
        return apiService.deleteIncident(id);
    }

    @Override
    public Call<Learner> createLearner(Learner learner) {
        return apiService.createLearner(learner);
    }

    @Override
    public Call<List<Learner>> getAllLearners() {
         return apiService.getAllLearners();
    }

    @Override
    public Call<Learner> updateLearner(int id, Learner learner) {
         return apiService.updateLearner(id, learner);
    }

    @Override
    public Call<Void> deleteLearner(int id) {
        return apiService.deleteLearner(id);
    }
}
