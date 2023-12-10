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

public class RoofCalculator4 extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText lengthEditText4;
    private EditText widthEditText4;
    private EditText nameEditText4;
    private EditText heightEditText4;
    private EditText DEditText;
    private EditText CEditText2;
    private EditText sves4;
    private Button calculateButton4;
    private TextView resultTextView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roof_calculator4);
        databaseHelper = new DatabaseHelper(this);
        lengthEditText4 = findViewById(R.id.editTextLength4);
        widthEditText4 = findViewById(R.id.editTextWidth4);
        nameEditText4 = findViewById(R.id.editTextName4);
        heightEditText4 = findViewById(R.id.editTextHeight4);
        sves4 = findViewById(R.id.sves4);
        DEditText = findViewById(R.id.editTextD);
        CEditText2 = findViewById(R.id.editTextC2);
        calculateButton4 = findViewById(R.id.buttonCalculate4);
        resultTextView4 = findViewById(R.id.textViewResult4);

        calculateButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculateRoofArea()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(RoofCalculator4.this, MyProjects.class);
                            startActivity(intent);
                        }
                    }, 3000);
                }
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, "Проект сохранен", Toast.LENGTH_SHORT).show();
    }

    private boolean calculateRoofArea() {
        String lengthStr = lengthEditText4.getText().toString();
        String widthStr = widthEditText4.getText().toString();
        String sves4Str = sves4.getText().toString();
        String DEditTextStr = DEditText.getText().toString();
        String CEditText2Str = CEditText2.getText().toString();
        String heightStr = heightEditText4.getText().toString();
        String nameStr = nameEditText4.getText().toString();

        if (lengthStr.isEmpty() || widthStr.isEmpty() || nameStr.isEmpty() || sves4Str.isEmpty() || DEditTextStr.isEmpty() || CEditText2Str.isEmpty() || heightStr.isEmpty()) {
            resultTextView4.setText("Введите все значения");
            showToast("Введите все значения");
            return false;
        }

        try {
            double a = Double.parseDouble(lengthStr);
            double b = Double.parseDouble(widthStr);
            double h = Double.parseDouble(heightStr);
            double s = Double.parseDouble(sves4Str);
            double c = Double.parseDouble(CEditText2Str);
            double d = Double.parseDouble(DEditTextStr);

            if (a <= 0 || b <= 0 || h <= 0 || s < 0 || c <= 0 || d <= 0) {
                resultTextView4.setText("Введите корректные значения");
                showToast("Введите корректные значения");
                return false;
            }

            double area = 2 * ((a + 2 * s) * (Math.sqrt(Math.pow(h, 2) + Math.pow(c, 2)) + (a + 2 * s) * Math.sqrt(Math.pow((b + 2 * s) / 2 - c, 2) + Math.pow(d, 2))));

            long newRowId = databaseHelper.insertData(nameStr, "Мансардная", a, b, h, area, s, c, d);
            if (newRowId != -1) {
                showToast("Данные добавлены, Row ID: " + newRowId);
                return true;
            } else {
                showToast("Ошибка при добавлении данных");
                return false;
            }

        } catch (NumberFormatException e) {
            resultTextView4.setText("Введите корректные числовые значения");
            showToast("Введите корректные числовые значения");
            return false;
        }
    }
}
