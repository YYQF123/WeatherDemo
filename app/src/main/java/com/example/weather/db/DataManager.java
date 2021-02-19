package com.example.weather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static SQLiteDatabase database;
    public static void initDb(Context context){
        DataHelper dataHelper=new DataHelper(context);
        database=dataHelper.getWritableDatabase();
    }
    //查找城市ID列表
    public static List<String> queryAllLocationName(){
        Cursor cursor = database.query("chart", null, null, null, null, null, null);
        List<String> cityList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String cityLocation = cursor.getString(cursor.getColumnIndex("cityLocation"));
            cityList.add(cityLocation);
        }

        return cityList;
    }

    //根据城市ID替换JSONContent
    public static int updateContentByID(String cityLocation,String JSONContent){
        ContentValues values=new ContentValues();
        values.put("JSONContent",JSONContent);
        return database.update("chart",values,"cityLocation",new String[]{cityLocation});

    }

    //新增一条城市记录
    public static long addCityID(String cityLocation,String JSONContent){
        ContentValues values=new ContentValues();
        values.put("cityLocation",cityLocation);
        values.put("JSONContent",JSONContent);
        return database.insert("chart",null,values);
    }

    //根据ID查询数据库中的JSONContent
    public static String queryContentByID(String cityLocation){
        Cursor cursor = database.query("chart", null, "cityLocation=?", new String[]{cityLocation}, null, null, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            String content =cursor.getString(cursor.getColumnIndex("JSONContent"));
            return content;
        }return null;
    }

    //获取已存储的城市数量
    public  static int getCityIDCount(){
        Cursor cursor=database.query("chart",null,null,null,null,null,null);
        int count=cursor.getCount();
        return count;
    }

    //获取数据库中全部信息
    public static List<DataBean> queryAll(){
        Cursor cursor = database.query("chart", null, null, null, null, null, null);
        List<DataBean> beanList=new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String cityLocation=cursor.getString(cursor.getColumnIndex("cityLocation"));
            String JSONContent=cursor.getString(cursor.getColumnIndex("JSONContent"));
            DataBean dataBean = new DataBean(cityLocation, JSONContent, id);
            beanList.add(dataBean);
        }return beanList;
    }
}
