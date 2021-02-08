package com.example.weather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.net.MalformedURLException;
import java.net.URL;

import Tool.MyHttpUrlConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView addImage,setImage;
    LinearLayout pagerPoints;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化控件
        addImage=findViewById(R.id.image_add);
        setImage=findViewById(R.id.image_settings);
        pagerPoints=findViewById(R.id.pager_points);
        viewPager=findViewById(R.id.view_pager);
        //添加两个图片的点击事件
        addImage.setOnClickListener(this);
        setImage.setOnClickListener(this);


    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.image_add:

                break;
            case R.id.image_settings:

                break;
        }
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