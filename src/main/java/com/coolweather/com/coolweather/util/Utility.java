package com.coolweather.com.coolweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.coolweather.com.coolweather.db.City;
import com.coolweather.com.coolweather.db.County;
import com.coolweather.com.coolweather.db.Province;
import com.coolweather.com.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 江浩 on 2017/6/17.
 */

public class Utility {
    private static final String TAG = "Utility";
    /**
     * 解析并处理服务器返回的省级数据
     */
    public static boolean handleProviceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvince = new JSONArray(response);
                for (int i = 0; i < allProvince.length(); i++) {
                    JSONObject provinceObject = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                Log.i(TAG, "handleProviceResponse: 解析并处理服务器返回的省级数据");
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析并处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                Log.i(TAG, "handleCityResponse:解析并处理服务器返回的市级数据 ");
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * 解析并处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                Log.i(TAG, "handleCountyResponse: response  :   "+response);
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                Log.i(TAG, "handleCountyResponse:解析并处理服务器返回的县级数据");
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, "handleCountyResponse:解析并处理服务器返回的县级数据失败");
        return false;
    }
    /**
     * 将返回的JSON数据解析成Weather实体类
     * @param response
     */
    public static Weather handleWeatherResponse(String response) {
        try {
            Log.i(TAG, "handleWeatherResponse: 将返回的JSON数据解析成Weather实体类");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            Log.i(TAG, "handleWeatherResponse: weatherContent:"+weatherContent);
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
      return null;
    }
}
