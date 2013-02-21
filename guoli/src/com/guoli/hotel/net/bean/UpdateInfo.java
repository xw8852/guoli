/**
 * Project Name:SplashActivity
 * File Name:UpdateInfo.java
 * Package Name:com.guoli.hotel.net.bean
 * Date:2013-2-2下午4:50:09
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.bean;
/**
 * ClassName:UpdateInfo <br/>
 * @Description:    app加载结果对象
 * Date:     2013-2-2 下午4:50:09 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UpdateInfo {
    /** 系统时间 */
    private String sysdate;
    /** 最新版本号 */
    private String curver;

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

    public String getCurver() {
        return curver;
    }

    public void setCurver(String curver) {
        this.curver = curver;
    }

    @Override
    public String toString() {
        return "[sysdate:" + sysdate + ", curver:" + curver + "]";
    }
}

