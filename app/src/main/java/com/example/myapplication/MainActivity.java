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

        // Находим кнопку по идентификатору
        Button buttonGoToRoofCalculator = findViewById(R.id.buttonGoToRoofCalculator);

        // Устанавливаем обработчик события при нажатии на кнопку
        buttonGoToRoofCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для перехода к RoofCalculatorActivity
                Intent intent = new Intent(MainActivity.this, ChooseFunction.class);

                // Запускаем новую активити
                startActivity(intent);
            }



        });

        Button buttonGoToRoofGuide = findViewById(R.id.buttonGoToRoofGuide);

        // Устанавливаем обработчик события при нажатии на кнопку
        buttonGoToRoofGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для перехода к RoofCalculatorActivity
                Intent intent = new Intent(MainActivity.this, RoofGuide.class);

                // Запускаем новую активити
                startActivity(intent);
            }



        });

        Button buttonGoToMyProjects = findViewById(R.id.buttonGoToMyProjects);

        // Устанавливаем обработчик события при нажатии на кнопку
        buttonGoToMyProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем Intent для перехода к RoofCalculatorActivity
                Intent intent = new Intent(MainActivity.this, MyProjects.class);

                // Запускаем новую активити
                startActivity(intent);
            }



        });
    }
}
