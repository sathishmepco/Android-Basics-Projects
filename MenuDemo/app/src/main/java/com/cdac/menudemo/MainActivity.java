package com.cdac.menudemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonOptionMenu, buttonContextMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        buttonOptionMenu = findViewById(R.id.buttonOptionMenu);
        buttonContextMenu = findViewById(R.id.buttonContextMenu);
        ClickListener listener = new ClickListener();
        buttonContextMenu.setOnClickListener(listener);
        buttonOptionMenu.setOnClickListener(listener);
    }

    class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.buttonOptionMenu:
                    launchOptionMenuActivity();
                    break;
                case R.id.buttonContextMenu:
                    launchContextMenuActivity();
                    break;
            }
        }
    }

    private void launchOptionMenuActivity(){
        Intent intent = new Intent(MainActivity.this,OptionMenuActivity.class);
        startActivity(intent);
    }

    private void launchContextMenuActivity(){
        Intent intent = new Intent(MainActivity.this,ContextMenuActivity.class);
        startActivity(intent);
    }
}