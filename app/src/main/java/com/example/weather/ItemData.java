package com.example.weather;

public class ItemData {
    String weatherTRange;
    String weatherDate;
    String weatherWind;
    String weatherSd;

    public ItemData(String weatherTRange, String weatherDate, String weatherWind, String weatherSd) {
        this.weatherTRange = weatherTRange;
        this.weatherDate = weatherDate;
        this.weatherWind = weatherWind;
        this.weatherSd = weatherSd;
    }

    public String getWeatherTRange() {
        return weatherTRange;
    }

    public void setWeatherTRange(String weatherTRange) {
        this.weatherTRange = weatherTRange;
    }

    public String getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(String weatherDate) {
        this.weatherDate = weatherDate;
    }

    public String getWeatherWind() {
        return weatherWind;
    }

    public void setWeatherWind(String weatherWind) {
        this.weatherWind = weatherWind;
    }

    public String getWeatherSd() {
        return weatherSd;
    }

    public void setWeatherSd(String weatherSd) {
        this.weatherSd = weatherSd;
    }
}
