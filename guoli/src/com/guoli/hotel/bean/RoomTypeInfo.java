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
    private float price;
    
    /**折扣*/
    @SerializedName("zheke")
    private float discount;
    
    /**减少的价格*/
    @SerializedName("reduceprice")
    private float reducePrice;
    
    /**床型 1-大床,2-双人床,3-床型不定*/
    @SerializedName("chuangtype")
    private int bedType;
    
    /**早餐类型 1-无,2-单早,3-双早*/
    @SerializedName("breakfasttype")
    private int breakfastType;
    
    /**房间面积*/
    @SerializedName("area")
    private int area;
    
    /**宽带类型1-免费,2-付费*/
    @SerializedName("broadbandtype")
    private int broadbandType;
    
    /**可住人数*/
    @SerializedName("checknum")
    private int checkNum;
    
    /**是否可用加床 1-可加,0-不可加*/
    @SerializedName("isjiachuang")
    private int isAddBed;
    
    /**早餐价格*/
    @SerializedName("breakfastprice")
    private float breakfastPrice;
    
    /**加床价格*/
    @SerializedName("addchuangprice")
    private float addchuangPrice;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(float reducePrice) {
        this.reducePrice = reducePrice;
    }

    public int getBedType() {
        return bedType;
    }

    public void setBedType(int bedType) {
        this.bedType = bedType;
    }

    public int getBreakfastType() {
        return breakfastType;
    }

    public void setBreakfastType(int breakfastType) {
        this.breakfastType = breakfastType;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getBroadbandType() {
        return broadbandType;
    }

    public void setBroadbandType(int broadbandType) {
        this.broadbandType = broadbandType;
    }

    public int getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }

    public int getIsAddBed() {
        return isAddBed;
    }

    public void setIsAddBed(int isAddBed) {
        this.isAddBed = isAddBed;
    }

    public float getBreakfastPrice() {
        return breakfastPrice;
    }

    public void setBreakfastPrice(float breakfastPrice) {
        this.breakfastPrice = breakfastPrice;
    }

    public float getAddchuangPrice() {
        return addchuangPrice;
    }

    public void setAddchuangPrice(float addchuangPrice) {
        this.addchuangPrice = addchuangPrice;
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

