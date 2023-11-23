package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RoofCalculator2 extends AppCompatActivity {

    private EditText lengthEditText2;
    private EditText widthEditText2;
    private Button calculateButton2;
    private TextView resultTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roof_calculator2);

        lengthEditText2 = findViewById(R.id.editTextLength2);
        widthEditText2 = findViewById(R.id.editTextWidth2);
        calculateButton2 = findViewById(R.id.buttonCalculate2);
        resultTextView2 = findViewById(R.id.textViewResult2);

        calculateButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateRoofArea();
            }
        });
    }

    private void calculateRoofArea() {
        // Получаем значения длины и ширины крыши
        String lengthStr = lengthEditText2.getText().toString();
        String widthStr = widthEditText2.getText().toString();

        // Проверяем, не пусты ли введенные значения
        if (lengthStr.isEmpty() || widthStr.isEmpty()) {
            resultTextView2.setText("Введите значения для длины и ширины крыши");
            return;
        }

        // Преобразуем строки в числа
        double length = Double.parseDouble(lengthStr);
        double width = Double.parseDouble(widthStr);

        // Рассчитываем площадь крыши
        double area = length * width;

        // Выводим результат
        resultTextView2.setText("Площадь крыши: " + area + " кв. м");
    }
}
