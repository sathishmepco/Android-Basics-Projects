package com.cdac.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonPlay, buttonStop, buttonPause, buttonResume;
    static final int PLAY = 1;
    static final int STOP = 2;
    static final int PAUSE = 3;
    static final int RESUME = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        buttonPause = findViewById(R.id.buttonPause);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonStop = findViewById(R.id.buttonStop);
        buttonResume = findViewById(R.id.buttonResume);

        ClickListener listener = new ClickListener();
        buttonPlay.setOnClickListener(listener);
        buttonStop.setOnClickListener(listener);
        buttonPause.setOnClickListener(listener);
        buttonResume.setOnClickListener(listener);
    }

    class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.buttonPlay:
                    Intent intent = new Intent(MainActivity.this,MusicService.class);
                    intent.putExtra("code",PLAY);
                    startService(intent);
                    break;
                case R.id.buttonStop:
                    intent = new Intent(MainActivity.this,MusicService.class);
                    intent.putExtra("code",STOP);
//                    startService(intent);
                    stopService(intent);
                    break;
                case R.id.buttonPause:
                    intent = new Intent(MainActivity.this,MusicService.class);
                    intent.putExtra("code",PAUSE);
                    startService(intent);
                    break;
                case R.id.buttonResume:
                    intent = new Intent(MainActivity.this,MusicService.class);
                    intent.putExtra("code",RESUME);
                    startService(intent);
                    break;
            }
        }
    }
}