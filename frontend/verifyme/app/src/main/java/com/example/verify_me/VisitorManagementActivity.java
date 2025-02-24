package com.example.verify_me;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

public class VisitorManagementActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPurpose;
    private EditText editTextVisitDate;
    private EditText editTextStatus;
    private Button buttonAddVisitor;
    private ListView listViewVisitors;
    private ApiService apiService;
    private ArrayAdapter<String> adapter;
    private List<String> visitorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_management);

        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPurpose = findViewById(R.id.editTextPurpose);
        editTextVisitDate = findViewById(R.id.editTextVisitDate);
        editTextStatus = findViewById(R.id.editTextStatus);
        buttonAddVisitor = findViewById(R.id.buttonAddVisitor);
        listViewVisitors = findViewById(R.id.listViewVisitors);

        // Initialize API service and list
        apiService = RetrofitClient.getClient(Config.BASE_URL).create(ApiService.class);
        visitorList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, visitorList);
        listViewVisitors.setAdapter(adapter);

        // Set click listener
        buttonAddVisitor.setOnClickListener(v -> addVisitor());

        // Load initial data
        fetchAllVisitors();
    }

    private void addVisitor() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String purpose = editTextPurpose.getText().toString().trim();
        String visitDate = editTextVisitDate.getText().toString().trim();
        String status = editTextStatus.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || purpose.isEmpty() || 
            visitDate.isEmpty() || status.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Visitor newVisitor = new Visitor(
            0, // ID will be assigned by server
            username,
            email,
            purpose,
            visitDate,
            status
        );

        apiService.createVisitor(newVisitor).enqueue(new Callback<Visitor>() {
            @Override
            public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                if (response.isSuccessful()) {
                    clearInputFields();
                    fetchAllVisitors();
                    Toast.makeText(VisitorManagementActivity.this, "Visitor added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VisitorManagementActivity.this, "Failed to add visitor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Visitor> call, Throwable t) {
                Toast.makeText(VisitorManagementActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchAllVisitors() {
        apiService.getAllVisitors().enqueue(new Callback<List<Visitor>>() {
            @Override
            public void onResponse(Call<List<Visitor>> call, Response<List<Visitor>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    visitorList.clear();
                    for (Visitor visitor : response.body()) {
                        visitorList.add(String.format("Name: %s, Purpose: %s, Date: %s", 
                            visitor.getUsername(), visitor.getPurpose(), visitor.getVisitDate()));
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Visitor>> call, Throwable t) {
                Toast.makeText(VisitorManagementActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearInputFields() {
        editTextUsername.setText("");
        editTextEmail.setText("");
        editTextPurpose.setText("");
        editTextVisitDate.setText("");
        editTextStatus.setText("");
    }
} 