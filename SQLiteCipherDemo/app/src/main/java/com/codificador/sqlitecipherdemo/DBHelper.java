package com.codificador.sqlitecipherdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.widget.Toast;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;

public class DBHelper {

    final String TABLE_NAME = "contact";
    final String NAME = "name";
    final String NUMBER = "number";

    SQLiteDatabase database = null;
    Context context;

    public DBHelper(Context context){
        this.context = context;
        SQLiteDatabase.loadLibs(context);
        String filePath =  Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"mydb.db";
        File databaseFile = context.getDatabasePath(filePath);
        SharedPreferences sharedPreferences = context.getSharedPreferences("dbconfig",context.MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean("status",false);
        if(flag == false){
            databaseFile.mkdirs();
            databaseFile.delete();
        }
        database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "test123", null);
        if(flag == false){
            database.execSQL("create table "+TABLE_NAME+"("+NAME+" text, "+NUMBER+" text)");
            sharedPreferences.edit().putBoolean("status",true).commit();
        }
    }


    public void insertRecord(String name,String number){
        if(database == null)
            return;
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(NUMBER,number);
        long rowId = database.insert(TABLE_NAME,null,contentValues);
        if(rowId > 0)
            Toast.makeText(context,"Successfully inserted !! "+rowId,Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Unable to insert!!!", Toast.LENGTH_LONG).show();
    }

    public String getAllRecords(){
        if(database == null)
            return null;
        Cursor cursor = database.query(TABLE_NAME,null,null,null,null,null,null);
        StringBuilder sb = new StringBuilder();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String number = cursor.getString(cursor.getColumnIndex(NUMBER));
            sb.append(name+" "+number).append("\n");
            cursor.moveToNext();
        }
        cursor.close();
        return sb.toString();
    }
}