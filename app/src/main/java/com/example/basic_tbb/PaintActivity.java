package com.example.basic_tbb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

public class PaintActivity extends AppCompatActivity {
    private Button returnButton;
    private Spinner colorSpinner; //placeholder for color wheel to be implemented

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        returnButton = findViewById(R.id.return_button);
        colorSpinner = findViewById(R.id.color_spinner);

        PaintView paintView = new PaintView(this);
        setContentView(paintView);


    }
}