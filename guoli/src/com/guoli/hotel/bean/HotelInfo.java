/**
 * Project Name:Guoli
 * File Name:ItemHotelInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-11下午9:53:01
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:ItemHotelInfo <br/>
 * @Description:    酒店列表item对象
 * Date:     2013-3-11 下午9:53:01 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelInfo {
    /**酒店ID*/
    @SerializedName("shopid")
    private String id;
    /**酒店名称*/
    @SerializedName("shopname")
    private String name;
    /**酒店地址*/
    @SerializedName("address")
    private String address;
    /**酒店图片相对路径eg:0903/46787/*/
    @SerializedName("picpath")
    private String picPath;
    /**商圈ID*/
    @SerializedName("shangquan")
    private String businessDistrict;
    /**所属区域ID*/
    @SerializedName("zone")
    private String district;
    /**酒店星级*/
    @SerializedName("star")
    private String starLavel;
    /**酒店经度*/
    @SerializedName("mapx")
    private String mapx;
    /**酒店纬度*/
    @SerializedName("mapy")
    private String mapy;
    /**市场价格*/
    @SerializedName("price")
    private String price;
    /**果粒价格*/
    @SerializedName("guoliprice")
    private String guoliPrice;
    /**折扣*/
    @SerializedName("zheke")
    private String discount;
    /***/
    @SerializedName("pid")
    private String pid;
    /**图片名称*/
    @SerializedName("filename")
    private String fileName;
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
    public String getBusinessDistrict() {
        return businessDistrict;
    }
    public void setBusinessDistrict(String businessDistrict) {
        this.businessDistrict = businessDistrict;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getStarLavel() {
        return starLavel;
    }
    public void setStarLavel(String starLavel) {
        this.starLavel = starLavel;
    }
    public String getMapx() {
        return mapx;
    }
    public void setMapx(String mapx) {
        this.mapx = mapx;
    }
    public String getMapy() {
        return mapy;
    }
    public void setMapy(String mapy) {
        this.mapy = mapy;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getGuoliPrice() {
        return guoliPrice;
    }
    public void setGuoliPrice(String guoliPrice) {
        this.guoliPrice = guoliPrice;
    }
    public String getDiscount() {
        return discount;
    }
    public void setDiscount(String discount) {
        this.discount = discount;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", address=" + address + ", picPath=" + picPath
                + ", fileName=" + fileName + ", businessDistrict=" + businessDistrict
                + ", district=" + district + ", starLavel=" + starLavel + ", mapx=" + mapx + ", mapy=" + mapy
                + ", price=" + price + ", guoliPrice=" + guoliPrice + ", discount=" + discount
                + ", pid=" + pid + "}";
    }
}

