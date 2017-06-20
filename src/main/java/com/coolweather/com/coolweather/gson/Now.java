package com.coolweather.com.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 江浩 on 2017/6/18.
 */

public class Now {
    @SerializedName("tmp")
    public String tmperature;
    @SerializedName("cond")
    public Cond more;

    public class Cond {
        @SerializedName("txt")
        public String info;
    }
}
