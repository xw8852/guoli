/**
 * Project Name:SplashActivity
 * File Name:AppUpdateInfo.java
 * Package Name:com.guoli.hotel.net.request.bean
 * Date:2013-2-2下午4:02:03
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.net.request.bean;

/**
 * ClassName:AppUpdateInfo <br/>
 * 
 * @Description: app加载接口 Date: 2013-2-2 下午4:02:03 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class UpdateRequestParams implements IRequestParams {

    /** app版本号 */
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "[version:" + version + "]";
    }
}
