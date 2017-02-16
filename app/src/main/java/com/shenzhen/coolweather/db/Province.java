package com.shenzhen.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * @author wizong @Date 2017/2/16 16:12 @Description TODO
 */
public class Province extends DataSupport {
    private int id;
    private String provinceName;

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    private int provinceCode;
}
