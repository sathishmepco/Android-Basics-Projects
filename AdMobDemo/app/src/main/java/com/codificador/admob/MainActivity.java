package com.codificador.admob;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.buttonBanner).setOnClickListener(this);
        findViewById(R.id.buttonInterstitial).setOnClickListener(this);
        findViewById(R.id.buttonNative).setOnClickListener(this);
        findViewById(R.id.buttonRewarded).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBanner:
                showBanner();
                break;
            case R.id.buttonInterstitial:
                showInterstitial();
                break;
            case R.id.buttonNative:
                showNative();
                break;
            case R.id.buttonRewarded:
                showRewarded();
                break;

        }
    }

    private void showBanner(){
        startActivity(new Intent(getApplicationContext(),BannerActivity.class));
    }

    private void showInterstitial(){
        startActivity(new Intent(getApplicationContext(),InterstitialActivity.class));
    }

    private void showNative(){
        startActivity(new Intent(getApplicationContext(),NativeActivity.class));
    }

    private void showRewarded(){
        startActivity(new Intent(getApplicationContext(),RewardedActivity.class));
    }

}