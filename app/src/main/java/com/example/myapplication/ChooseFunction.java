package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseFunction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_function);

        ImageButton scat1 = findViewById(R.id.scat1);
        ImageButton valma = findViewById(R.id.valma);
        ImageButton scat2 = findViewById(R.id.scat2);
        ImageButton mansard = findViewById(R.id.mansard);

        scat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseFunction.this, RoofCalculator.class);
                startActivity(intent);
            }
        });

        valma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseFunction.this, RoofCalculator2.class);
                startActivity(intent);
            }
        });
        scat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseFunction.this, RoofCalculator3.class);
                startActivity(intent);
            }
        });
        mansard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseFunction.this, RoofCalculator4.class);
                startActivity(intent);
            }
        });
    }
}
