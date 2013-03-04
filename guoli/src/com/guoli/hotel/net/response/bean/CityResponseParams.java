/**
 * Project Name:SplashActivity
 * File Name:CityResponseParams.java
 * Package Name:com.guoli.hotel.net.response
 * Date:2013-2-2下午4:38:48
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.response.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.guoli.hotel.net.bean.CityInfo;

/**
 * ClassName:CityResponseParams <br/>
 * @Description:    城市选择接口返回的数据
 * Date:     2013-2-2 下午4:38:48 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CityResponseParams implements IResponseParams {

    /**城市数据集合*/
    @SerializedName("result")
    private List<CityInfo> list;
    
    private int cityCount;

    public List<CityInfo> getList() {
        return list;
    }

    public void setList(List<CityInfo> list) {
        this.list = list;
    }
    
    public int getCityCount() {
        return cityCount;
    }

    public void setCityCount(int cityCount) {
        this.cityCount = cityCount;
    }

    @Override
    public String toString() {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        for (CityInfo info : list) {
            buffer.append(info.toString()).append(",");
        }
        return "result:[" + buffer.toString() + " cityCount:" + cityCount + "]";
    }
}

