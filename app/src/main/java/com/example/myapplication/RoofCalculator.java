package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.Math;



public class RoofCalculator extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText lengthEditText;
    private EditText widthEditText;
    private EditText nameEditText;
    private EditText heightEditText;
    private EditText sves1;

    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roof_calculator);
        databaseHelper = new DatabaseHelper(this);
        lengthEditText = findViewById(R.id.editTextLength);
        widthEditText = findViewById(R.id.editTextWidth);
        nameEditText = findViewById(R.id.editTextName);
        heightEditText = findViewById(R.id.editTextHeight);
        sves1 = findViewById(R.id.sves1);
        calculateButton = findViewById(R.id.buttonCalculate);
        resultTextView = findViewById(R.id.textViewResult);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateRoofArea();
                Intent intent = new Intent(RoofCalculator.this, MyProjects.class);

                // Запускаем новую активити
                startActivity(intent);
            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(this, "Проект сохранен" , Toast.LENGTH_SHORT).show();
    }
    private void calculateRoofArea() {
        // Получаем значения длины и ширины крыши
        String lengthStr = lengthEditText.getText().toString();
        String widthStr = widthEditText.getText().toString();
        String sves1Str = sves1.getText().toString();
        String heightStr = heightEditText.getText().toString();
        String nameStr = nameEditText.getText().toString();

        // Проверяем, не пусты ли введенные значения
        if (lengthStr.isEmpty() || widthStr.isEmpty() || nameStr.isEmpty() || sves1Str.isEmpty()  || heightStr.isEmpty()) {
            resultTextView.setText("Введите все значения");
            return;
        }

        // Преобразуем строки в числа
        double length = Double.parseDouble(lengthStr);
        double width = Double.parseDouble(widthStr);
        double height = Double.parseDouble(heightStr);
        double d = Double.parseDouble(sves1Str);
        double area = (Math.sqrt(Math.pow(height, 2) + Math.pow(length, 2)) + 2 *d) * (width + 2 * d);

        // Добавление данных в базу данных
        long newRowId = databaseHelper.insertData(nameStr,width,length);
        if (newRowId != -1) {
            showToast("Data added, Row ID: " + newRowId);
        } else {
            showToast("Error adding data");
        }

        // Выводим результат
        resultTextView.setText("Площадь крыши: " + area + " кв. м");
    }
}
