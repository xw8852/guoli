/**
 * Project Name:Guoli
 * File Name:HotelDetailInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-14下午8:37:31
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:HotelDetailInfo <br/>
 * @Description:    酒店详情
 * Date:     2013-3-14 下午8:37:31 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelDetailInfo {

    /**酒店ID*/
    private String id;
    
    /**酒店名称*/
    @SerializedName("shopname")
    private String name;
    
    /**酒店地址*/
    @SerializedName("address")
    private String address;
    
    /**酒店图片地址*/
    @SerializedName("picpath")
    private String picPath;
    
    /**酒店介绍*/
    @SerializedName("introduce")
    private String introduce;
    
    /**酒店星级*/
    @SerializedName("star")
    private int star;
    
    /**开业时间*/
    @SerializedName("opendate")
    private String opendate;
    
    /**装修时间*/
    @SerializedName("decorationdate")
    private String decorationdate;
    
    /**地图经度*/
    @SerializedName("mapx")
    private double mapx;
    
    /**地图纬度*/
    @SerializedName("mapy")
    private double mapy;
    
    /**交通信息*/
    @SerializedName("trafficinfo")
    private TrafficInfo trafficInfo;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getOpendate() {
        return opendate;
    }

    public void setOpendate(String opendate) {
        this.opendate = opendate;
    }

    public String getDecorationdate() {
        return decorationdate;
    }

    public void setDecorationdate(String decorationdate) {
        this.decorationdate = decorationdate;
    }

    public double getMapx() {
        return mapx;
    }

    public void setMapx(double mapx) {
        this.mapx = mapx;
    }

    public double getMapy() {
        return mapy;
    }

    public void setMapy(double mapy) {
        this.mapy = mapy;
    }

    public TrafficInfo getTrafficInfo() {
        return trafficInfo;
    }

    public void setTrafficInfo(TrafficInfo trafficInfo) {
        this.trafficInfo = trafficInfo;
    }

   /* @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", address=" + address + ", picPath=" + picPath
                + ", star=" + star + ", openDate=" + opendate + ", decorationDate=" + decorationdate
                + ", mapx=" + mapx + ", mapy=" + mapy + "}";
    }*/
}

