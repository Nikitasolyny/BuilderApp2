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
                calculateRoofArea();
                Intent intent = new Intent(RoofCalculator2.this, MyProjects.class);
                startActivity(intent);
            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(this, "Проект сохранен" , Toast.LENGTH_SHORT).show();
    }
    private void calculateRoofArea() {
        String lengthStr = lengthEditText2.getText().toString();
        String widthStr = widthEditText2.getText().toString();
        String sves2Str = sves2.getText().toString();
        String CEditTextStr = CEditText.getText().toString();
        String heightStr = heightEditText2.getText().toString();
        String nameStr = nameEditText2.getText().toString();

        if (lengthStr.isEmpty() || widthStr.isEmpty() || nameStr.isEmpty() || sves2Str.isEmpty()  || CEditTextStr.isEmpty() || heightStr.isEmpty()) {
            resultTextView2.setText("Введите все значения");
            return;
        }

        double a = Double.parseDouble(lengthStr);
        double b = Double.parseDouble(widthStr);
        double h = Double.parseDouble(heightStr);
        double d = Double.parseDouble(sves2Str);
        double c = Double.parseDouble(CEditTextStr);
        double area = 2*(((b+2*d)*Math.sqrt(Math.pow(c, 2)+Math.pow(h, 2)))/2 + (2*(a+d-c))/2*Math.sqrt(Math.pow(h, 2)+Math.pow(((b+2*d)/2), 2)));

        long newRowId = databaseHelper.insertData(nameStr, "Вальма", a,b,h,area,d,c,0 );
        if (newRowId != -1) {
            showToast("Data added, Row ID: " + newRowId);
        } else {
            showToast("Error adding data");
        }

        resultTextView2.setText("Площадь крыши: " + area + " кв. м");
    }
}
