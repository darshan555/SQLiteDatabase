package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Contact";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NUMBER = "phone_number";


    public MyDBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME+" TEXT,"+KEY_PHONE_NUMBER+
                " TEXT"+")");
        //Open query
        //SQLiteDatabase database = this.getReadableDatabase(); // if we don't have database name then we use to get

        //Close query
        //database.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public void addContact(String name,String number){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(KEY_NAME,name);
        value.put(KEY_PHONE_NUMBER,number);

        database.insert(TABLE_NAME,null,value);
    }
    public ArrayList<ContactModel> fetchData(){
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        ArrayList<ContactModel> allContact = new ArrayList<>();
        while (cursor.moveToNext()){
            ContactModel model = new ContactModel();
            model.id = cursor.getInt(0);
            model.name = cursor.getString(1);
            model.phoneNumber = cursor.getString(2);

            allContact.add(model);
        }
        return allContact;
    }
    public void update(ContactModel contactModel){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PHONE_NUMBER,contactModel.phoneNumber);

        database.update(TABLE_NAME,values,KEY_ID+"="+contactModel.id,null);

    }
    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(id)});

    }
}
