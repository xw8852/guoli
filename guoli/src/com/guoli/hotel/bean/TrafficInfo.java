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
 * @Description:    酒店详情中的交通
 * Date:     2013-3-15 上午10:32:54 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TrafficInfo {

    /**市中心*/
    @SerializedName("shizhongxin")
    private String cityCenter;
    
    /**机场*/
    @SerializedName("jichang")
    private String airPort;
    
    /**火车站*/
    @SerializedName("huochezhan")
    private String railwayStation;

    public String getCityCenter() {
        return cityCenter;
    }

    public void setCityCenter(String cityCenter) {
        this.cityCenter = cityCenter;
    }

    public String getAirPort() {
        return airPort;
    }

    public void setAirPort(String airPort) {
        this.airPort = airPort;
    }

    public String getRailwayStation() {
        return railwayStation;
    }

    public void setRailwayStation(String railwayStation) {
        this.railwayStation = railwayStation;
    }
    
    @Override
    public String toString() {
        return "{cityCenter=" + cityCenter + ", airPort=" + airPort + ", railwayStation=" + railwayStation +"}";
    }
}

