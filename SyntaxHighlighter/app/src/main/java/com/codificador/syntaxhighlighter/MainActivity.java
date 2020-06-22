package com.codificador.syntaxhighlighter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        loadCode();
    }

    private void loadCode(){
        webView.getSettings().setJavaScriptEnabled(true);
        float density = getResources().getDisplayMetrics().density;
        int initialScaleLevel = 100;
        if(density == 3){
            initialScaleLevel = 190;
        }else if(density == 2){
            initialScaleLevel = 130;
        }else if(density == 4){
            initialScaleLevel = 240;
        }
        webView.setInitialScale(initialScaleLevel);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl("file:///android_asset/java_demo.html");
    }
}