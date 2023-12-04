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



public class RoofCalculator3 extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText lengthEditText3;
    private EditText widthEditText3;
    private EditText nameEditText3;
    private EditText heightEditText3;
    private EditText sves3;

    private Button calculateButton3;
    private TextView resultTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roof_calculator3);
        databaseHelper = new DatabaseHelper(this);
        lengthEditText3 = findViewById(R.id.editTextLength3);
        widthEditText3 = findViewById(R.id.editTextWidth3);
        nameEditText3 = findViewById(R.id.editTextName3);
        heightEditText3 = findViewById(R.id.editTextHeight3);
        sves3 = findViewById(R.id.sves3);
        calculateButton3 = findViewById(R.id.buttonCalculate3);
        resultTextView3 = findViewById(R.id.textViewResult3);

        calculateButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateRoofArea();
                Intent intent = new Intent(RoofCalculator3.this, MyProjects.class);
                startActivity(intent);
            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(this, "Проект сохранен" , Toast.LENGTH_SHORT).show();
    }
    private void calculateRoofArea() {
        String lengthStr = lengthEditText3.getText().toString();
        String widthStr = widthEditText3.getText().toString();
        String sves3Str = sves3.getText().toString();
        String heightStr = heightEditText3.getText().toString();
        String nameStr = nameEditText3.getText().toString();

        if (lengthStr.isEmpty() || widthStr.isEmpty() || nameStr.isEmpty() || sves3Str.isEmpty()  || heightStr.isEmpty()) {
            resultTextView3.setText("Введите все значения");
            return;
        }

        double a = Double.parseDouble(lengthStr);
        double b = Double.parseDouble(widthStr);
        double h = Double.parseDouble(heightStr);
        double d = Double.parseDouble(sves3Str);
        double area = 2*((a+2*d)*(Math.sqrt(Math.pow(h,2)+Math.pow((b+2*d)/2,2))));

        long newRowId = databaseHelper.insertData(nameStr,"Двухскатная",a,b,h,area,d,0,0);
        if (newRowId != -1) {
            showToast("Data added, Row ID: " + newRowId);
        } else {
            showToast("Error adding data");
        }

        resultTextView3.setText("Площадь крыши: " + area + " кв. м");
    }
}
