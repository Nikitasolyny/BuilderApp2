package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.lang.Math;

public class RoofCalculator2 extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText lengthEditText2;
    private EditText widthEditText2;
    private EditText nameEditText2;
    private EditText CEditText;
    private EditText heightEditText2;
    private EditText sves2;
    private Button calculateButton2;
    private TextView resultTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roof_calculator2);
        databaseHelper = new DatabaseHelper(this);
        lengthEditText2 = findViewById(R.id.editTextLength2);
        widthEditText2 = findViewById(R.id.editTextWidth2);
        nameEditText2 = findViewById(R.id.editTextName2);
        heightEditText2 = findViewById(R.id.editTextHeight2);
        sves2 = findViewById(R.id.sves2);
        CEditText = findViewById(R.id.editTextC);
        calculateButton2 = findViewById(R.id.buttonCalculate2);
        resultTextView2 = findViewById(R.id.textViewResult2);

        calculateButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculateRoofArea()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(RoofCalculator2.this, MyProjects.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean calculateRoofArea() {
        String lengthStr = lengthEditText2.getText().toString();
        String widthStr = widthEditText2.getText().toString();
        String sves2Str = sves2.getText().toString();
        String CEditTextStr = CEditText.getText().toString();
        String heightStr = heightEditText2.getText().toString();
        String nameStr = nameEditText2.getText().toString();

        if (lengthStr.isEmpty() || widthStr.isEmpty() || nameStr.isEmpty() || sves2Str.isEmpty() || CEditTextStr.isEmpty() || heightStr.isEmpty()) {
            resultTextView2.setText("Введите все значения");
            showToast("Введите все значения");
            return false;
        }

        try {
            double a = Double.parseDouble(lengthStr);
            double b = Double.parseDouble(widthStr);
            double h = Double.parseDouble(heightStr);
            double d = Double.parseDouble(sves2Str);
            double c = Double.parseDouble(CEditTextStr);

            if (a <= 0 || b <= 0 || h <= 0 || d < 0 || c <= 0) {
                resultTextView2.setText("Введите корректные значения");
                showToast("Введите корректные значения");
                return false;
            }

            double area = Math.ceil((2 * (((b + 2 * d) * Math.sqrt(Math.pow(c, 2) + Math.pow(h, 2))) / 2 + (2 * (a + d - c)) / 2 * Math.sqrt(Math.pow(h, 2) + Math.pow(((b + 2 * d) / 2), 2)))) * 100) / 100;

            long newRowId = databaseHelper.insertData(nameStr, "Вальма", a, b, h, area, d, c, 0);
            if (newRowId != -1) {
                showToast("Проект сохранен, площадь составила: " + area + "метров квадратных");
                return true;
            } else {
                showToast("Ошибка при добавлении данных");
                return false;
            }

        } catch (NumberFormatException e) {
            resultTextView2.setText("Введите корректные числовые значения");
            showToast("Введите корректные числовые значения");
            return false;
        }
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
