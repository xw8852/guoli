/**
 * Project Name:Guoli
 * File Name:OrderPriceRequest.java
 * Package Name:com.guoli.hotel.net.request.bean
 * Date:2013-4-17下午4:54:25
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.request.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:OrderPriceRequest <br/>
 * @Description:
 * Date:     2013-4-17 下午4:54:25 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class OrderPriceRequest implements Parcelable {

    /**酒店id*/
    @SerializedName("shopid")
    private String id;
    
    /**房型ID*/
    @SerializedName("pid")
    private String pid;
    
    /**入住时间*/
    @SerializedName("indate")
    private String inDate;
    
    /**离店时间*/
    @SerializedName("outdate")
    private String endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    @Override
    public String toString() {
        return "{id=" + id + ", pid=" + pid + ", inDate=" + inDate + ", endDate=" + endDate + "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(pid);
        dest.writeString(inDate);
        dest.writeString(endDate);
    }
    
    public static final Parcelable.Creator<OrderPriceRequest> CREATOR = new Creator<OrderPriceRequest>() {
        
        @Override
        public OrderPriceRequest[] newArray(int size) {
            return new OrderPriceRequest[size];
        }
        
        @Override
        public OrderPriceRequest createFromParcel(Parcel source) {
            OrderPriceRequest request = new OrderPriceRequest();
            request.id = source.readString();
            request.pid = source.readString();
            request.inDate = source.readString();
            request.endDate = source.readString();
            return request;
        }
    };
}

