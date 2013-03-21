/**
 * Project Name:Guoli
 * File Name:FeedbackInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-21下午2:59:55
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:FeedbackInfo <br/>
 * @Description:    意见反馈对象
 * Date:     2013-3-21 下午2:59:55 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FeedbackInfo {

    /***/
    @SerializedName("")
    private String address;
    
    /***/
    @SerializedName("")
    private String content;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    @Override
    public String toString() {
        return "{address=" + address + ", content=" + content +"}";
    }
}

