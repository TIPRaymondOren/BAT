package com.example.bat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoadingScreen extends AppCompatActivity {

    TextView backEndText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loading_screen);
        final Handler nextScreenHandler = new Handler();
        final Handler loadProcessHandler1 = new Handler();
        final Handler loadProcessHandler2 = new Handler();
        final Handler loadProcessHandler3 = new Handler();

        int loadingTimeInMS = 10000;
        int loadProcess1Delay = 2000;
        int loadProcess2Delay = 4000;
        int loadProcess3Delay = 8000;

        backEndText = findViewById(R.id.backEndProcess);

        // --------------------- Loading Process Description #2 ---------------------
        loadProcessHandler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                backEndText.setText("Processing your Report...");
            }
        }, loadProcess1Delay);

        // --------------------- Loading Process Description #3 ---------------------
        loadProcessHandler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                backEndText.setText("Looking for a mechanic...");
            }
        }, loadProcess2Delay);

        // --------------------- Loading Process Description #3 ---------------------
        loadProcessHandler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                backEndText.setText("Mechanic Found!");
            }
        }, loadProcess3Delay);

        nextScreenHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent openScreen = new Intent(LoadingScreen.this, MechanicPage.class);
                LoadingScreen.this.startActivity(openScreen);
            }
        }, loadingTimeInMS);


    }
}