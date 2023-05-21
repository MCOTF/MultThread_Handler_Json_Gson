package com.mcotf.handlerjsongson;

import com.google.gson.annotations.SerializedName;

public class WeatherBean{
    //此处为了方便Gson获取数据用的

    //解决键值不对应可添加注解@SerializedName
    @SerializedName("cityid")
    private String city_id;
    @SerializedName("city")
    private String city;
    @SerializedName("wea")
    private String weather;
    private String updateTime;
    private String weatherImg;
    private String tem;
    private String weNight;
    private String win;
    private String winSpeed;
    private String winMeter;
    private String air;
/*{"nums":0,"cityid":"101121001","city":"菏泽","date":"2023-05-21","week":"星期日","update_time":"23:42","wea":"多云","wea_img":"yun","tem":"14","tem_day":"17","tem_night":"11","win":"西北风","win_speed":"3级","win_meter":"13km\/h","air":"73","pressure":"1011","humidity":"89%"}*/

    @Override
    public String toString() {
        return "WeatherBean{" +
                "city_id='" + city_id + '\'' +
                ", city='" + city + '\'' +
                ", weather='" + weather + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", weatherImg='" + weatherImg + '\'' +
                ", tem='" + tem + '\'' +
                ", weNight='" + weNight + '\'' +
                ", win='" + win + '\'' +
                ", winSpeed='" + winSpeed + '\'' +
                ", winMeter='" + winMeter + '\'' +
                ", air='" + air + '\'' +
                '}';
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getWeatherImg() {
        return weatherImg;
    }

    public void setWeatherImg(String weatherImg) {
        this.weatherImg = weatherImg;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getWeNight() {
        return weNight;
    }

    public void setWeNight(String weNight) {
        this.weNight = weNight;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getWinSpeed() {
        return winSpeed;
    }

    public void setWinSpeed(String winSpeed) {
        this.winSpeed = winSpeed;
    }

    public String getWinMeter() {
        return winMeter;
    }

    public void setWinMeter(String winMeter) {
        this.winMeter = winMeter;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }
}
