/**
 * Project Name:Guoli
 * File Name:HotelListInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-11下午10:05:13
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:HotelListInfo <br/>
 * 
 * @Description: 酒店列表对象 Date: 2013-3-11 下午10:05:13 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class HotelListInfo {

    @SerializedName("result")
    private List<HotelInfo> list;

    public List<HotelInfo> getList() {
        return list;
    }

    public void setList(List<HotelInfo> list) {
        this.list = list;
    }
}
