/**
 * Project Name:Guoli
 * File Name:RecommendInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-18上午9:24:18
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:RecommendInfo <br/>
 * @Description:    酒店推荐详情
 * Date:     2013-3-18 上午9:24:18 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RecommendInfo {

    /**酒店ID*/
    @SerializedName("shopid")
    private String id;
    
    /**酒店名称*/
    @SerializedName("shopname")
    private String name;
    
    /**酒店入住期间*/
    @SerializedName("date_desc")
    private String date;
    
    /**酒店地址*/
    @SerializedName("address")
    private String address;
    
    /**推荐理由*/
    @SerializedName("recommend_reason")
    private String reason;
    
    /**酒店简介*/
    @SerializedName("abstract")
    private String brief;
    
    /**酒店电话*/
    @SerializedName("phone")
    private String phone;
    
    /**酒店星级*/
    @SerializedName("star")
    private int level;
    
    /**图片路径*/
    @SerializedName("picpath")
    private String picPath;
    
    /**商圈*/
    @SerializedName("shangquan")
    private String shangquan;
    
    /**区域*/
    @SerializedName("zone")
    private String zone;
    
    /**经度*/
    @SerializedName("mapx")
    private double mapx;
    
    /**纬度*/
    @SerializedName("mapy")
    private double mapy;
    
    /***/
    @SerializedName("pid")
    private String pid;
    
    /**市场价格*/
    @SerializedName("actprice")
    private int price;
    
    /**果粒价格*/
    @SerializedName("guoliprice")
    private int guoliPrice;
    
    /**折扣*/
    @SerializedName("zheke")
    private double discount;
    
    /**图片名称*/
    @SerializedName("pic")
    private String picName;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getShangquan() {
        return shangquan;
    }

    public void setShangquan(String shangquan) {
        this.shangquan = shangquan;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGuoliPrice() {
        return guoliPrice;
    }

    public void setGuoliPrice(int guoliPrice) {
        this.guoliPrice = guoliPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }
}

