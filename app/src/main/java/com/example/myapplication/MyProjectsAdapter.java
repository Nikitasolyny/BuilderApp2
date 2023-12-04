package com.example.myapplication;

import android.util.Log;
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

        holder.typeTextView.setText("Type: " + project.getType());
        holder.heightTextView.setText("Height: " + project.getHeight());
        holder.dTextView.setText("D: " + project.getD());
        holder.cTextView.setText("C: " + project.getC());
        holder.sTextView.setText("S: " + project.getS());
        Log.d("MyProjectsAdapter", "Binding project at position " + position);
    }
    @Override
    public int getItemCount() {
        if (projects == null) {
            return 0;
        }
        return projects.size();
    }
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView widthTextView;
        TextView lengthTextView;
        TextView squareTextView;
        TextView typeTextView;
        TextView heightTextView;
        TextView dTextView;
        TextView cTextView;
        TextView sTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            widthTextView = itemView.findViewById(R.id.widthTextView);
            lengthTextView = itemView.findViewById(R.id.lengthTextView);
            squareTextView = itemView.findViewById(R.id.squareTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            heightTextView = itemView.findViewById(R.id.heightTextView);
            dTextView = itemView.findViewById(R.id.dTextView);
            cTextView = itemView.findViewById(R.id.cTextView);
            sTextView = itemView.findViewById(R.id.sTextView);
        }
    }
}
