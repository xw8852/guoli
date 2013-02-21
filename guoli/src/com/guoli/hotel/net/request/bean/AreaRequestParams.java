/**
 * Project Name:SplashActivity
 * File Name:AreaRequestParams.java
 * Package Name:com.guoli.hotel.net.request.bean
 * Date:2013-2-4下午1:04:41
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.request.bean;
/**
 * ClassName:AreaRequestParams <br/>
 * @Description:    区域选择接口对象
 * Date:     2013-2-4 下午1:04:41 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AreaRequestParams implements IRequestParams {

    /**城市编号*/
    private String cityCode;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    
    @Override
    public String toString() {
        return "[cityCode:" + cityCode + "]";
    }
}

