/**
 * Project Name:Guoli
 * File Name:HotelPicInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-4-2下午5:09:09
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.guoli.hotel.net.response.bean.PicInfo;

/**
 * ClassName:HotelPicInfo <br/>
 * @Description:    hotel_hotelpic接口返回结果
 * Date:     2013-4-2 下午5:09:09 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelPicInfo {

    @SerializedName("result")
    private List<PicInfo> picInfos;

    public List<PicInfo> getPicInfos() {
        return picInfos;
    }

    public void setPicInfos(List<PicInfo> picInfos) {
        this.picInfos = picInfos;
    }
}

