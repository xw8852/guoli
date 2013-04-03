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

import java.util.List;

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
    private List<TrafficInfo> trafficInfos;
    
    /**客房设施*/
    @SerializedName("roomfacility")
    private String roomFacility;
    
    /**餐饮设施*/
    @SerializedName("diningfacility")
    private String diningFacility;
    
    /**娱乐设施*/
    @SerializedName("entertainmentfacility")
    private String entertainmentFacility;
    
    /**可接受信用课*/
    @SerializedName("creditcard")
    private String creditCard;
    
    /***/
    @SerializedName("isbrand")
    private int isBrand;
    
    /***/
    @SerializedName("ispark")
    private int isPark;
    
    /**电话*/
    @SerializedName("phone")
    private String phone;
    
    /**酒店图片名称*/
    @SerializedName("filename")
    private String picName;
    
    /**提示*/
    @SerializedName("prompt")
    private String notice;
    
    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

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

    public List<TrafficInfo> getTrafficInfos() {
        return trafficInfos;
    }

    public void setTrafficInfos(List<TrafficInfo> trafficInfos) {
        this.trafficInfos = trafficInfos;
    }

    public String getRoomFacility() {
        return roomFacility;
    }

    public void setRoomFacility(String roomFacility) {
        this.roomFacility = roomFacility;
    }

    public String getDiningFacility() {
        return diningFacility;
    }

    public void setDiningFacility(String diningFacility) {
        this.diningFacility = diningFacility;
    }

    public String getEntertainmentFacility() {
        return entertainmentFacility;
    }

    public void setEntertainmentFacility(String entertainmentFacility) {
        this.entertainmentFacility = entertainmentFacility;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public int getIsBrand() {
        return isBrand;
    }

    public void setIsBrand(int isBrand) {
        this.isBrand = isBrand;
    }

    public int getIsPark() {
        return isPark;
    }

    public void setIsPark(int isPark) {
        this.isPark = isPark;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }
    
   /* @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", address=" + address + ", picPath=" + picPath
                + ", star=" + star + ", openDate=" + opendate + ", decorationDate=" + decorationdate
                + ", mapx=" + mapx + ", mapy=" + mapy + "}";
    }*/
}

