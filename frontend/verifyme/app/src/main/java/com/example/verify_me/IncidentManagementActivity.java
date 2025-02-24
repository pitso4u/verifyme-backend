package com.example.verify_me;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class IncidentManagementActivity extends AppCompatActivity {

    private EditText editTextIncidentId, editTextDescription;
    private Button buttonAddIncident, buttonUpdateIncident, buttonDeleteIncident;
    private ListView listViewIncidents;
    private ApiService apiService;
    private ArrayAdapter<String> adapter;
    private List<String> incidentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_management);

        editTextIncidentId = findViewById(R.id.editTextIncidentId);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonAddIncident = findViewById(R.id.buttonAddIncident);
        buttonUpdateIncident = findViewById(R.id.buttonUpdateIncident);
        buttonDeleteIncident = findViewById(R.id.buttonDeleteIncident);
        listViewIncidents = findViewById(R.id.listViewIncidents);

        apiService = RetrofitClient.getClient(Config.BASE_URL).create(ApiService.class);
        incidentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, incidentList);
        listViewIncidents.setAdapter(adapter);

        buttonAddIncident.setOnClickListener(v -> addIncident());
        buttonUpdateIncident.setOnClickListener(v -> updateIncident());
        buttonDeleteIncident.setOnClickListener(v -> deleteIncident());

        fetchAllIncidents();
    }

    private void addIncident() {
        Incident newIncident = new Incident();
        newIncident.setIncidentId(Integer.parseInt(editTextIncidentId.getText().toString()));
        newIncident.setDescription(editTextDescription.getText().toString());

        apiService.createIncident(newIncident).enqueue(new Callback<Incident>() {
            @Override
            public void onResponse(Call<Incident> call, Response<Incident> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(IncidentManagementActivity.this, "Incident added", Toast.LENGTH_SHORT).show();
                    fetchAllIncidents();
                } else {
                    Log.e("IncidentManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Incident> call, Throwable t) {
                Log.e("IncidentManagement", "Failure: " + t.getMessage());
            }
        });
    }

    private void fetchAllIncidents() {
        apiService.getAllIncidents().enqueue(new Callback<List<Incident>>() {
            @Override
            public void onResponse(Call<List<Incident>> call, Response<List<Incident>> response) {
                if (response.isSuccessful()) {
                    incidentList.clear();
                    for (Incident incident : response.body()) {
                        incidentList.add("Incident ID: " + incident.getIncidentId() + ", Description: " + incident.getDescription());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("IncidentManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Incident>> call, Throwable t) {
                Log.e("IncidentManagement", "Failure: " + t.getMessage());
            }
        });
    }

    private void updateIncident() {
        int incidentId = Integer.parseInt(editTextIncidentId.getText().toString());
        Incident updatedIncident = new Incident();
        updatedIncident.setIncidentId(incidentId);
        updatedIncident.setDescription(editTextDescription.getText().toString());

        apiService.updateIncident(incidentId, updatedIncident).enqueue(new Callback<Incident>() {
            @Override
            public void onResponse(Call<Incident> call, Response<Incident> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(IncidentManagementActivity.this, "Incident updated", Toast.LENGTH_SHORT).show();
                    fetchAllIncidents();
                } else {
                    Log.e("IncidentManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Incident> call, Throwable t) {
                Log.e("IncidentManagement", "Failure: " + t.getMessage());
            }
        });
    }

    private void deleteIncident() {
        int incidentId = Integer.parseInt(editTextIncidentId.getText().toString());

        apiService.deleteIncident(incidentId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(IncidentManagementActivity.this, "Incident deleted", Toast.LENGTH_SHORT).show();
                    fetchAllIncidents();
                } else {
                    Log.e("IncidentManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("IncidentManagement", "Failure: " + t.getMessage());
            }
        });
    }
} 