package com.cdac.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //through code register a broadcast receiver component
        /*BroadcastReceiver receiver = new PowerBroadcastReceiver();
        registerReceiver(receiver,new IntentFilter(Intent.ACTION_POWER_CONNECTED));
        registerReceiver(receiver, new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));*/
    }
}
