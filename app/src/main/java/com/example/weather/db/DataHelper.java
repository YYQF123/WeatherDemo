package com.example.weather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    public DataHelper(Context context){
        super(context,"weather.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        String s="create table chart(_id integer primary key autoincrement,cityName varchar(30) unique not null,JSONContent text not null)";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
