package com.cdac.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ACTIVITY_LIFECYCLE","onCreate Called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ACTIVITY_LIFECYCLE","onStart Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ACTIVITY_LIFECYCLE","onResume Called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ACTIVITY_LIFECYCLE","onPause Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ACTIVITY_LIFECYCLE","onStop Called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ACTIVITY_LIFECYCLE","onDestroy Called");
    }
}