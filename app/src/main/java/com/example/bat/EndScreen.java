package com.example.bat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EndScreen extends AppCompatActivity {

    ImageView star1img, star2img, star3img, star4img, star5img;
    TextView ratingText;
    Button doneBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_end_screen);
        star1img = findViewById(R.id.star1);
        star2img = findViewById(R.id.star2);
        star3img = findViewById(R.id.star3);
        star4img = findViewById(R.id.star4);
        star5img = findViewById(R.id.star5);
        ratingText = findViewById(R.id.ratingText);
        doneBtn = findViewById(R.id.endButton);

        // 1 Star Review Code
        star1img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star1img.setImageResource(R.drawable.yellow_star);
                star2img.setImageResource(R.drawable.blue_star);
                star3img.setImageResource(R.drawable.blue_star);
                star4img.setImageResource(R.drawable.blue_star);
                star5img.setImageResource(R.drawable.blue_star);
                ratingText.setText(R.string.rating_1);
            }
        });

        // 2 Star Review Code

        star2img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star1img.setImageResource(R.drawable.yellow_star);
                star2img.setImageResource(R.drawable.yellow_star);
                star3img.setImageResource(R.drawable.blue_star);
                star4img.setImageResource(R.drawable.blue_star);
                star5img.setImageResource(R.drawable.blue_star);
                ratingText.setText(R.string.rating_2);
            }
        });

        // 3 Star Review Code

        star3img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star1img.setImageResource(R.drawable.yellow_star);
                star2img.setImageResource(R.drawable.yellow_star);
                star3img.setImageResource(R.drawable.yellow_star);
                star4img.setImageResource(R.drawable.blue_star);
                star5img.setImageResource(R.drawable.blue_star);
                ratingText.setText(R.string.rating_3);
            }
        });
        // 4 Star Review Code

        star4img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star1img.setImageResource(R.drawable.yellow_star);
                star2img.setImageResource(R.drawable.yellow_star);
                star3img.setImageResource(R.drawable.yellow_star);
                star4img.setImageResource(R.drawable.yellow_star);
                star5img.setImageResource(R.drawable.blue_star);
                ratingText.setText(R.string.rating_4);
            }
        });
        // 5 Star Review Code

        star5img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star1img.setImageResource(R.drawable.yellow_star);
                star2img.setImageResource(R.drawable.yellow_star);
                star3img.setImageResource(R.drawable.yellow_star);
                star4img.setImageResource(R.drawable.yellow_star);
                star5img.setImageResource(R.drawable.yellow_star);
                ratingText.setText(R.string.rating_5);
            }
        });

        // Done Button
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

    }
}