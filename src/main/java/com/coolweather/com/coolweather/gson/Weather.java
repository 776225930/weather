package com.coolweather.com.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 江浩 on 2017/6/18.
 */

public class Weather {
    public String status;
    public Basic  basic;
    public Aqi aqi;
    public Now now;
    public Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
