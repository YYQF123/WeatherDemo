package com.example.weather.ManageCity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.weather.R;
import com.example.weather.db.DataBean;
import com.example.weather.db.DataManager;

import java.util.ArrayList;
import java.util.List;

public class CityManageActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView addIV,backIV,editIV;
    ListView itemManageList;
    List<DataBean> manageListData;
    private CityManageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manage);
        addIV=findViewById(R.id.manage_add);
        backIV=findViewById(R.id.manage_iv_back);
        editIV=findViewById(R.id.manage_iv_delete);
        itemManageList=findViewById(R.id.manage_listView);
        manageListData=new ArrayList<>();
        //给图片添加监听
        addIV.setOnClickListener(this);
        backIV.setOnClickListener(this);
        editIV.setOnClickListener(this);

        //设置适配器
        adapter = new CityManageAdapter(this, manageListData);
        itemManageList.setAdapter(adapter);

    }

    //获取焦点的生命周期中获取当前的真实数据源，提示适配器更新
    @Override
    protected void onResume() {
        super.onResume();
        List<DataBean> dataList = DataManager.queryAll();
        manageListData.clear();
        manageListData.addAll(dataList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.manage_add:
                int IDCount= DataManager.getCityIDCount();
                //城市数量上限为3
                if(IDCount <=3){
                Intent intent = new Intent(this, CityAddActivity.class);
                startActivity(intent);
                }else {
                    Toast.makeText(this, "城市数量已满", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.manage_iv_back:
                finish();
                break;
            case R.id.manage_iv_delete:
                Intent intent2 = new Intent(this, CityEditActivity.class);
                startActivity(intent2);
                break;
        }
    }
}