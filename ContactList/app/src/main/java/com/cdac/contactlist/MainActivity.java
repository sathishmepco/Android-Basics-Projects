package com.cdac.contactlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ContactAdapter adapter;

    EditText editTextName, editTextNumber;
    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        listView = findViewById(R.id.listView);
        editTextName = findViewById(R.id.editTextName);
        editTextNumber = findViewById(R.id.editTextNumber);
        buttonAdd = findViewById(R.id.buttonAdd);

        adapter = new ContactAdapter(MainActivity.this,new ArrayList<Contact>());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Contact contact = (Contact) adapterView.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, contact.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                adapter.removeContact(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String number = editTextNumber.getText().toString();
                Contact contact = new Contact(name,number);
                adapter.addContact(contact);
                adapter.notifyDataSetChanged();
            }
        });

    }
}