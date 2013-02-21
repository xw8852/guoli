/**
 * Project Name:SplashActivity
 * File Name:BaseResponse.java
 * Package Name:com.guoli.hotel.net.response
 * Date:2013-2-2下午4:21:12
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.response.bean;
/**
 * ClassName:BaseResponse <br/>
 * @Description:    服务器返回数据的对象基类
 * Date:     2013-2-2 下午4:21:12 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BaseResponse {

    /**返回结果0-失败,1-成功*/
    private int success;
    /**错误提示信息*/
    private String errorMsg;
    /**返回参数*/
    private IResponseParams responseParams;
    
    public int getSuccess() {
        return success;
    }
    public void setSuccess(int success) {
        this.success = success;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public IResponseParams getResponseParams() {
        return responseParams;
    }
    public void setResponseParams(IResponseParams responseParams) {
        this.responseParams = responseParams;
    }
    
    @Override
    public String toString() {
        return "{success:" + success + ", errorMsg=" + errorMsg + ", responseParams:" + responseParams + "}";
    }
}

