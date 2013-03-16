/**
 * Project Name:Guoli
 * File Name:RecommendHotelInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-9下午2:22:51
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:RecommendHotelInfo <br/>
 * @Description:    推荐/搜索结果酒店信息
 * Date:     2013-3-9 下午2:22:51 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RecommendHotelInfo {

    /**酒店ID*/
    @SerializedName("shopid")
    private String id;
    /**入住期间*/
    @SerializedName("date_desc")
    private String date;
    /**推荐理由*/
    @SerializedName("recommend_reason")
    private String reason;
    /**酒店名称*/
    @SerializedName("shopname")
    private String name;
    /**酒店地址*/
    @SerializedName("address")
    private String address;
    /**图片路径*/
    @SerializedName("picpath")
    private String picPath;
    /**商圈ID*/
    @SerializedName("shangquan")
    private String businessArea;
    /***/
    @SerializedName("zone")
    private String zone;
    /**经度*/
    @SerializedName("mapx")
    private double longitude;
    /**纬度*/
    @SerializedName("mapy")
    private double latitude;
    /**市场价格*/
    @SerializedName("actprice")
    private double price;
    /**果粒价格*/
    @SerializedName("guoliprice")
    private double guoliPrice;
    /**折扣*/
    @SerializedName("zheke")
    private double discount;
    /**图片名称*/
    @SerializedName("pic")
    private String picName;
    /***/
    @SerializedName("pid")
    private String pid;
    /***/
    @SerializedName("filename")
    private String filename;
    /**星级*/
    @SerializedName("star")
    private int level;
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
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
    public String getBusinessArea() {
        return businessArea;
    }
    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }
    public String getZone() {
        return zone;
    }
    public void setZone(String zone) {
        this.zone = zone;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getGuoliPrice() {
        return guoliPrice;
    }
    public void setGuoliPrice(double guoliPrice) {
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
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", address=" + address + ", date=" + date
                + ", zone=" + zone + ", businessArea=" + businessArea + ", price=" + price
                + ", guoliPrice=" + guoliPrice + ", discount=" + discount + ", level=" + level
                + ", longitude=" + longitude + ", latitude=" + latitude + ", picPath=" + picPath
                + ", picName=" + picName + ", filename=" + filename + ", pid=" + pid
                + ", reason=" + reason + "}";
    }
}

