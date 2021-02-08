package com.example.weather;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class WeatherConditionFragment extends LoadDataFragment implements View.OnClickListener{


    TextView weatherTempature,weatherCity,weatherCondition,weatherData,weatherWind,weatherTRange,indexDress,indexCold,indexCar,indexSports,indexZwx;
    ImageView conditionImage;
    LinearLayout forecastLayout;

    String urlFirst;
    String urlSecond;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_weather_condition, container, false);
        //实例化控件
        weatherTempature=view.findViewById(R.id.weather_tempature);
        weatherCity=view.findViewById(R.id.weather_city);
        weatherCondition=view.findViewById(R.id.weather_condition);
        weatherData=view.findViewById(R.id.weather_date);
        weatherWind=view.findViewById(R.id.weather_wind);
        weatherTRange=view.findViewById(R.id.weather_tempurature_range);
        indexDress=view.findViewById(R.id.index_dress);
        indexCold=view.findViewById(R.id.index_cold);
        indexCar=view.findViewById(R.id.index_car);
        indexSports=view.findViewById(R.id.index_sport);
        indexZwx=view.findViewById(R.id.index_zwx);
        conditionImage=view.findViewById(R.id.weather_iv_condition);
        forecastLayout=view.findViewById(R.id.weather_forecast);

        //设置每日指数信息的监听
        indexZwx.setOnClickListener(this);
        indexSports.setOnClickListener(this);
        indexCar.setOnClickListener(this);
        indexCold.setOnClickListener(this);
        indexDress.setOnClickListener(this);

        //通过Acitivity传入当前fragment的地区名,拼接网址url
        Bundle bundle=getArguments();
        String city = bundle.getString("city");
        String urlFinal=urlFirst+city+urlSecond;

        loadHttpData(urlFinal);

        return view;
    }

    @Override
    public void onSuccess(String response) {
        super.onSuccess(response);
        //解析数据，展示到布局中
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.index_car:
                break;
            case R.id.index_cold:
                break;
            case R.id.index_sport:
                break;
            case R.id.index_zwx:
                break;
            case R.id.index_dress:
                break;
        }

    }
}