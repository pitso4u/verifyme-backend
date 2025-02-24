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

public class LearnerManagementActivity extends AppCompatActivity {

    private EditText editTextUserId, editTextCourse, editTextEnrollmentDate;
    private Button buttonAddLearner, buttonUpdateLearner, buttonDeleteLearner;
    private ListView listViewLearners;
    private ApiService apiService;
    private ArrayAdapter<String> adapter;
    private List<String> learnerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner_management);

        editTextUserId = findViewById(R.id.editTextUserId);
        editTextCourse = findViewById(R.id.editTextCourse);
        editTextEnrollmentDate = findViewById(R.id.editTextEnrollmentDate);
        buttonAddLearner = findViewById(R.id.buttonAddLearner);
        buttonUpdateLearner = findViewById(R.id.buttonUpdateLearner);
        buttonDeleteLearner = findViewById(R.id.buttonDeleteLearner);
        listViewLearners = findViewById(R.id.listViewLearners);

        apiService = RetrofitClient.getClient(Config.BASE_URL).create(ApiService.class);
        learnerList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, learnerList);
        listViewLearners.setAdapter(adapter);

        buttonAddLearner.setOnClickListener(v -> addLearner());
        buttonUpdateLearner.setOnClickListener(v -> updateLearner());
        buttonDeleteLearner.setOnClickListener(v -> deleteLearner());
        
        fetchAllLearners();
    }

    private void addLearner() {
        Learner newLearner = new Learner();
        newLearner.setId(Integer.parseInt(editTextUserId.getText().toString()));
        newLearner.setCourse(editTextCourse.getText().toString());
        newLearner.setEnrollmentDate(editTextEnrollmentDate.getText().toString());

        apiService.createLearner(newLearner).enqueue(new Callback<Learner>() {
            @Override
            public void onResponse(Call<Learner> call, Response<Learner> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LearnerManagementActivity.this, "Learner added", Toast.LENGTH_SHORT).show();
                    fetchAllLearners();
                } else {
                    Log.e("LearnerManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Learner> call, Throwable t) {
                Log.e("LearnerManagement", "Failure: " + t.getMessage());
            }
        });
    }

    private void fetchAllLearners() {
        apiService.getAllLearners().enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {
                if (response.isSuccessful()) {
                    learnerList.clear();
                    for (Learner learner : response.body()) {
                        learnerList.add("User ID: " + learner.getId() + ", Course: " + learner.getCourse());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("LearnerManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {
                Log.e("LearnerManagement", "Failure: " + t.getMessage());
            }
        });
    }

    private void updateLearner() {
        int learnerId = Integer.parseInt(editTextUserId.getText().toString());
        Learner updatedLearner = new Learner();
        updatedLearner.setId(learnerId);
        updatedLearner.setCourse(editTextCourse.getText().toString());
        updatedLearner.setEnrollmentDate(editTextEnrollmentDate.getText().toString());

        apiService.updateLearner(learnerId, updatedLearner).enqueue(new Callback<Learner>() {
            @Override
            public void onResponse(Call<Learner> call, Response<Learner> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LearnerManagementActivity.this, "Learner updated", Toast.LENGTH_SHORT).show();
                    fetchAllLearners();
                } else {
                    Log.e("LearnerManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Learner> call, Throwable t) {
                Log.e("LearnerManagement", "Failure: " + t.getMessage());
            }
        });
    }

    private void deleteLearner() {
        int learnerId = Integer.parseInt(editTextUserId.getText().toString());

        apiService.deleteLearner(learnerId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LearnerManagementActivity.this, "Learner deleted", Toast.LENGTH_SHORT).show();
                    fetchAllLearners();
                } else {
                    Log.e("LearnerManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("LearnerManagement", "Failure: " + t.getMessage());
            }
        });
    }
} 