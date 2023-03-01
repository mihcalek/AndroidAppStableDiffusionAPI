package com.example.asynctaskai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView promptInput;
    ImageView resultImage;
    Button buttonGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        promptInput = findViewById(R.id.editTextPrompt);
        resultImage = findViewById(R.id.imageView);
        buttonGenerate = findViewById(R.id.buttonGenerate);

    }

}