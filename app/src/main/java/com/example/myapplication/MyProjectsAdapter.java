package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyProjectsAdapter extends RecyclerView.Adapter<MyProjectsAdapter.ViewHolder> {

    private List<Project> projects;

    public MyProjectsAdapter(List<Project> projects) {
        this.projects = projects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.nameTextView.setText("Name: " + project.getName());
        holder.widthTextView.setText("Width: " + project.getWidth());
        holder.lengthTextView.setText("Length: " + project.getLength());
        holder.squareTextView.setText("Square: " + project.getSquare());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView widthTextView;
        TextView lengthTextView;
        TextView squareTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            widthTextView = itemView.findViewById(R.id.widthTextView);
            lengthTextView = itemView.findViewById(R.id.lengthTextView);
            squareTextView = itemView.findViewById(R.id.squareTextView);
        }
    }
}
