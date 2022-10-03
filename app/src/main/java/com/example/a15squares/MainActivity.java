package com.example.a15squares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameView gameView = findViewById(R.id.gameView);

        gameView.setOnTouchListener(gameView);

        Button resetButton = findViewById(R.id.resetButton);

        resetButton.setOnClickListener(gameView);





    }
}