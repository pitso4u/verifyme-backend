package com.example.verify_me;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    // Endpoint to create a new user
    @POST("/api/users")
    Call<User> createUser(@Body User user);

    // Endpoint to get all users
    @GET("/api/users")
    Call<List<User>> getAllUsers();

    // Endpoint to create a new employee
    @POST("/api/employees")
    Call<Employee> createEmployee(@Body Employee employee);

    // Endpoint to get all employees
    @GET("/api/employees")
    Call<List<Employee>> getAllEmployees();

    // Endpoint to create a new learner
    @POST("/api/learners")
    Call<Learner> createLearner(@Body Learner learner);

    // Endpoint to get all learners
    @GET("/api/learners")
    Call<List<Learner>> getAllLearners();

    // Endpoint to update a learner
    @PUT("/api/learners/{id}")
    Call<Learner> updateLearner(@Path("id") int id, @Body Learner learner);

    // Endpoint to delete a learner
    @DELETE("/api/learners/{id}")
    Call<Void> deleteLearner(@Path("id") int id);

    // Endpoint to update an employee
    @PUT("/api/employees/{id}")
    Call<Employee> updateEmployee(@Path("id") int id, @Body Employee employee);

    // Endpoint to delete an employee
    @DELETE("/api/employees/{id}")
    Call<Void> deleteEmployee(@Path("id") int id);

    // Endpoint to create a new visitor
    @POST("/api/visitors")
    Call<Visitor> createVisitor(@Body Visitor visitor);

    // Endpoint to get all visitors
    @GET("/api/visitors")
    Call<List<Visitor>> getAllVisitors();

    // Endpoint to update a visitor
    @PUT("/api/visitors/{id}")
    Call<Visitor> updateVisitor(@Path("id") int id, @Body Visitor visitor);

    // Endpoint to delete a visitor
    @DELETE("/api/visitors/{id}")
    Call<Void> deleteVisitor(@Path("id") int id);

    // Endpoint to create a new incident
    @POST("/api/incidents")
    Call<Incident> createIncident(@Body Incident incident);

    // Endpoint to get all incidents
    @GET("/api/incidents")
    Call<List<Incident>> getAllIncidents();

    // Endpoint to update an incident
    @PUT("/api/incidents/{id}")
    Call<Incident> updateIncident(@Path("id") int id, @Body Incident incident);

    // Endpoint to delete an incident
    @DELETE("/api/incidents/{id}")
    Call<Void> deleteIncident(@Path("id") int id);

    // Endpoint to create a new attendance
    @POST("/api/attendances")
    Call<Attendance> createAttendance(@Body Attendance attendance);

    // Endpoint to get all attendances
    @GET("/api/attendances")
    Call<List<Attendance>> getAllAttendance();

    // Endpoint to update an attendance
    @PUT("/api/attendances/{id}")
    Call<Attendance> updateAttendance(@Path("id") int id, @Body Attendance attendance);

    // Endpoint to delete an attendance
    @DELETE("/api/attendances/{id}")
    Call<Void> deleteAttendance(@Path("id") int id);

    // Endpoint to update a user
    @PUT("/api/users/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    // Endpoint to delete a user
    @DELETE("/api/users/{id}")
    Call<Void> deleteUser(@Path("id") int id);

    @GET("api/users/{id}")
    Call<User> getUser(@Path("id") String id);

    // Endpoint to scan QR code
    @POST("/api/scan-qr")
    Call<Void> scanQr(@Query("learnerId") String learnerId, @Query("timestamp") String timestamp, @Query("method") String method);
}