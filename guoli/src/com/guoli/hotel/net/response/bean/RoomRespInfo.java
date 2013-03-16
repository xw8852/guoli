/**
 * Project Name:Guoli
 * File Name:RoomRespInfo.java
 * Package Name:com.guoli.hotel.net.response.bean
 * Date:2013-3-15下午5:32:47
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.response.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.guoli.hotel.bean.HotelDetailInfo;
import com.guoli.hotel.bean.HotelParamsInfo;
import com.guoli.hotel.bean.RoomTypeInfo;

/**
 * ClassName:RoomRespInfo <br/>
 * @Description:    hotel_room接口返回对象
 * Date:     2013-3-15 下午5:32:47 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RoomRespInfo {

    /**房间详情*/
    @SerializedName("hotelinfo")
    private HotelDetailInfo hotelInfo;
    
    /**房间类型*/
    @SerializedName("result")
    private List<RoomTypeInfo> roomTypeInfos;
    
    /**酒店图片张数*/
    @SerializedName("piccount")
    private int picCount;
    
    /**酒店ID、入住/离店时间*/
    @SerializedName("param")
    private HotelParamsInfo paramsInfo;

    public HotelDetailInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelDetailInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public int getPicCount() {
        return picCount;
    }

    public void setPicCount(int picCount) {
        this.picCount = picCount;
    }

    public List<RoomTypeInfo> getRoomTypeInfos() {
        return roomTypeInfos;
    }

    public void setRoomTypeInfos(List<RoomTypeInfo> roomTypeInfos) {
        this.roomTypeInfos = roomTypeInfos;
    }

    public HotelParamsInfo getParamsInfo() {
        return paramsInfo;
    }

    public void setParamsInfo(HotelParamsInfo paramsInfo) {
        this.paramsInfo = paramsInfo;
    }
}

