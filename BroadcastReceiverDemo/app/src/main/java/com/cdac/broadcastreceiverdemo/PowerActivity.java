package com.cdac.broadcastreceiverdemo;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PowerActivity extends AppCompatActivity {

    TextView textViewAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);
        init();
    }

    private void init(){
        String action = getIntent().getStringExtra("action");
        textViewAction= findViewById(R.id.textViewAction);
        textViewAction.setText(action.substring(action.lastIndexOf(".")+1));
    }
}