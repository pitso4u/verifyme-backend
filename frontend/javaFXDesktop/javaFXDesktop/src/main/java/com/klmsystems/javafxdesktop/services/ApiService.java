package com.klmsystems.javafxdesktop.services;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import com.klmsystems.javafxdesktop.models.AttendanceRequest;
import com.klmsystems.javafxdesktop.models.AuthRequest;
import com.klmsystems.javafxdesktop.models.AuthResponse;
import com.klmsystems.javafxdesktop.models.Employee;
import com.klmsystems.javafxdesktop.models.Incident;
import com.klmsystems.javafxdesktop.models.Learner;
import com.klmsystems.javafxdesktop.models.Visitor;

public interface ApiService {
    @POST("/login")
    Call<AuthResponse> login(@Body AuthRequest request);

    @POST("/logAttendance")
    Call<Void> logAttendance(@Body AttendanceRequest request);

    // Visitor API methods
    @GET("/visitors")
    Call<List<Visitor>> getAllVisitors();

    @POST("/visitors")
    Call<Visitor> createVisitor(@Body Visitor visitor);

    @PUT("/visitors/{id}")
    Call<Visitor> updateVisitor(@Path("id") int id, @Body Visitor visitor);

    @DELETE("/visitors/{id}")
    Call<Void> deleteVisitor(@Path("id") int id);

    // Employee API methods
    @POST("/employees")
    Call<Employee> createEmployee(@Body Employee employee);

    @GET("/employees")
    Call<List<Employee>> getAllEmployees();

    @PUT("/employees/{id}")
    Call<Employee> updateEmployee(@Path("id") int id, @Body Employee employee);

    @DELETE("/employees/{id}")
    Call<Void> deleteEmployee(@Path("id") int id);

    // Learner API methods
    @POST("/learners")
    Call<Learner> createLearner(@Body Learner learner);

    @GET("/learners")
    Call<List<Learner>> getAllLearners();

    @PUT("/learners/{id}")
    Call<Learner> updateLearner(@Path("id") int id, @Body Learner learner);

    @DELETE("/learners/{id}")
    Call<Void> deleteLearner(@Path("id") int id);

    // Incident API methods
    @POST("/incidents")
    Call<Incident> createIncident(@Body Incident incident);

    @GET("/incidents")
    Call<List<Incident>> getAllIncidents();

    @GET("/incidents/{id}")
    Call<Incident> getIncidentById(@Path("id") int id);

    @PUT("/incidents/{id}")
    Call<Incident> updateIncident(@Path("id") int id, @Body Incident incident);

    @DELETE("/incidents/{id}")
    Call<Void> deleteIncident(@Path("id") int id);
}
