package com.cdac.notificationdemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button buttonShow, buttonCancel, buttonCancelAll;
    static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        buttonShow = findViewById(R.id.buttonShow);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancelAll = findViewById(R.id.buttonCancelAll);

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });
        buttonCancelAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAllNotifications();
            }
        });
    }

    private void showNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1234")
                .setSmallIcon(android.R.drawable.ic_menu_camera)
                .setContentTitle("Notification Title")
                .setContentText("notification content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(this, EventActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(counter, notification);
        counter++;
    }

    private void cancelNotification(){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.cancel(0);
    }

    private void cancelAllNotifications(){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.cancelAll();
    }
}