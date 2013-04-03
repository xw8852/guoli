/**
 * Project Name:Guoli
 * File Name:TrafficInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-15上午10:32:54
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;


/**
 * ClassName:TrafficInfo <br/>
 * 
 * @Description: 酒店详情中的交通 Date: 2013-3-15 上午10:32:54 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class TrafficInfo {
    
    /**位置名称*/
    @SerializedName("add")
    private String name;
    
    /**距离*/
    @SerializedName("distance")
    private String distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
