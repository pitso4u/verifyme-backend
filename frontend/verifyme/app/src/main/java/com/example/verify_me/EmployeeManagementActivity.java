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

public class EmployeeManagementActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextDepartment;
    private EditText editTextPosition;
    private EditText editTextRole;
    private Button buttonAddEmployee, buttonUpdateEmployee, buttonDeleteEmployee;
    private ListView listViewEmployees;
    private ApiService apiService;
    private ArrayAdapter<String> adapter;
    private List<String> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_management);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextDepartment = findViewById(R.id.editTextDepartment);
        editTextPosition = findViewById(R.id.editTextPosition);
        editTextRole = findViewById(R.id.editTextRole);
        buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
        buttonUpdateEmployee = findViewById(R.id.buttonUpdateEmployee);
        buttonDeleteEmployee = findViewById(R.id.buttonDeleteEmployee);
        listViewEmployees = findViewById(R.id.listViewEmployees);

        apiService = RetrofitClient.getClient(Config.BASE_URL).create(ApiService.class);
        employeeList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
        listViewEmployees.setAdapter(adapter);

        buttonAddEmployee.setOnClickListener(v -> addEmployee());
        buttonUpdateEmployee.setOnClickListener(v -> updateEmployee());
        buttonDeleteEmployee.setOnClickListener(v -> deleteEmployee());

        fetchAllEmployees();
    }

    private void addEmployee() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String department = editTextDepartment.getText().toString().trim();
        String position = editTextPosition.getText().toString().trim();
        String role = editTextRole.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || department.isEmpty() || 
            position.isEmpty() || role.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Employee newEmployee = new Employee();
        newEmployee.setUserId(1); // Set a valid user ID here
        newEmployee.setUsername(username);
        newEmployee.setEmail(email);
        newEmployee.setRole(role);
        newEmployee.setDepartment(department);
        newEmployee.setPosition(position);

        apiService.createEmployee(newEmployee).enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    clearInputFields();
                    fetchAllEmployees();
                    Toast.makeText(EmployeeManagementActivity.this, "Employee added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EmployeeManagementActivity.this, "Failed to add employee: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(EmployeeManagementActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchAllEmployees() {
        apiService.getAllEmployees().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()) {
                    employeeList.clear();
                    for (Employee employee : response.body()) {
                        employeeList.add("User ID: " + employee.getId() + ", Department: " + employee.getDepartment());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("EmployeeManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.e("EmployeeManagement", "Failure: " + t.getMessage());
            }
        });
    }

    private void updateEmployee() {
        int employeeId = Integer.parseInt(editTextUsername.getText().toString());
        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(String.valueOf(employeeId));
        updatedEmployee.setDepartment(editTextDepartment.getText().toString());

        apiService.updateEmployee(employeeId, updatedEmployee).enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EmployeeManagementActivity.this, "Employee updated", Toast.LENGTH_SHORT).show();
                    fetchAllEmployees();
                } else {
                    Log.e("EmployeeManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.e("EmployeeManagement", "Failure: " + t.getMessage());
            }
        });
    }

    private void deleteEmployee() {
        int employeeId = Integer.parseInt(editTextUsername.getText().toString());

        apiService.deleteEmployee(employeeId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EmployeeManagementActivity.this, "Employee deleted", Toast.LENGTH_SHORT).show();
                    fetchAllEmployees();
                } else {
                    Log.e("EmployeeManagement", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("EmployeeManagement", "Failure: " + t.getMessage());
            }
        });
    }

    private void clearInputFields() {
        editTextUsername.setText("");
        editTextEmail.setText("");
        editTextDepartment.setText("");
        editTextPosition.setText("");
        editTextRole.setText("");
    }
} 