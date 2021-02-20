package com.example.weather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.weather.ManageCity.CityAddActivity;
import com.example.weather.ManageCity.CityManageActivity;
import com.example.weather.db.DataManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView addImage, setImage;
    LinearLayout pagerPoints;
    ViewPager viewPager;
    //pager的数据
    List<Fragment> fragmentList;
    List<Fragment> fakeFragmentList;
    //城市名称集合
    List<String> cityNameList;
    //viewPager页数指示器集合
    List<ImageView> indicateList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataManager.initDb(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化控件
        addImage = findViewById(R.id.image_add);
        setImage = findViewById(R.id.image_settings);
        pagerPoints = findViewById(R.id.pager_points);
        viewPager = findViewById(R.id.view_pager);
        //添加两个图片的点击事件
        addImage.setOnClickListener(this);
        setImage.setOnClickListener(this);

        fragmentList = new ArrayList<>();
        fakeFragmentList=new ArrayList<>();
        initFake();
        //获取数据库中城市ID列表
        cityNameList= DataManager.queryAllLocationName();
        //如果为空，添加北京
        if (cityNameList == null) {
            cityNameList.add("北京");
        }

        indicateList = new ArrayList<>();

        //搜索界面可能会进行跳转
        Intent intent=getIntent();
        String cityName=intent.getStringExtra("cityName");
        if(!cityNameList.contains(cityName)&&cityName!=null){
            cityNameList.add(cityName);
        }

        //初始化viewpager
        initPager();
        CityFragmentAdapter adapter = new CityFragmentAdapter(getSupportFragmentManager(), fakeFragmentList);
        viewPager.setAdapter(adapter);

        //初始化小点指示器
        initPoint();
        viewPager.setCurrentItem(0);

        //给viewpager设置监听事件,和指示器联动
        setVpListener();

    }

    private void initFake() {
        //虚构数据
        List<ItemData> itemDataList = new ArrayList<>();
        ItemData data1=new ItemData("6-10℃","2020-2-19","东风6级","潮湿");
        ItemData data2=new ItemData("6-10℃","2020-2-20","东风6级","潮湿");
        ItemData data3=new ItemData("6-10℃","2020-2-21","东风6级","潮湿");
        ItemData data4=new ItemData("6-10℃","2020-2-22","东风6级","潮湿");
        ItemData data5=new ItemData("6-10℃","2020-2-23","东风6级","潮湿");
        itemDataList.add(data1);
        itemDataList.add(data2);
        itemDataList.add(data3);
        itemDataList.add(data4);
        itemDataList.add(data5);

        WeatherFragmentFake fragment1=new WeatherFragmentFake("2℃","上海",
                "晴",itemDataList);
        WeatherFragmentFake fragment2=new WeatherFragmentFake("10℃","河北",
                "小雨",itemDataList);
        WeatherFragmentFake fragment3=new WeatherFragmentFake("6℃","北京",
                "阴",itemDataList);
        WeatherFragmentFake fragment4=new WeatherFragmentFake("5℃","重庆",
                "晴",itemDataList);
        WeatherFragmentFake fragment5=new WeatherFragmentFake("12℃","云南",
                "晴",itemDataList);
        fakeFragmentList.add(fragment1);
        fakeFragmentList.add(fragment2);
        fakeFragmentList.add(fragment3);
        fakeFragmentList.add(fragment4);
        fakeFragmentList.add(fragment5);

    }


    private void setVpListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //给所有小点设置为默认颜色
                for (int i = 0; i < indicateList.size(); i++) {
                    indicateList.get(i).setImageResource(R.drawable.point);
                }
                //给选中的小点设置为特殊颜色
                indicateList.get(position).setImageResource(R.drawable.point_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initPoint() {
        if (fakeFragmentList != null && fakeFragmentList.size() > 0) {
            for (int i = 0; i < fakeFragmentList.size(); i++) {
                ImageView pointIv = new ImageView(this);
                pointIv.setImageResource(R.drawable.point);
                pointIv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) pointIv.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, 0);
                indicateList.add(pointIv);
                pagerPoints.addView(pointIv);
            }
            indicateList.get(0).setImageResource(R.drawable.point_selected);
        }
    }
    private void initPager() {
        //给fragmentList添加fragment
        if (cityNameList != null && cityNameList.size() > 0) {
            for (int i = 0; i < cityNameList.size(); i++) {
                WeatherConditionFragment fragment = new WeatherConditionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", cityNameList.get(i));
                fragment.setArguments(bundle);
                fragmentList.add(fragment);
            }
        }
    }

    Intent intent=new Intent();
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.image_add:
                intent.setClass(this,CityManageActivity.class);
                startActivity(intent);
                break;
            case R.id.image_settings:
                Toast.makeText(this,"还未添加设置界面QAQ",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}