/**
 * Project Name:SplashActivity
 * File Name:CityInfo.java
 * Package Name:com.guoli.hotel.net.bean
 * Date:2013-2-2下午4:39:55
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.bean;
/**
 * ClassName:CityInfo <br/>
 * @Description:    城市对象
 * Date:     2013-2-2 下午4:39:55 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CityInfo {
    /**城市名称*/
    private String cityName;
    
    /**城市编码*/
    private String cityCode;
    
    /**城市首字母*/
    private String firstChar;
    
    /**是否是当前所选城市*/
    private boolean isChecked;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
    
    @Override
    public String toString() {
        return "{cityName:"+ cityName + ", cityCode:" + cityCode + ", firstChar:" + firstChar
                + ", isChecked:" + isChecked + "}";
    }
}

