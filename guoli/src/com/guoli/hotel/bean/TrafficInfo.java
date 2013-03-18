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

import java.util.List;

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

    /** 市中心 */
    @SerializedName("shizhongxin")
    private List<String> cityCenter;

    /** 机场 */
    @SerializedName("jichang")
    private List<String> airPorts;

    /** 火车站 */
    @SerializedName("huochezhan")
    private List<String> railwayStations;

    public List<String> getCityCenter() {
        return cityCenter;
    }

    public void setCityCenter(List<String> cityCenter) {
        this.cityCenter = cityCenter;
    }

    public List<String> getAirPorts() {
        return airPorts;
    }

    public void setAirPorts(List<String> airPorts) {
        this.airPorts = airPorts;
    }

    public List<String> getRailwayStations() {
        return railwayStations;
    }

    public void setRailwayStations(List<String> railwayStations) {
        this.railwayStations = railwayStations;
    }

}
