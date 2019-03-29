package com.cdac.applaunchcount;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int count = sharedPreferences.getInt("count",0);
        count++;

        editor.putInt("count",count);
        editor.commit();

        TextView textViewCount = findViewById(R.id.textViewLaunchCount);
        textViewCount.setText("Launch Count is "+count);

    }
}