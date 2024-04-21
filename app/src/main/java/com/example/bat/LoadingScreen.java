package com.example.bat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
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

        //check if app has perms for notifs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(LoadingScreen.this,
                    android.Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(LoadingScreen.this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

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
                makeNotification();
            }
        }, loadingTimeInMS);


    }

    public void makeNotification(){
        String chanelID = "CHANNEL_ID_NOTIFICATION";
        String info = "Hang tight! The mechanic will be with you shortly.";

        //create notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), chanelID);

        builder.setSmallIcon(R.drawable.baseline_notifications_active_24)
                .setContentTitle("Mechanic Found")
                .setContentText(info)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //launch mechanic page when notification is clicked
        Intent intent = new Intent(getApplicationContext(), MechanicPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0, intent, PendingIntent.FLAG_MUTABLE);

        //display notification
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }
}