package com.cdac.alarmdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents(){
        Button buttonSetAlarm, buttonCancelAlarm;

        buttonSetAlarm = findViewById(R.id.buttonSetAlarm);
        buttonCancelAlarm = findViewById(R.id.buttonCancelAlarm);

        buttonSetAlarm.setOnClickListener(this);
        buttonCancelAlarm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSetAlarm:
                setAlarm();
                break;
            case R.id.buttonCancelAlarm:
                cancelAlarm();
                break;
        }
    }

    public void setAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(getApplicationContext(),StopAlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+1*1000,pendingIntent);
    }

    private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(getApplicationContext(),StopAlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,alarmIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

    public void startAlaramRTCRepeat(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,new Intent(getApplicationContext(),StopAlarmActivity.class),PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, 5000, pendingIntent);
    }

    public void startAlaramElapsedRepeat(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,new Intent(getApplicationContext(),StopAlarmActivity.class),PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,System.currentTimeMillis()+5000,5000,pendingIntent);
    }
}
