package com.klmsystems.javafxdesktop.services;

import com.klmsystems.javafxdesktop.models.AttendanceRequest;
import com.klmsystems.javafxdesktop.models.AuthRequest;
import com.klmsystems.javafxdesktop.models.AuthResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;

public class AuthService {
    public static final String BASE_URL = "http://localhost:5001/api";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final ApiService apiService = retrofit.create(ApiService.class);

    public static Call<AuthResponse> authenticateUser(String username, String password) {
        AuthRequest request = new AuthRequest(username, password);
        return apiService.login(request);
    }

    public static Call<Void> logAttendance(String qrCodeData) {
        AttendanceRequest request = new AttendanceRequest(qrCodeData);
        return apiService.logAttendance(request);
    }
}
