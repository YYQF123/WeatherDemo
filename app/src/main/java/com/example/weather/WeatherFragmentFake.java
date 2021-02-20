package com.example.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

public class WeatherFragmentFake extends Fragment implements View.OnClickListener{
    TextView weatherTempature, weatherCity, weatherCondition;

    String weatherTempatureData,weatherCityData,weatherConditionData;
    List<ItemData> itemDataList;
    TextView indexDress, indexCold, indexCar, indexSports, indexZwx, indexTravel;
    ImageView conditionImage;
    LinearLayout detailsLayout;

    public WeatherFragmentFake(String weatherTempatureData, String weatherCityData, String weatherConditionData, List<ItemData> itemDataList) {
        this.weatherTempatureData = weatherTempatureData;
        this.weatherCityData = weatherCityData;
        this.weatherConditionData = weatherConditionData;
        this.itemDataList = itemDataList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_condition, container, false);
        //实例化控件
        weatherTempature = view.findViewById(R.id.weather_temperature);
        weatherCity = view.findViewById(R.id.weather_city);
        weatherCondition = view.findViewById(R.id.weather_condition);
        conditionImage = view.findViewById(R.id.weather_iv_condition);

        this.weatherTempature.setText(weatherTempatureData);
        this.weatherCity.setText(weatherCityData);
        this.weatherCondition.setText(weatherConditionData);


        indexDress = view.findViewById(R.id.index_dress);
        indexCold = view.findViewById(R.id.index_cold);
        indexCar = view.findViewById(R.id.index_car);
        indexSports = view.findViewById(R.id.index_comfort);
        indexZwx = view.findViewById(R.id.index_zwx);
        indexTravel = view.findViewById(R.id.index_travel);

        detailsLayout = view.findViewById(R.id.weather_details);

        //设置每日指数信息的监听
        indexZwx.setOnClickListener(this);
        indexSports.setOnClickListener(this);
        indexCar.setOnClickListener(this);
        indexCold.setOnClickListener(this);
        indexDress.setOnClickListener(this);
        for (int i = 0; i < itemDataList.size(); i++) {
            ItemData data=itemDataList.get(i);

            View itemView=LayoutInflater.from(getActivity()).inflate(R.layout.item_weather_details,null);
            TextView windText=itemView.findViewById(R.id.weather_wind);
            TextView rangeText=itemView.findViewById(R.id.weather_temperature_range_value);
            TextView dateText=itemView.findViewById(R.id.weather_date);
            TextView sdText=itemView.findViewById(R.id.weather_sd);

            windText.setText(data.getWeatherWind());
            dateText.setText(data.getWeatherDate());
            rangeText.setText(data.getWeatherTRange());
            sdText.setText(data.getWeatherSd());

            detailsLayout.addView(itemView);
        }

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}

