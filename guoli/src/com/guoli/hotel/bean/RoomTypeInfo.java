/**
 * Project Name:Guoli
 * File Name:RoomTypeInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-15下午5:15:54
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:RoomTypeInfo <br/>
 * @Description:    房型信息
 * Date:     2013-3-15 下午5:15:54 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RoomTypeInfo {

    /***/
    @SerializedName("pid")
    private String pid;
    
    /**售价 <1表示需要电询*/
    @SerializedName("actprice")
    private float actprice;
    
    /**房间类型名称*/
    @SerializedName("name")
    private String name;
    
    /***/
    @SerializedName("price")
    private String price;
    
    /**折扣*/
    @SerializedName("zheke")
    private String discount;
    
    /**减少的价格*/
    @SerializedName("reduceprice")
    private String reducePrice;
    
    /**床型 1-大床,2-双人床,3-床型不定*/
    @SerializedName("chuangtype")
    private String bedType;
    
    /**早餐类型 1-无,2-单早,3-双早*/
    @SerializedName("breakfasttype")
    private int breakfastType;
    
    /**房间面积*/
    @SerializedName("area")
    private String area;
    
    /**宽带类型1-免费,2-付费*/
    @SerializedName("broadbandtype")
    private String broadbandType;
    
    /**可住人数*/
    @SerializedName("checknum")
    private String checkNum;
    
    /**是否可用加床 1-可加,0-不可加*/
    @SerializedName("isjiachuang")
    private String isAddBed;
    
    /**早餐价格*/
    @SerializedName("breakfastprice")
    private String breakfastPrice;
    
    /**加床价格*/
    @SerializedName("addchuangprice")
    private String addchuangPrice;
    
    /**楼层*/
    @SerializedName("louceng")
    private String floor;
    
    /**床宽*/
    @SerializedName("chuangkuan")
    private String bedWidth;
    
    /**是否是无烟房*/
    @SerializedName("wuyanfang")
    private String allowSmoking;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public float getActprice() {
        return actprice;
    }

    public void setActprice(float actprice) {
        this.actprice = actprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(String reducePrice) {
        this.reducePrice = reducePrice;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public int getBreakfastType() {
        return breakfastType;
    }

    public void setBreakfastType(int breakfastType) {
        this.breakfastType = breakfastType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBroadbandType() {
        return broadbandType;
    }

    public void setBroadbandType(String broadbandType) {
        this.broadbandType = broadbandType;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getIsAddBed() {
        return isAddBed;
    }

    public void setIsAddBed(String isAddBed) {
        this.isAddBed = isAddBed;
    }

    public String getBreakfastPrice() {
        return breakfastPrice;
    }

    public void setBreakfastPrice(String breakfastPrice) {
        this.breakfastPrice = breakfastPrice;
    }

    public String getAddchuangPrice() {
        return addchuangPrice;
    }

    public void setAddchuangPrice(String addchuangPrice) {
        this.addchuangPrice = addchuangPrice;
    }
    
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBedWidth() {
        return bedWidth;
    }

    public void setBedWidth(String bedWidth) {
        this.bedWidth = bedWidth;
    }

    public String getAllowSmoking() {
        return allowSmoking;
    }

    public void setAllowSmoking(String allowSmoking) {
        this.allowSmoking = allowSmoking;
    }

    @Override
    public String toString() {
        return "{pid=" + pid + ", actprice=" + actprice + ", name=" + name + ", price=" + price
                + ", discount=" + discount + ", reducePrice=" + reducePrice + ", bedType=" + bedType
                + ", breakfastType=" + breakfastType + ", area=" + area + ", broadbandType=" + broadbandType
                + ", checkNum=" + checkNum + ", isAddBed=" + isAddBed + ", breakfastPrice=" + breakfastPrice
                + ", addchuangPrice=" + addchuangPrice + "}";
    }
}

