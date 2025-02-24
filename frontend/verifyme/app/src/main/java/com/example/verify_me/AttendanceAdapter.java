package com.example.verify_me;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {
    private List<Attendance> attendanceList;

    public AttendanceAdapter(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendance, parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        Attendance attendance = attendanceList.get(position);
        holder.textViewStudentId.setText(attendance.getStudentId());
        holder.textViewDate.setText(attendance.getDate());
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public void updateAttendance(List<Attendance> newAttendance) {
        this.attendanceList = newAttendance;
        notifyDataSetChanged();
    }

    static class AttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView textViewStudentId;
        TextView textViewDate;

        AttendanceViewHolder(View itemView) {
            super(itemView);
            textViewStudentId = itemView.findViewById(R.id.textViewStudentId);
            textViewDate = itemView.findViewById(R.id.textViewDate);
        }
    }
} 