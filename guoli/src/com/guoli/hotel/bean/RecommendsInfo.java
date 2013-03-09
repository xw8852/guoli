/**
 * Project Name:Guoli
 * File Name:RecommendsInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-9下午3:51:01
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:RecommendsInfo <br/>
 * @Description:    酒店推荐接口返回对象
 * Date:     2013-3-9 下午3:51:01 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RecommendsInfo {

    @SerializedName("result")
    private List<RecommendHotelInfo> list;

    public List<RecommendHotelInfo> getList() {
        return list;
    }

    public void setList(List<RecommendHotelInfo> list) {
        this.list = list;
    }
}

