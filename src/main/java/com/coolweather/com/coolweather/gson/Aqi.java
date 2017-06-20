package com.coolweather.com.coolweather.gson;

/**
 * Created by 江浩 on 2017/6/18.
 */

public class Aqi {
    public AqiCity city;

    public class AqiCity {
        public String aqi;
        public String pm25;
    }
}
