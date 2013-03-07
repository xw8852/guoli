/**
 * Project Name:Guoli
 * File Name:SearchInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-7下午3:28:32
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:SearchInfo <br/>
 * 
 * @Description: 酒店搜索条件对象 Date: 2013-3-7 下午3:28:32 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class SearchInfo implements Parcelable {

    /** 城市编码 */
    @SerializedName("citycode")
    private String cityCode;

    /** 入住时间 yyyy-MM-dd */
    @SerializedName("Indate")
    private String startDate;

    /** 离店时间 yyyy-MM-dd */
    @SerializedName("Outdate")
    private String endDate;

    /** 价格区间 2-500以下,5-500至800,8-800至1200,10-1200以上*/
    @SerializedName("price")
    private String price;

    /** 酒店星级 '02'-五星,'03'-四星, '04'-三星以下 */
    @SerializedName("starlevel")
    private String level;

    /** 1-区域,2-商圈 */
    @SerializedName("areaflag")
    private String areaType;
    
    /**行政区域编码*/
    @SerializedName("Area")
    private String area;

    /** 搜索关键字 */
    @SerializedName("keyword")
    private String keyWord;

    /** 排序类型：'guoli'-果粒推荐,'price_desc'-价格由高到低,'price_asc'-价格由低到高,'discount_asc'-折扣由高到低 */
    @SerializedName("Orderkey")
    private String orderKey;
    
    /**当前页码,默认由1开始*/
    @SerializedName("Pageno")
    private String pageNum;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }
    
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "{cityCode=" + cityCode + ", startDate=" + startDate + ", endDate=" + endDate
                + ", price=" + price + ", level=" + level + ", areaType=" + areaType
                + ", area=" + area
                + ", keyWord=" + keyWord + ", orderKey=" + orderKey + ", pageNum=" + pageNum + "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityCode);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(price);
        dest.writeString(level);
        dest.writeString(keyWord);
        dest.writeString(orderKey);
        dest.writeString(pageNum);
        dest.writeString(area);
    }
    
    public static final Parcelable.Creator<SearchInfo> CREATOR = new Creator<SearchInfo>() {
        
        @Override
        public SearchInfo[] newArray(int size) {
            return new SearchInfo[size];
        }
        
        @Override
        public SearchInfo createFromParcel(Parcel source) {
            SearchInfo info = new SearchInfo();
            info.cityCode = source.readString();
            info.startDate = source.readString();
            info.endDate = source.readString();
            info.price = source.readString();
            info.level = source.readString();
            info.areaType = source.readString();
            info.keyWord = source.readString();
            info.orderKey = source.readString();
            info.pageNum = source.readString();
            info.area = source.readString();
            return info;
        }
    };
}
