package com.cdac.menudemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class OptionMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionAdd:
                Toast.makeText(OptionMenuActivity.this,"Add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.actionSettings:
                Toast.makeText(OptionMenuActivity.this,"Settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.actionLogout:
                Toast.makeText(OptionMenuActivity.this,"Logout",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
