package com.codificador.checkboxrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ArrayList<Item> list = new ArrayList<>();
        Item item = new Item();
        item.setId(1);
        item.setName("Item 1");
        list.add(item);

        item = new Item();
        item.setId(2);
        item.setName("Item 2");
        list.add(item);

        item = new Item();
        item.setId(3);
        item.setName("Item 3");
        list.add(item);

        item = new Item();
        item.setId(4);
        item.setName("Item 4");
        list.add(item);

        item = new Item();
        item.setId(5);
        item.setName("Item 5");
        list.add(item);

        item = new Item();
        item.setId(6);
        item.setName("Item 6");
        list.add(item);

        item = new Item();
        item.setId(7);
        item.setName("Item 7");
        list.add(item);

        item = new Item();
        item.setId(8);
        item.setName("Item 8");
        list.add(item);

        item = new Item();
        item.setId(9);
        item.setName("Item 9");
        list.add(item);

        item = new Item();
        item.setId(10);
        item.setName("Item 10");
        list.add(item);

        item = new Item();
        item.setId(11);
        item.setName("Item 11");
        list.add(item);

        item = new Item();
        item.setId(12);
        item.setName("Item 12");
        list.add(item);

        item = new Item();
        item.setId(13);
        item.setName("Item 13");
        list.add(item);

        item = new Item();
        item.setId(14);
        item.setName("Item 14");
        list.add(item);

        item = new Item();
        item.setId(15);
        item.setName("Item 15");
        list.add(item);

        item = new Item();
        item.setId(16);
        item.setName("Item 16");
        list.add(item);

        item = new Item();
        item.setId(17);
        item.setName("Item 17");
        list.add(item);

        item = new Item();
        item.setId(18);
        item.setName("Item 18");
        list.add(item);

        item = new Item();
        item.setId(19);
        item.setName("Item 19");
        list.add(item);

        item = new Item();
        item.setId(20);
        item.setName("Item 20");
        list.add(item);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        final Adapter adapter = new Adapter(list,ItemActivity.this);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.buttonSendResult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Item> selectedItems = adapter.getSelectedItems();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("items",selectedItems);
                setResult(RESULT_OK,resultIntent);
                finish();
            }
        });
    }
}
