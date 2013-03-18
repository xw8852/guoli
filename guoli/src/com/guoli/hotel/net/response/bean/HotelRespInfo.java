/**
 * Project Name:Guoli
 * File Name:HotelRespInfo.java
 * Package Name:com.guoli.hotel.net.response.bean
 * Date:2013-3-18下午6:13:18
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.response.bean;

import com.google.gson.annotations.SerializedName;
import com.guoli.hotel.bean.HotelDetailInfo;

/**
 * ClassName:HotelRespInfo <br/>
 * @Description: hotel_hotelinfo接口返回对象
 * Date:     2013-3-18 下午6:13:18 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelRespInfo {

    /***/
    @SerializedName("hotelinfo")
    private HotelDetailInfo hotelInfo;
    
    /***/
    @SerializedName("pictotal")
    private int count;

    public HotelDetailInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelDetailInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

