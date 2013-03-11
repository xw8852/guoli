/**
 * Project Name:Guoli
 * File Name:LocationInfo.java
 * Package Name:com.guoli.hotel.net.response.bean
 * Date:2013-3-6下午8:11:58
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:LocationInfo <br/>
 * 
 * @Description: 行政区域/商圈 Date: 2013-3-6 下午8:11:58 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class LocationInfo {

    /** 行政区域集合 */
    @SerializedName("zoneresult")
    private List<AreaInfo> zoneInfos;
    /** 商圈集合 */
    @SerializedName("bussresult")
    private List<AreaInfo> businessInfos;

    public List<AreaInfo> getZoneInfos() {
        return zoneInfos;
    }

    public void setZoneInfos(List<AreaInfo> zoneInfos) {
        this.zoneInfos = zoneInfos;
    }

    public List<AreaInfo> getBusinessInfos() {
        return businessInfos;
    }

    public void setBusinessInfos(List<AreaInfo> businessInfos) {
        this.businessInfos = businessInfos;
    }

}
