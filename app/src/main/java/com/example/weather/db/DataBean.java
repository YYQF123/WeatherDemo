package com.example.weather.db;

public class DataBean {
    private String cityName;
    private String JSONContent;
    private int _id;

    public DataBean() {
    }

    public DataBean(String cityName, String JSONContent, int _id) {
        this.cityName = cityName;
        this.JSONContent = JSONContent;
        this._id = _id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getJSONContent() {
        return JSONContent;
    }

    public void setJSONContent(String JSONContent) {
        this.JSONContent = JSONContent;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
