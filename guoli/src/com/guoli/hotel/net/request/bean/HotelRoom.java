/**
 * Project Name:Guoli
 * File Name:HotelInfo.java
 * Package Name:com.guoli.hotel.net.request.bean
 * Date:2013-3-14下午7:57:49
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.request.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:HotelInfo <br/>
 * @Description:    酒店房型接口hotel_room
 * Date:     2013-3-14 下午7:57:49 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelRoom implements Parcelable {

    /**酒店ID*/
    @SerializedName("shopid")
    private String id;
    
    /**入住时间*/
    @SerializedName("indate")
    private String startDate;
    
    /**离店时间*/
    @SerializedName("outdate")
    private String endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(startDate);
        dest.writeString(endDate);
    }
    
    public static final Parcelable.Creator<HotelRoom> CREATOR = new Creator<HotelRoom>() {
        @Override
        public HotelRoom[] newArray(int size) {
            return new HotelRoom[size];
        }
        
        @Override
        public HotelRoom createFromParcel(Parcel source) {
            HotelRoom room = new HotelRoom();
            room.id = source.readString();
            room.startDate = source.readString();
            room.endDate = source.readString();
            return room;
        }
    };
}

