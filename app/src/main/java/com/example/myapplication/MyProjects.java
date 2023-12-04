package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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

        // Получаем данные из базы данных
        List<Project> projects = dbHelper.getAllProjects();

        // Находим RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Создаем и устанавливаем адаптер
        adapter = new MyProjectsAdapter(projects);
        recyclerView.setAdapter(adapter);

        // Устанавливаем менеджер компоновки
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Находим кнопки
        Button deleteLastButton = findViewById(R.id.btnDeleteLast);
        Button deleteAllButton = findViewById(R.id.btnDeleteAll);

        // Устанавливаем обработчики для кнопок
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
}



