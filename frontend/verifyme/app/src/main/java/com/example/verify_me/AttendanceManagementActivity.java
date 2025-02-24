package com.example.verify_me;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceManagementActivity extends AppCompatActivity {
    private RecyclerView recyclerViewAttendance;
    private AttendanceAdapter attendanceAdapter;
    private Button buttonAddAttendance;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_management);

        // Initialize views
        recyclerViewAttendance = findViewById(R.id.recyclerViewAttendance);
        buttonAddAttendance = findViewById(R.id.buttonAddAttendance);

        // Setup RecyclerView
        recyclerViewAttendance.setLayoutManager(new LinearLayoutManager(this));
        attendanceAdapter = new AttendanceAdapter(new ArrayList<>());
        recyclerViewAttendance.setAdapter(attendanceAdapter);

        // Initialize Retrofit and API service
        apiService = RetrofitClient.getClient(Config.getBaseUrl()).create(ApiService.class);

        // Load attendance data
        loadAttendanceData();

        // Setup add button click listener
        buttonAddAttendance.setOnClickListener(v -> showAddAttendanceDialog());
    }

    private void loadAttendanceData() {
        Call<List<Attendance>> call = apiService.getAllAttendance();
        call.enqueue(new Callback<List<Attendance>>() {
            @Override
            public void onResponse(Call<List<Attendance>> call, Response<List<Attendance>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    attendanceAdapter.updateAttendance(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Attendance>> call, Throwable t) {
                // Handle error
            }
        });
    }

    private void showAddAttendanceDialog() {
        // Implement dialog to add new attendance
        // This could be a new activity or dialog fragment
    }
} 