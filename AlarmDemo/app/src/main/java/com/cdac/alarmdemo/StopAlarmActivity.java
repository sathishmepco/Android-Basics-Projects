package com.cdac.alarmdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopAlarmActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button  buttonStopAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_alarm);
        initComponents();
        playAlarmRingtone();
    }

    private void initComponents(){
        textView = findViewById(R.id.textView);
        buttonStopAlarm = findViewById(R.id.buttonStopAlarm);
        buttonStopAlarm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if( view.getId() == R.id.buttonStopAlarm){
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,new Intent(getApplicationContext(),MainActivity.class),PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.cancel(pendingIntent);

            buttonStopAlarm.setVisibility(View.GONE);
            textView.setText("Alarm is Stopped !");

            if(ringtoneAlarm != null)
                ringtoneAlarm.stop();
        }
    }
    Ringtone ringtoneAlarm = null;
    private void playAlarmRingtone(){
        Uri alarmTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        ringtoneAlarm = RingtoneManager.getRingtone(getApplicationContext(), alarmTone);
        ringtoneAlarm.play();

        //this also works
        /*MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),alarmTone);
        mediaPlayer.start();*/
    }
}