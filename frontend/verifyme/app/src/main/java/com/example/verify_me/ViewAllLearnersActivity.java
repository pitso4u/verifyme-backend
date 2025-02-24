package com.example.verify_me;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllLearnersActivity extends AppCompatActivity {
    private RecyclerView recyclerViewLearners;
    private LearnerAdapter learnerAdapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_learners);

        recyclerViewLearners = findViewById(R.id.recyclerViewLearners);
        recyclerViewLearners.setLayoutManager(new LinearLayoutManager(this));
        learnerAdapter = new LearnerAdapter(new ArrayList<>());
        recyclerViewLearners.setAdapter(learnerAdapter);

        apiService = RetrofitClient.getClient(Config.BASE_URL).create(ApiService.class);
        loadLearners();
    }

    private void loadLearners() {
        Call<List<Learner>> call = apiService.getAllLearners();
        call.enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    learnerAdapter.updateLearners(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {
                // Handle error
            }
        });
    }
} 