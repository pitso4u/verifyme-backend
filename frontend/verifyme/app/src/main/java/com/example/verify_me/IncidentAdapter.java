package com.example.verify_me;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder> {

    private List<Incident> incidentList;

    public IncidentAdapter(List<Incident> incidentList) {
        this.incidentList = incidentList;
    }

    @NonNull
    @Override
    public IncidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_incident, parent, false);
        return new IncidentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncidentViewHolder holder, int position) {
        Incident incident = incidentList.get(position);
        holder.textViewDescription.setText(incident.getDescription());
        holder.textViewTimestamp.setText(incident.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return incidentList.size();
    }

    public static class IncidentViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewDescription;
        public TextView textViewTimestamp;

        public IncidentViewHolder(View itemView) {
            super(itemView);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
        }
    }
} 