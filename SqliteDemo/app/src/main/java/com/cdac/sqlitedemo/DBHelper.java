package com.cdac.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID ="_id";
    public static final String CONTACT_COLUMN_NAME = "name";
    public static final String CONTACT_COLUMN_CONTACT_NUMBER = "number";
    public static final int DB_VERSION = 1;

    public static final String CREATE_QUERY =   "create table "+TABLE_NAME+
            "("+CONTACTS_COLUMN_ID+" integer primary key, "+CONTACT_COLUMN_NAME +" text,"+CONTACT_COLUMN_CONTACT_NUMBER+" text)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }

    public void addContact(ContactInfo contact){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_COLUMN_NAME,contact.getName());
        contentValues.put(CONTACT_COLUMN_CONTACT_NUMBER,contact.getNumber());
        database.insert(TABLE_NAME,null,contentValues);
    }

    public ArrayList<ContactInfo> getAllContacts(){
        ArrayList<ContactInfo> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ContactInfo contact = new ContactInfo();
            contact.setName(cursor.getString(cursor.getColumnIndex(CONTACT_COLUMN_NAME)));
            contact.setNumber(cursor.getString(cursor.getColumnIndex(CONTACT_COLUMN_CONTACT_NUMBER)));
            contact.setId(cursor.getLong(cursor.getColumnIndex(CONTACTS_COLUMN_ID)));
            list.add(contact);
            System.out.println(contact.getId()+" "+contact.getName()+" "+contact.getNumber());
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public int delete(long id){
        try{
            SQLiteDatabase database = this.getWritableDatabase();
            int rows = database.delete(TABLE_NAME,CONTACTS_COLUMN_ID+"=?",new String[]{id+""});
            return rows;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public int deleteAll(){
        try{
            SQLiteDatabase database = this.getWritableDatabase();
            int rows = database.delete(TABLE_NAME,"1 != 2",null);
            return rows;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}