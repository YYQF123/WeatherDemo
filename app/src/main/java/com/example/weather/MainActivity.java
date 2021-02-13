package com.example.weather;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //处理指数图片大小
    
    Drawable cloth= getApplicationContext().getDrawable(R.drawable.index_clothes);
    @SuppressLint("UseCompatLoadingForDrawables")
    Drawable car= getApplicationContext().getDrawable(R.drawable.index_car);
    @SuppressLint("UseCompatLoadingForDrawables")
    Drawable comfort= getApplicationContext().getDrawable(R.drawable.index_comfort);
    @SuppressLint("UseCompatLoadingForDrawables")
    Drawable travel= getApplicationContext().getDrawable(R.drawable.index_travel);
    @SuppressLint("UseCompatLoadingForDrawables")
    Drawable cold= getApplicationContext().getDrawable(R.drawable.index_cold);
    @SuppressLint("UseCompatLoadingForDrawables")
    Drawable zwx= getApplicationContext().getDrawable(R.drawable.index_zwx);


    ImageView addImage, setImage;
    LinearLayout pagerPoints;
    ViewPager viewPager;
    //pager的数据
    List<Fragment> fragmentList;
    //城市locationID集合
    List<Integer> cityLocationList;
    //viewPager页数指示器集合
    List<ImageView> indicateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        cityLocationList = new ArrayList<>();
        indicateList = new ArrayList<>();
        //如果为空，添加北京

        if (cityLocationList == null) {

            cityLocationList.add(101010100);

        }

        //初始化  viewPager
        initPager();
        CityFragmentAdapter adapter = new CityFragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);

        //初始化  小点指示器
        initPoint();
        viewPager.setCurrentItem(0);

        //给viewpager设置监听事件,和指示器联动
        setVpListener();

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
        for (int i = 0; i < fragmentList.size(); i++) {
            ImageView pointIv = new ImageView(this);
            pointIv.setImageResource(R.drawable.point);
            pointIv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) pointIv.getLayoutParams();
            layoutParams.setMargins(0, 0, 25, 0);
            indicateList.add(pointIv);
            pagerPoints.addView(pointIv);
        }
        indicateList.get(0).setImageResource(R.drawable.point_selected);
    }

    private void initPager() {
        //给fragmentList添加fragment
        for (int i = 0; i < cityLocationList.size(); i++) {
            WeatherConditionFragment fragment = new WeatherConditionFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("location", cityLocationList.get(i));
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_add:

                break;
            case R.id.image_settings:

                break;
        }
    }
}