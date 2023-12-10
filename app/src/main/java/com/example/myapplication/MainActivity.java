package com.example.myapplication;

import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonGoToRoofCalculator = findViewById(R.id.buttonGoToRoofCalculator);
        buttonGoToRoofCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseFunction.class);
                startActivity(intent);
            }



        });

        Button buttonGoToRoofGuide = findViewById(R.id.buttonGoToRoofGuide);
        buttonGoToRoofGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RoofGuide.class);
                startActivity(intent);
            }



        });

        Button buttonGoToMyProjects = findViewById(R.id.buttonGoToMyProjects);

        buttonGoToMyProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyProjects.class);
                startActivity(intent);
            }



        });
    }
}
