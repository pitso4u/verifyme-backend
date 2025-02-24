package com.example.verify_me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {

    private Button buttonManageUsers;
    private Button buttonManageEmployees;
    private Button buttonManageLearners;
    private Button buttonManageVisitors;
    private Button buttonManageIncidents;
    private Button buttonManageAttendance;

    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Initialize buttons
        buttonManageUsers = root.findViewById(R.id.buttonManageUsers);
        buttonManageEmployees = root.findViewById(R.id.buttonManageEmployees);
        buttonManageLearners = root.findViewById(R.id.buttonManageLearners);
        buttonManageVisitors = root.findViewById(R.id.buttonManageVisitors);
        buttonManageIncidents = root.findViewById(R.id.buttonManageIncidents);
        buttonManageAttendance = root.findViewById(R.id.buttonManageAttendance);

        // Set click listeners
        buttonManageUsers.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UserManagementActivity.class);
            startActivity(intent);
        });

        buttonManageEmployees.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EmployeeManagementActivity.class);
            startActivity(intent);
        });

        buttonManageLearners.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LearnerManagementActivity.class);
            startActivity(intent);
        });

        buttonManageVisitors.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), VisitorManagementActivity.class);
            startActivity(intent);
        });

        buttonManageIncidents.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), IncidentManagementActivity.class);
            startActivity(intent);
        });

        buttonManageAttendance.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AttendanceManagementActivity.class);
            startActivity(intent);
        });

        return root;
    }
}