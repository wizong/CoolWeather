package com.shenzhen.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * @author wizong @Date 2017/2/16 16:15 @Description TODO
 */
public class County extends DataSupport{
    private int id;
    private String countyName;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    private int cityId;
    private int weatherId;
}
