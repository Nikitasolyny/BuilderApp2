package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyProjects extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_projects);

        // Получаем данные из базы данных
        List<Project> projects = getProjectsFromDatabase();

        // Находим RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Создаем и устанавливаем адаптер
        MyProjectsAdapter adapter = new MyProjectsAdapter(projects);
        recyclerView.setAdapter(adapter);

        // Устанавливаем менеджер компоновки
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Project> getProjectsFromDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        return dbHelper.getAllProjects();
    }
}

