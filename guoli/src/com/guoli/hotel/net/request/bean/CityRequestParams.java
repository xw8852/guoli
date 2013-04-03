/**
 * Project Name:SplashActivity
 * File Name:CityRequestParams.java
 * Package Name:com.guoli.hotel.net.request.bean
 * Date:2013-2-2下午4:36:45
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.net.request.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:CityRequestParams <br/>
 * 
 * @Description: 城市选择接口请求参数 Date: 2013-2-2 下午4:36:45 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class CityRequestParams implements IRequestParams {

    /** 城市编号 */
    @SerializedName("citycode")
    private String cityCode;
    
    @SerializedName("version")
    private String version;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "[cityCode:" + cityCode + ", version=" + version + "]";
    }
}
