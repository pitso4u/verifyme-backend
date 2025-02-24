package com.example.verify_me;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LearnerAdapter extends RecyclerView.Adapter<LearnerAdapter.LearnerViewHolder> {
    private List<Learner> learnerList;
    private OnLearnerClickListener listener;

    public interface OnLearnerClickListener {
        void onLearnerClick(Learner learner);
    }

    public LearnerAdapter(List<Learner> learnerList) {
        this.learnerList = learnerList;
    }

    @NonNull
    @Override
    public LearnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learner, parent, false);
        return new LearnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerViewHolder holder, int position) {
        Learner learner = learnerList.get(position);
        
        // Assuming these are the fields in your Learner class
        holder.textViewName.setText(learner.getName());
        holder.textViewEmail.setText(learner.getEmail());

        if (listener != null) {
            holder.itemView.setOnClickListener(v -> listener.onLearnerClick(learner));
        }
    }

    @Override
    public int getItemCount() {
        return learnerList != null ? learnerList.size() : 0;
    }

    public void updateLearners(List<Learner> newLearners) {
        this.learnerList = newLearners;
        notifyDataSetChanged();
    }

    public void setOnLearnerClickListener(OnLearnerClickListener listener) {
        this.listener = listener;
    }

    static class LearnerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewEmail;

        LearnerViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
        }
    }
}