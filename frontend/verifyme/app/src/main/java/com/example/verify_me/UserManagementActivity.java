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

public class UserManagementActivity extends AppCompatActivity {

    private EditText editTextUserId, editTextUsername, editTextPassword, editTextEmail;
    private Button buttonAddUser, buttonUpdateUser, buttonDeleteUser;
    private ListView listViewUsers;
    private ApiService apiService;
    private ArrayAdapter<String> adapter;
    private List<String> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        editTextUserId = findViewById(R.id.editTextUserId);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonAddUser = findViewById(R.id.buttonAddUser);
        buttonUpdateUser = findViewById(R.id.buttonUpdateUser);
        buttonDeleteUser = findViewById(R.id.buttonDeleteUser);
        listViewUsers = findViewById(R.id.listViewUsers);

        apiService = RetrofitClient.getClient(Config.BASE_URL).create(ApiService.class);
        userList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        listViewUsers.setAdapter(adapter);

        buttonAddUser.setOnClickListener(v -> addUser());
        buttonUpdateUser.setOnClickListener(v -> updateUser());
        buttonDeleteUser.setOnClickListener(v -> deleteUser());

        fetchAllUsers();
    }

    private void addUser() {
        User newUser = new User();
        newUser.setUsername(editTextUsername.getText().toString());
        newUser.setPassword(editTextPassword.getText().toString());
        newUser.setEmail(editTextEmail.getText().toString());

        apiService.createUser(newUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UserManagementActivity.this, "User added", Toast.LENGTH_SHORT).show();
                    fetchAllUsers();
                } else {
                    Log.e("UserManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("UserManagement", "Failure: " + t.getMessage());
            }
        });
    }

    private void fetchAllUsers() {
        apiService.getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    userList.clear();
                    for (User user : response.body()) {
                        userList.add("User ID: " + user.getId() + ", Username: " + user.getUsername() + ", Email: " + user.getEmail());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("UserManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("UserManagement", "Failure: " + t.getMessage());
            }
        });
    }

    private void updateUser() {
        int userId = Integer.parseInt(editTextUserId.getText().toString());
        User updatedUser = new User();
        updatedUser.setUsername(editTextUsername.getText().toString());
        updatedUser.setPassword(editTextPassword.getText().toString());
        updatedUser.setEmail(editTextEmail.getText().toString());

        apiService.updateUser(userId, updatedUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UserManagementActivity.this, "User updated", Toast.LENGTH_SHORT).show();
                    fetchAllUsers();
                } else {
                    Log.e("UserManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("UserManagement", "Failure: " + t.getMessage());
            }
        });
    }

    private void deleteUser() {
        int userId = Integer.parseInt(editTextUserId.getText().toString());

        apiService.deleteUser(userId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UserManagementActivity.this, "User deleted", Toast.LENGTH_SHORT).show();
                    fetchAllUsers();
                } else {
                    Log.e("UserManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("UserManagement", "Failure: " + t.getMessage());
            }
        });
    }
} 