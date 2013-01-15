/**
 * Project Name:Guoli
 * File Name:RoomInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-1-14下午3:45:11
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;
/**
 * ClassName:RoomInfo <br/>
 * @Description:    房型bean
 * Date:     2013-1-14 下午3:45:11 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RoomInfo {
    /**房型名称*/
    private String name;
    /**市场价格*/
    private int marketPrice;
    /**折扣*/
    private float discount;
    /**实际价格*/
    private int realPrice;
    /**房间面积*/
    private int dimensions;
    /**楼层*/
    private String floors;
    /**备注*/
    private String remark;
    /**床宽*/
    private float bedWidth;
    /**可住人数*/
    private int peopleNumbers;
    /**是否是无烟房*/
    private String smokeDesc;
    /**宽带描述*/
    private String networkDesc;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMarketPrice() {
        return marketPrice;
    }
    public void setMarketPrice(int marketPrice) {
        this.marketPrice = marketPrice;
    }
    public float getDiscount() {
        return discount;
    }
    public void setDiscount(float discount) {
        this.discount = discount;
    }
    public int getRealPrice() {
        return realPrice;
    }
    public void setRealPrice(int realPrice) {
        this.realPrice = realPrice;
    }
    public int getDimensions() {
        return dimensions;
    }
    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }
    public String getFloors() {
        return floors;
    }
    public void setFloors(String floors) {
        this.floors = floors;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public float getBedWidth() {
        return bedWidth;
    }
    public void setBedWidth(float bedWidth) {
        this.bedWidth = bedWidth;
    }
    public int getPeopleNumbers() {
        return peopleNumbers;
    }
    public void setPeopleNumbers(int peopleNumbers) {
        this.peopleNumbers = peopleNumbers;
    }
    public String getSmokeDesc() {
        return smokeDesc;
    }
    public void setSmokeDesc(String smokeDesc) {
        this.smokeDesc = smokeDesc;
    }
    public String getNetworkDesc() {
        return networkDesc;
    }
    public void setNetworkDesc(String networkDesc) {
        this.networkDesc = networkDesc;
    }

    @Override
    public String toString() {
        return "[name=" + ", marketPrice=" + marketPrice + ", discount=" + discount + ", realPrice=" + realPrice
                    + ", dimensions=" + dimensions + ", floors=" + floors + ", bedWidth=" + bedWidth
                    + ", peopleNumbers=" + peopleNumbers + ", smokeDesc=" + smokeDesc + ", networkDesc=" + networkDesc 
                    + ", remark=" + remark+ "]";
    }
}

