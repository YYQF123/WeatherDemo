package com.example.weather.db;

public class DataBean {
    private String cityLocation;
    private String JSONContent;
    private int _id;

    public DataBean() {
    }

    public DataBean(String cityLocation, String JSONContent, int id) {
        this.cityLocation = cityLocation;
        this.JSONContent = JSONContent;
        this._id = id;
    }

    public String getCityLocation() {
        return cityLocation;
    }

    public void setCityLocation(String cityLocation) {
        this.cityLocation = cityLocation;
    }

    public String getJSONContent() {
        return JSONContent;
    }

    public void setJSONContent(String JSONContent) {
        this.JSONContent = JSONContent;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }
}
