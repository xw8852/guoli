/**
 * Project Name:SplashActivity
 * File Name:BaseRequest.java
 * Package Name:com.guoli.hotel.net.request.bean
 * Date:2013-2-2下午4:04:23
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.net.request.bean;

/**
 * ClassName:BaseRequest <br/>
 * 
 * @Description: 接口请求基类 Date: 2013-2-2 下午4:04:23 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class BaseRequest {

    /** 业务类型 */
    private String action;
    /** 平台类型 */
    private String platformType = DEFAULT_PLATFORM_TYPE;
    /** 业务参数*/
    private IRequestParams requestParams;

    private static final String DEFAULT_PLATFORM_TYPE = "android";

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public IRequestParams getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(IRequestParams requestParams) {
        this.requestParams = requestParams;
    }

    @Override
    public String toString() {
        return "{action:" + action + ", platformType:" + platformType + ", params:" + requestParams.toString() + "}";
    }
}
