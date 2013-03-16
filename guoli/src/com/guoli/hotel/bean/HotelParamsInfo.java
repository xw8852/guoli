/**
 * Project Name:Guoli
 * File Name:HotelParamsInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-15下午9:47:31
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:HotelParamsInfo <br/>
 * @Description:    接口返回结果中的param对象
 * Date:     2013-3-15 下午9:47:31 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelParamsInfo {

    /**酒店ID*/
    @SerializedName("shopid")
    private String id;
    
    /**入住时间*/
    @SerializedName("indate")
    private String startDate;
    
    /**离店时间*/
    @SerializedName("outdate")
    private String endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    @Override
    public String toString() {
        return "{id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "}";
    }
}

