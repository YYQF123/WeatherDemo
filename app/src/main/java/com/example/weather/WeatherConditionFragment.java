package com.example.weather;

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.weather.base.GetCityName;
import com.example.weather.base.LoadDataFragment;
import com.example.weather.db.DataManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class WeatherConditionFragment extends LoadDataFragment implements View.OnClickListener{



    TextView weatherTempature,weatherCity,weatherCondition;
    TextView weatherTRange,weatherDate,weatherWind,weatherSd;
    TextView indexDress,indexCold,indexCar,indexSports,indexZwx,indexTravel;
    ImageView conditionImage;
    LinearLayout detailsLayout;

    //设置城市名称
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if(msg.what==10){
                String response=(String) msg.obj;

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("location");
                    JSONObject jsonObject1=jsonArray.getJSONObject(0);
                    String cityName=jsonObject1.getString("name");
                    weatherCity.setText(cityName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            return false;
        }
    });

    String urlFirst="https://devapi.qweather.com/v7/weather/3d?";
    String location;
    //key
    String urlThird;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_weather_condition, container, false);
        //实例化控件
        weatherTempature=view.findViewById(R.id.weather_temperature);
        weatherCity=view.findViewById(R.id.weather_city);
        weatherCondition=view.findViewById(R.id.weather_condition);
        conditionImage=view.findViewById(R.id.weather_iv_condition);

        weatherDate=view.findViewById(R.id.weather_date);
        weatherWind=view.findViewById(R.id.weather_wind);
        weatherTRange=view.findViewById(R.id.weather_temperature_range);
        weatherSd=view.findViewById(R.id.weather_sd);


        indexDress=view.findViewById(R.id.index_dress);
        indexCold=view.findViewById(R.id.index_cold);
        indexCar=view.findViewById(R.id.index_car);
        indexSports=view.findViewById(R.id.index_comfort);
        indexZwx=view.findViewById(R.id.index_zwx);
        indexTravel=view.findViewById(R.id.index_travel);

        detailsLayout=view.findViewById(R.id.weather_details);

        //设置每日指数信息的监听
        indexZwx.setOnClickListener(this);
        indexSports.setOnClickListener(this);
        indexCar.setOnClickListener(this);
        indexCold.setOnClickListener(this);
        indexDress.setOnClickListener(this);

        //通过Acitivity传入当前fragment的地区id,拼接网址url
        Bundle bundle=getArguments();
        location = bundle.getString("location");
        String urlSecond="location="+location+"&";
        String urlFinal=urlFirst+urlSecond+urlThird;

        loadHttpData(urlFinal);
        GetCityName.loadHttpData(urlFinal,handler);

        return view;
    }

    private void showData(String response) {
        //解析和展示的具体实现
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray daily=jsonObject.getJSONArray("daily");
            for (int i = 0; i < daily.length(); i++) {
                JSONObject object=daily.getJSONObject(i);
                String date=object.getString("fxDate");
                String tempMax=object.getString("tempMax");
                String tempMin=object.getString("tempMin");
                String tempRange=tempMin+"-"+tempMax;
                String wind=object.getString("windDirDay");
                String sd=object.getString("humidity");

                String temperature=Integer.toString((Integer.parseInt(tempMax)+Integer.parseInt(tempMin))/2);
                String condition=object.getString("textDay");
                weatherTempature.setText(temperature);
                weatherCondition.setText(condition);

                View itemView=LayoutInflater.from(getActivity()).inflate(R.layout.item_weather_details,null);
                detailsLayout.addView(itemView);
                TextView windText=itemView.findViewById(R.id.weather_wind);
                TextView rangeText=itemView.findViewById(R.id.weather_temperature_range_value);
                TextView dateText=itemView.findViewById(R.id.weather_date);
                TextView sdText=itemView.findViewById(R.id.weather_sd);
                windText.setText(wind);
                dateText.setText(date);
                rangeText.setText(tempRange);
                sdText.setText(sd);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onSuccess(String response) {
        //覆写父类onSuccess方法，解析，展示数据
        showData(response);

        //更新数据
        int a=DataManager.updateContentByID(location,response);
        if(a<=0){
            //更新失败，需要增加这个城市
            DataManager.addCityID(location,response);
        }

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.index_car:
                break;
            case R.id.index_cold:
                break;
            case R.id.index_comfort:
                break;
            case R.id.index_zwx:
                break;
            case R.id.index_dress:
                break;
        }

    }
}