package com.cdac.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void dial(){
        Uri number = Uri.parse("tel:9988776655");
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(number);
        startActivity(i);
    }

    private void openUrl(){
        Uri url = Uri.parse("http://www.google.com");
        Intent i = new Intent(Intent.ACTION_VIEW,url);
        startActivity(i);
    }

    private void shareText(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, "How are you?");
        startActivity(i);
    }

    private void init(){
        Button buttonDial = findViewById(R.id.buttonDial);
        buttonDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dial();
            }
        });

        Button buttonOpenUrl = findViewById(R.id.buttonOpenUrl);
        buttonOpenUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl();
            }
        });

        Button buttonShare = findViewById(R.id.buttonShare);
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareText();
            }
        });
    }
}
