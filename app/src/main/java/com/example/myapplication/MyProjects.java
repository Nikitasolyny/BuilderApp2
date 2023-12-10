package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class MyProjects extends AppCompatActivity {

    private MyProjectsAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_projects);

        dbHelper = new DatabaseHelper(this);

        List<Project> projects = dbHelper.getAllProjects();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        adapter = new MyProjectsAdapter(projects);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button deleteLastButton = findViewById(R.id.btnDeleteLast);
        Button deleteAllButton = findViewById(R.id.btnDeleteAll);

        deleteLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLastData();
            }
        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllData();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void deleteLastData() {
        int rowsDeleted = dbHelper.deleteLastData();
        if (rowsDeleted > 0) {
            Toast.makeText(this, "Last entry deleted", Toast.LENGTH_SHORT).show();
            updateRecyclerView();
        } else {
            Toast.makeText(this, "No entries to delete", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteAllData() {
        int rowsDeleted = dbHelper.deleteAllData();
        if (rowsDeleted > 0) {
            Toast.makeText(this, "All entries deleted", Toast.LENGTH_SHORT).show();
            updateRecyclerView();
        } else {
            Toast.makeText(this, "No entries to delete", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateRecyclerView() {
        List<Project> updatedProjects = dbHelper.getAllProjects();
        adapter.setProjects(updatedProjects);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



