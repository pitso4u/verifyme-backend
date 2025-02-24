package com.example.verify_me;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllEmployeesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employees);

        recyclerView = findViewById(R.id.recyclerViewEmployees);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchAllEmployees();
    }

    private void fetchAllEmployees() {
        ApiService apiService = RetrofitClient.getClient(Config.BASE_URL).create(ApiService.class);
        apiService.getAllEmployees().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()) {
                    List<Employee> employees = response.body();
                    employeeAdapter = new EmployeeAdapter(employees);
                    recyclerView.setAdapter(employeeAdapter);
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                // Handle failure
            }
        });
    }
} 