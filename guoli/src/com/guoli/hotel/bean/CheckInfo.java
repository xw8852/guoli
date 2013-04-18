/**
 * Project Name:SplashActivity
 * File Name:CheckInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-1-28下午7:57:19
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:CheckInfo <br/>
 * @Description:    入住日期清单bean
 * Date:     2013-1-28 下午7:57:19 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CheckInfo {

    /**日期*/
    @SerializedName("date")
    private String date;
    
    /**价格*/
    @SerializedName("actprice")
    private String price;
    
    /**周几*/
    @SerializedName("week")
    private String week;
    
    /**早餐安排 0-无1-单2-双*/
    @SerializedName("breakfast")
    private String breakfast;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }
}

