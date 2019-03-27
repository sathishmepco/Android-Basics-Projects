package com.cdac.explicitintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_data);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String username = bundle.getString("username",null);
            TextView textView = findViewById(R.id.textViewUser);
            textView.setText("Welcome, "+username);
        }
    }
}