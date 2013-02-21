/**
 * Project Name:SplashActivity
 * File Name:AppUpdateResponseParams.java
 * Package Name:com.guoli.hotel.net.response
 * Date:2013-2-2下午4:29:08
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.net.response.bean;

import com.guoli.hotel.net.bean.UpdateInfo;

/**
 * ClassName:AppUpdateResponseParams <br/>
 * 
 * @Description: app加载接口对应的返回数据 Date: 2013-2-2 下午4:29:08 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class UpdateResponseParams implements IResponseParams {

    private UpdateInfo updateInfo;

    public UpdateInfo getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(UpdateInfo updateInfo) {
        this.updateInfo = updateInfo;
    }
    
    @Override
    public String toString() {
        return updateInfo == null ? "" : updateInfo.toString();
    }
}
