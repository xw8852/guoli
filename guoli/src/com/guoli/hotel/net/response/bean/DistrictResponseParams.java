/**
 * Project Name:SplashActivity
 * File Name:AreaResponseParams.java
 * Package Name:com.guoli.hotel.net.response.bean
 * Date:2013-2-4下午1:21:08
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.response.bean;

import java.util.List;

import com.guoli.hotel.net.bean.CityInfo;
import com.guoli.hotel.net.bean.BusinessDistrictInfo;

/**
 * ClassName:AreaResponseParams <br/>
 * @Description:    区域选择的返回结果
 * Date:     2013-2-4 下午1:21:08 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DistrictResponseParams implements IResponseParams {

    /**城市/地图列表*/
    private List<CityInfo> cityList;
    /**商圈列表*/
    private List<BusinessDistrictInfo> districtList;
    
    
    public List<CityInfo> getCityList() {
        return cityList;
    }
    public void setCityList(List<CityInfo> cityList) {
        this.cityList = cityList;
    }
    public List<BusinessDistrictInfo> getDistrictList() {
        return districtList;
    }
    public void setDistrictList(List<BusinessDistrictInfo> districtList) {
        this.districtList = districtList;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}

