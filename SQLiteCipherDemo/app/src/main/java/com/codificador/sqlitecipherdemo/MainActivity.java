package com.codificador.sqlitecipherdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DBHelper dbHelper;
    EditText editTextFName, editTextLName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
        editTextFName = findViewById(R.id.editTextName);
        editTextLName = findViewById(R.id.editTextNumber);
        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonGetAll).setOnClickListener(this);
    }

    private void initPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    123);
            return;
        }
        dbHelper = new DBHelper(getApplicationContext());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            dbHelper = new DBHelper(getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonAdd:
                addRecord();
                break;
            case R.id.buttonGetAll:
                getAllRecords();
                break;
        }
    }

    private void addRecord(){
        String fName = editTextFName.getText().toString();
        String lName = editTextLName.getText().toString();
        dbHelper.insertRecord(fName,lName);
    }

    private void getAllRecords(){
        String allRecords = dbHelper.getAllRecords();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("All Records");
        builder.setMessage(allRecords);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}