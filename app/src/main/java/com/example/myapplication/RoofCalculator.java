package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
                if (calculateRoofArea()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(RoofCalculator.this, MyProjects.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);
                }
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean calculateRoofArea() {
        String lengthStr = lengthEditText.getText().toString();
        String widthStr = widthEditText.getText().toString();
        String sves1Str = sves1.getText().toString();
        String heightStr = heightEditText.getText().toString();
        String nameStr = nameEditText.getText().toString();

        if (lengthStr.isEmpty() || widthStr.isEmpty() || nameStr.isEmpty() || sves1Str.isEmpty() || heightStr.isEmpty()) {
            resultTextView.setText("Введите все значения");
            showToast("Введите все значения");
            return false;
        }

        try {
            double length = Double.parseDouble(lengthStr);
            double width = Double.parseDouble(widthStr);
            double height = Double.parseDouble(heightStr);
            double d = Double.parseDouble(sves1Str);

            if (length <= 0 || width <= 0 || height <= 0 || d < 0) {
                resultTextView.setText("Введите корректные значения");
                showToast("Введите корректные значения");
                return false;
            }

            double area = (length + 2 * d) * Math.sqrt(Math.pow(height, 2) + Math.pow(width + 2 * d, 2));

            long newRowId = databaseHelper.insertData(nameStr, "Односкатная", length, width, height, area, d, 0, 0);
            if (newRowId != -1) {
                showToast("Проект сохранен, Row ID: " + newRowId);
                return true;
            } else {
                showToast("Ошибка при добавлении данных");
                return false;
            }

        } catch (NumberFormatException e) {
            resultTextView.setText("Введите корректные числовые значения");
            showToast("Введите корректные числовые значения");
            return false;
        }
    }
}
