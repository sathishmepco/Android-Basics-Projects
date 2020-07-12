package com.codificador.checkboxrecyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textViewOutput);
        findViewById(R.id.buttonGetSelectedItems).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ItemActivity.class);
                startActivityForResult(intent,123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == RESULT_OK){
            ArrayList<Item> items = (ArrayList<Item>) data.getSerializableExtra("items");
            StringBuilder sb = new StringBuilder();
            for(Item item : items){
                sb.append(item.getName()).append("      ").append(item.getQty());
                sb.append("\n\n");
            }
            textView.setText(sb.toString());
        }
    }
}