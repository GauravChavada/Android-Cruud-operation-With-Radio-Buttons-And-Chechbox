package com.au.registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    public database(@Nullable Context context) {
        super(context, "info", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, gender TEXT,hobi TEXT, skill TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert(String name,String gender,String hobi,String skill){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("gender",gender);
        cv.put("hobi",hobi);
        cv.put("skill",skill);
        long result = db.insert("user",null,cv);
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }
    public Cursor select()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM user",null);
        return c;
    }
}
