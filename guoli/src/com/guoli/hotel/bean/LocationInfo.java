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

import java.util.ArrayList;
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
    private List<ZoneInfo> zoneInfos;
    /** 商圈集合 */
    @SerializedName("bussresult")
    private List<BussinessInfo> businessInfos;

    public List<AreaInfo> getZoneInfos() {
        if (zoneInfos == null) {
            return null;
        }
        List<AreaInfo> list = new ArrayList<AreaInfo>();
        list.addAll(zoneInfos);
        return list;
    }

    public void setZoneInfos(List<ZoneInfo> zoneInfos) {
        this.zoneInfos = zoneInfos;
    }

    public List<AreaInfo> getBusinessInfos() {
        if (businessInfos == null) {
            return null;
        }
        List<AreaInfo> list = new ArrayList<AreaInfo>();
        list.addAll(businessInfos);
        return list;
    }

    public void setBusinessInfos(List<BussinessInfo> businessInfos) {
        this.businessInfos = businessInfos;
    }

}
