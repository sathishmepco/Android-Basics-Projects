package com.cdac.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextNumber;
    Button buttonAdd, buttonGetAll, buttonDeleteAll;
    TextView textViewResult;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        editTextName = findViewById(R.id.editTextName);
        editTextNumber = findViewById(R.id.editTextNumber);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonGetAll = findViewById(R.id.buttonGetAll);
        buttonDeleteAll = findViewById(R.id.buttonDeleteAll);
        textViewResult = findViewById(R.id.textViewResult);
        dbHelper = new DBHelper(MainActivity.this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String number = editTextNumber.getText().toString();
                ContactInfo contactInfo = new ContactInfo();
                contactInfo.setName(name);
                contactInfo.setNumber(number);
                dbHelper.addContact(contactInfo);
            }
        });

        buttonGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<ContactInfo> list = dbHelper.getAllContacts();
                if(list.size() == 0){
                    textViewResult.setText("There is no record available");
                    return;
                }

                StringBuilder stringBuilder = new StringBuilder();
                for(ContactInfo contactInfo : list){
                    stringBuilder.append(contactInfo.getId()+"\t "+contactInfo.getName()+"\t "+contactInfo.getNumber()).append("\n");
                }
                textViewResult.setText(stringBuilder.toString());
            }
        });

        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rows = dbHelper.deleteAll();
                textViewResult.setText(rows+" records deleted");
            }
        });
    }
}

