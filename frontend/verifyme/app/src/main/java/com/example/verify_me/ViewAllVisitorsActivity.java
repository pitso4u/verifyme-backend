package com.example.verify_me;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllVisitorsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VisitorAdapter visitorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_visitors);

        recyclerView = findViewById(R.id.recyclerViewVisitors);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchAllVisitors();
    }

    private void fetchAllVisitors() {
        ApiService apiService = RetrofitClient.getClient(Config.BASE_URL).create(ApiService.class);
        apiService.getAllVisitors().enqueue(new Callback<List<Visitor>>() {
            @Override
            public void onResponse(Call<List<Visitor>> call, Response<List<Visitor>> response) {
                if (response.isSuccessful()) {
                    List<Visitor> visitors = response.body();
                    visitorAdapter = new VisitorAdapter(visitors);
                    recyclerView.setAdapter(visitorAdapter);
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<List<Visitor>> call, Throwable t) {
                // Handle failure
            }
        });
    }
} 