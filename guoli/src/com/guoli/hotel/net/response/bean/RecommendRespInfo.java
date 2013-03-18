/**
 * Project Name:Guoli
 * File Name:RecommendRespInfo.java
 * Package Name:com.guoli.hotel.net.response.bean
 * Date:2013-3-18上午9:36:21
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.response.bean;

import com.google.gson.annotations.SerializedName;
import com.guoli.hotel.bean.RecommendInfo;

/**
 * ClassName:RecommendRespInfo <br/>
 * @Description:    hotel_rechotel接口返回对象
 * Date:     2013-3-18 上午9:36:21 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RecommendRespInfo {
    
    /**酒店图片张数*/
    @SerializedName("pictotal")
    private int count;
    
    /**入住时间*/
    @SerializedName("indate")
    private String startDate;
    
    /**离店时间*/
    @SerializedName("outdate")
    private String endDate;

    /**推荐酒店的详情*/
    @SerializedName("hotelinfo")
    private RecommendInfo recommendInfo;

    public RecommendInfo getRecommendInfo() {
        return recommendInfo;
    }

    public void setRecommendInfo(RecommendInfo recommendInfo) {
        this.recommendInfo = recommendInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
}

