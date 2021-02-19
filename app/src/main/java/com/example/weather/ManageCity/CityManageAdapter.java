package com.example.weather.ManageCity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.weather.R;
import com.example.weather.baseClass.HttpGetCityLocation;
import com.example.weather.db.DataBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CityManageAdapter extends BaseAdapter {
    Context context;
    List<DataBean> listData;
    ViewHolder holder=null;


    public CityManageAdapter(Context context, List<DataBean> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
//?
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {

            if(convertView==null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_city_manage, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else {
                holder=(ViewHolder) convertView.getTag();
            }
            DataBean dataBean = listData.get(position);
            String key;
            String url="https://devapi.qweather.com/v7/weather/3d?"+"location="+dataBean.getCityLocation()+key

            Handler handler=new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    if(msg.what==10){
                        String response=(String) msg.obj;
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("location");
                            JSONObject jsonObject1=jsonArray.getJSONObject(0);
                            String cityName=jsonObject1.getString("name");
                            holder.cityTV.setText(cityName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }
            });
            HttpGetCityLocation.loadHttpData(url,handler);

            JSONObject jsonObject=new JSONObject(dataBean.getJSONContent());
            JSONArray daily=jsonObject.getJSONArray("daily");
            JSONObject object=daily.getJSONObject(0);

            String date=object.getString("fxDate");
            String tempMax=object.getString("tempMax");
            String tempMin=object.getString("tempMin");

            String tempRange=tempMin+"-"+tempMax;
            String wind=object.getString("windDirDay");
            String temperature=Integer.toString((Integer.parseInt(tempMax)+Integer.parseInt(tempMin))/2);
            String condition=object.getString("textDay");

            holder.tempTV.setText(temperature);
            holder.conditionTV.setText(condition);
            holder.windTV.setText(wind);
            holder.tempRangeTV.setText(tempRange);



            }catch (Exception e){
            e.printStackTrace();
        }
            return convertView;
        }

    }
    //?
    class ViewHolder{
        TextView cityTV,tempTV,conditionTV,windTV,tempRangeTV;
        public ViewHolder(View itemView){
            cityTV=itemView.findViewById(R.id.item_cityName);
            tempTV=itemView.findViewById(R.id.item_temp);
            conditionTV=itemView.findViewById(R.id.item_condition);
            windTV=itemView.findViewById(R.id.item_wind);
            tempRangeTV=itemView.findViewById(R.id.item_tempRange);

        }
    }

