package com.cdac.menudemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContextMenuActivity extends AppCompatActivity {

    ListView listView;
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);
        init();
    }

    private void init(){
        listView = findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mobileArray);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contexxt_menu,menu);
        menu.setHeaderTitle("Options");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionEdit:
                Toast.makeText(ContextMenuActivity.this,"Edit",Toast.LENGTH_SHORT).show();
                break;
            case R.id.actionDelete:
                Toast.makeText(ContextMenuActivity.this,"Delete",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}