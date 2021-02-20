package com.example.weather.ManageCity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.weather.R;

import java.util.List;

public class CityManageFakeAdapter extends BaseAdapter {
    Context context;
    List<FakeManageData> dataList;
    ViewHolder holder=null;


    public CityManageFakeAdapter(Context context,List<FakeManageData> dataList) {
        this.context=context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_city_manage, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FakeManageData data=dataList.get(position);
        holder.cityTV.setText(data.getCity());
        holder.tempTV.setText(data.getTemperature());
        holder.conditionTV.setText(data.getCondition());
        holder.windTV.setText(data.getWind());
        holder.tempRangeTV.setText(data.getTempRange());


        return convertView;
    }

    class ViewHolder {
        TextView cityTV, tempTV, conditionTV, windTV, tempRangeTV;

        public ViewHolder(View itemView) {
            cityTV = itemView.findViewById(R.id.item_cityName);
            tempTV = itemView.findViewById(R.id.item_temp);
            conditionTV = itemView.findViewById(R.id.item_condition);
            windTV = itemView.findViewById(R.id.item_wind);
            tempRangeTV = itemView.findViewById(R.id.item_tempRange);

        }
    }
}

