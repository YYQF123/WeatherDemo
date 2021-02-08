package com.example.weather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.net.URL;

import Tool.MyHttpUrlConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private final Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if(msg.what==10){
                String[] response=(String[]) msg.obj;
                //UI操作


            }
            return false;
        }
    });

    public void initData(){
        new Thread(new Runnable() {

            private URL url;

            @Override
            public void run() {
                try {
                    url = new URL("https://geoapi.qweather.com/v2/city/lookup?{}");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                MyHttpUrlConnection.getDataFromServer(url ,handler);
            }
        });
    }

}