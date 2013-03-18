/**
 * Project Name:Guoli
 * File Name:HotelRecommendInfo.java
 * Package Name:com.guoli.hotel.net.request.bean
 * Date:2013-3-18上午9:17:51
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.request.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:HotelRecommendInfo <br/>
 * @Description:hotel_rechotel接口请求对象
 * Date:     2013-3-18 上午9:17:51 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelRecommendInfo {

    /**酒店ID*/
    @SerializedName("shopid")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

