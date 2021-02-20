package com.example.weather.ManageCity;

public class FakeManageData {
    String city;
    String temperature;
    String condition;
    String wind;
    String tempRange;

    public FakeManageData(String city, String temperature, String condition, String wind, String tempRange) {
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
        this.wind = wind;
        this.tempRange = tempRange;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTempRange() {
        return tempRange;
    }

    public void setTempRange(String tempRange) {
        this.tempRange = tempRange;
    }
}
