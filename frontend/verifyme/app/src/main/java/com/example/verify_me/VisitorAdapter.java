package com.example.verify_me;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.VisitorViewHolder> {

    private List<Visitor> visitorList;

    public VisitorAdapter(List<Visitor> visitorList) {
        this.visitorList = visitorList;
    }

    @NonNull
    @Override
    public VisitorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visitor, parent, false);
        return new VisitorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitorViewHolder holder, int position) {
        Visitor visitor = visitorList.get(position);
        holder.textViewUsername.setText(visitor.getUsername());
        holder.textViewEmail.setText(visitor.getEmail());
    }

    @Override
    public int getItemCount() {
        return visitorList.size();
    }

    public static class VisitorViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewUsername;
        public TextView textViewEmail;

        public VisitorViewHolder(View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
        }
    }
} 