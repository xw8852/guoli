/**
 * Project Name:Guoli
 * File Name:HotelInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-1-11下午12:57:01
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;
/**
 * ClassName:HotelInfo <br/>
 * @Description:    酒店信息bean
 * Date:     2013-1-11 下午12:57:01 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelInfo {
    /**酒店ID*/
    private String id;
    /**酒店名称*/
    private String name;
    /**酒店地址*/
    private String address;
    /**酒店所属区域*/
    private String area;
    /**酒店缩略图地址*/
    private String picPath;
    /**酒店级别*/
    private int level;
    /**酒店价格*/
    private int price;
    /**酒店折扣*/
    private float discount;
    /**酒店电话*/
    private String phoneNumber;
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
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getPicPath() {
        return picPath;
    }
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public float getDiscount() {
        return discount;
    }
    public void setDiscount(float discount) {
        this.discount = discount;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", address=" + address + ", area=" + area
                + ", phoneNumber=" + phoneNumber + ", level=" + level + ", price=" + price
                + ", discount=" + discount + ", picPath=" + picPath;
    }
}

