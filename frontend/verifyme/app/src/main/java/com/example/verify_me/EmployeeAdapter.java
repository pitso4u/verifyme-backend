package com.example.verify_me;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employeeList;
    private OnEmployeeClickListener listener;

    public interface OnEmployeeClickListener {
        void onEmployeeClick(Employee employee);
    }

    public EmployeeAdapter(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.textViewUsername.setText(employee.getUsername());
        holder.textViewEmail.setText(employee.getEmail());
        holder.textViewDepartment.setText(employee.getDepartment());
        holder.textViewPosition.setText(employee.getPosition());

        if (listener != null) {
            holder.itemView.setOnClickListener(v -> listener.onEmployeeClick(employee));
        }
    }

    @Override
    public int getItemCount() {
        return employeeList != null ? employeeList.size() : 0;
    }

    public void updateEmployees(List<Employee> newEmployees) {
        this.employeeList = newEmployees;
        notifyDataSetChanged();
    }

    public void setOnEmployeeClickListener(OnEmployeeClickListener listener) {
        this.listener = listener;
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewUsername;
        public TextView textViewEmail;
        public TextView textViewDepartment;
        public TextView textViewPosition;

        public EmployeeViewHolder(View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewDepartment = itemView.findViewById(R.id.textViewDepartment);
            textViewPosition = itemView.findViewById(R.id.textViewPosition);
        }
    }
} 