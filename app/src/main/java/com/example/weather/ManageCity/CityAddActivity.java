package com.example.weather.ManageCity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weather.MainActivity;
import com.example.weather.R;
import com.example.weather.baseClass.HttpGetCityLocation;
import com.example.weather.baseClass.HttpIfAddCityExistActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CityAddActivity extends HttpIfAddCityExistActivity implements View.OnClickListener {
    EditText searchEt;
    ImageView submitIv;

    String city;


    String urlFirst="https://devapi.qweather.com/v7/weather/3d?";
    String location;

    //key
    String urlThird;

    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if(msg.what==10){
                String response=(String) msg.obj;

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("location");
                    JSONObject jsonObject1=jsonArray.getJSONObject(0);
                    location= jsonObject1.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_add);
        searchEt=findViewById(R.id.add_et_search);
        submitIv=findViewById(R.id.add_iv_search);
        submitIv.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.add_iv_search){
            city = searchEt.getText().toString();
            if(!TextUtils.isEmpty(city)){
                String urlGetLocation=urlFirst+"location="+city+"&"+urlThird;
                HttpGetCityLocation.loadHttpData(urlGetLocation,handler);

                String urlSecond="location="+location+"&";
                String urlFinal=urlFirst+urlSecond+urlThird;
                loadHttpData(urlFinal);

            }else {
                Toast.makeText(this,"输入为空",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void onSuccess(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            String code=jsonObject.getString("code");
            String code2="200";
            if(code2.equals(code)){
                Intent intent=new Intent(this, MainActivity.class);
                //?
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("cityName",city);
                startActivity(intent);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}