package com.example.verify_me;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllIncidentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private IncidentAdapter incidentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_incidents);

        recyclerView = findViewById(R.id.recyclerViewIncidents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchAllIncidents();
    }

    private void fetchAllIncidents() {
        ApiService apiService = RetrofitClient.getClient(Config.BASE_URL).create(ApiService.class);
        apiService.getAllIncidents().enqueue(new Callback<List<Incident>>() {
            @Override
            public void onResponse(Call<List<Incident>> call, Response<List<Incident>> response) {
                if (response.isSuccessful()) {
                    List<Incident> incidents = response.body();
                    incidentAdapter = new IncidentAdapter(incidents);
                    recyclerView.setAdapter(incidentAdapter);
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<List<Incident>> call, Throwable t) {
                // Handle failure
            }
        });
    }
} 